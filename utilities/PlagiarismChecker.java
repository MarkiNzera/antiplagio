import java.util.ArrayList;

public class PlagiarismChecker {
    private ArrayList<Document> documents;

    public PlagiarismChecker(){
        documents = new ArrayList<>();
    }

    public void addDocument(Document newDocument){
        documents.add(newDocument);
    }

    public Pairs<Document, PlagiarismStrategy<Integer, Integer>> checkPlagiarism(Document plagiarizedDocument, int m, PlagiarismStrategy<Integer, Integer> plagiarismStrategy){
        Pairs<Document, PlagiarismStrategy<Integer, Integer>> occurrencesInAllDocuments = new Pairs<>();

        for(Document document : documents){
            PlagiarismStrategy<Integer, Integer> occurrences = checkPlagiarismInOneDocument(document,
                    plagiarizedDocument, m, plagiarismStrategy);

            occurrencesInAllDocuments.put(document, occurrences);
        }

        return occurrencesInAllDocuments;
    }

    private PlagiarismStrategy<Integer, Integer> checkPlagiarismInOneDocument(Document checkingDocument, Document plagiarizedDocument, int m, PlagiarismStrategy<Integer, Integer> plagiarismStrategy){
        int textLength = checkingDocument.getLength();
        int plagiarismLength = plagiarizedDocument.getLength();

        if(plagiarismLength < m){
            m = plagiarismLength;
        }

        PlagiarismStrategy<Integer, String> patternHashes = findPatternHashes(plagiarizedDocument, m, new HashTable<>());


        return findAllOccurrences(patternHashes, checkingDocument, m);
    }

    private PlagiarismStrategy<Integer, String> findPatternHashes(Document plagiarizedDocument, int m, PlagiarismStrategy<Integer, String> plagiarismStrategy){
        String contentOfPlagiarizedDocument = plagiarizedDocument.getContentOfDocument();

        for(int i = 0; i < plagiarizedDocument.getLength() - m; i++){
            String substring = contentOfPlagiarizedDocument.substring(i, i + m);
            int patternHash = substring.hashCode();
            plagiarismStrategy.put(patternHash, substring);
        }

        return plagiarismStrategy;
    }

    private HashTable<Integer, Integer> findAllOccurrences(PlagiarismStrategy<Integer, String> patternHashes, Document checkingDocument, int m){
        HashTable<Integer, Integer> occurrences = new HashTable<>();
        String contentOfCheckingDocument = checkingDocument.getContentOfDocument();

        for(NodeStrategy<Integer, String> hash : patternHashes.nodeSet()){
            String patternText = hash.getValue();
            int patternHash = hash.getKey();
            int patternLength = patternText.length();

            for(int i = 0; i <= checkingDocument.getLength() - patternLength; i += m){
                String subText = contentOfCheckingDocument.substring(i, i + patternLength);
                int subTextHash = subText.hashCode();

                if(patternHash == subTextHash && patternText.equals(subText)){
                    occurrences.put(i, i + patternLength - 1);
                }
            }
        }

        return occurrences;
    }

}
