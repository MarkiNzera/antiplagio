import java.util.ArrayList;

public class PlagiarismChecker {
    private ArrayList<Document> documents;

    public PlagiarismChecker(){
        documents = new ArrayList<>();
    }

    public void addDocument(Document newDocument){
        documents.add(newDocument);
    }

    public Pairs<Document, ArrayList<Integer>> checkPlagiarism(Document plagiarizedDocument, int m, PlagiarismStrategy<Integer, String> plagiarismStrategy){
        Pairs<Document, ArrayList<Integer>> occurrencesInAllDocuments = new Pairs<>();

        for(Document document : documents){
            ArrayList<Integer> occurrences = checkPlagiarismInOneDocument(document,
                    plagiarizedDocument, m, plagiarismStrategy);

            occurrencesInAllDocuments.put(document, occurrences);
        }

        return occurrencesInAllDocuments;
    }

    private ArrayList<Integer> checkPlagiarismInOneDocument(Document checkingDocument,
                Document plagiarizedDocument, int m, PlagiarismStrategy<Integer, String> plagiarismStrategy){
        int textLength = checkingDocument.getLength();
        int plagiarismLength = plagiarizedDocument.getLength();

        if(plagiarismLength < m){
            m = plagiarismLength;
        }

        PlagiarismStrategy<Integer, String> patternHashes = findPatternHashes(plagiarizedDocument, m, plagiarismStrategy);


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

    private ArrayList<Integer> findAllOccurrences(PlagiarismStrategy<Integer, String> patternHashes, Document checkingDocument, int m){
        ArrayList<Integer> occurrences = new ArrayList<>();
        String contentOfCheckingDocument = checkingDocument.getContentOfDocument();

        for(NodeStrategy<Integer, String> hash : patternHashes.nodeSet()){
            String patternText = hash.getValue();
            int patternHash = hash.getKey();
            int patternLength = patternText.length();

            for(int i = 0; i <= checkingDocument.getLength() - patternLength; i += m){
                String subText = contentOfCheckingDocument.substring(i, i + patternLength);
                int subTextHash = subText.hashCode();

                if(patternHash == subTextHash && patternText.equals(subText)){
                    occurrences.add(i);
                }
            }
        }

        return occurrences;
    }

}
