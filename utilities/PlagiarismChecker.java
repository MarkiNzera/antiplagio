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

    private ArrayList<Integer> findAllOccurrences(PlagiarismStrategy<Integer, String> plagiarizedDocumentHashes, Document checkingDocument, int m){
        ArrayList<Integer> occurrences = new ArrayList<>();
        String contentOfCheckingDocument = checkingDocument.getContentOfDocument();

        for(int i = 0; i <= checkingDocument.getLength() - m; i += m){
            String subText = contentOfCheckingDocument.substring(i, i + m);
            int subTextHash = subText.hashCode();

            if(plagiarizedDocumentHashes.contains(subTextHash) && plagiarizedDocumentHashes.get(subTextHash).equals(subText)){
                occurrences.add(i);
            }
        }


        return occurrences;
    }

}
