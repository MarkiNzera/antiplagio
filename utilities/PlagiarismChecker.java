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

        PlagiarismStrategy<Integer, String> plagiarizedDocumentHashes = findPatternHashes(plagiarizedDocument, m, plagiarismStrategy);

        for(Document document : documents){
            ArrayList<Integer> occurrences = findAllOccurrences(plagiarizedDocumentHashes,
                    document, m);

            occurrencesInAllDocuments.put(document, occurrences);
        }


        return occurrencesInAllDocuments;
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

    private ArrayList<Integer> findAllOccurrences(PlagiarismStrategy<Integer, String> plagiarizedDocument, Document checkingDocument, int m){
        ArrayList<Integer> occurrences = new ArrayList<>();
        String contentOfCheckingDocument = checkingDocument.getContentOfDocument();

        for(NodeStrategy<Integer, String> node : plagiarizedDocument.nodeSet()){
            String patternText = node.getValue();
            int patternHash = node.getKey();
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
