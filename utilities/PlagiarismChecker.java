import java.util.ArrayList;
import java.util.List;

public class PlagiarismChecker {
    private ArrayList<Document> documents;

    public PlagiarismChecker(){
        documents = new ArrayList<>();
    }

    public void addDocument(Document newDocument){
        documents.add(newDocument);
    }


    public Pairs<Document, ArrayList<Integer>> checkPlagiarism(Document plagiarizedDocument, int m, PlagiarismStrategy<Integer, List<String>> plagiarismStrategy){
        Pairs<Document, ArrayList<Integer>> occurrencesInAllDocuments = new Pairs<>();

        PlagiarismStrategy<Integer, List<String>> plagiarizedDocumentHashes = findPlagiarizedHashes(plagiarizedDocument, m, plagiarismStrategy);

        for(Document document : documents){
            ArrayList<Integer> occurrences = findAllOccurrencesInOneDocument(plagiarizedDocumentHashes,
                    document, m);

            occurrencesInAllDocuments.put(document, occurrences);
        }


        return occurrencesInAllDocuments;
    }

    private PlagiarismStrategy<Integer, List<String>> findPlagiarizedHashes(Document plagiarizedDocument, int m, PlagiarismStrategy<Integer, List<String>> plagiarismStrategy){
        List<String> wordsOfPlagiarizedDocument = plagiarizedDocument.getWordsOfDocument();

        for(int i = 0; i < plagiarizedDocument.getNumOfWords() - m; i++){
            List<String> subWord = wordsOfPlagiarizedDocument.subList(i, i + m);
            int patternHash = subWord.hashCode();
            plagiarismStrategy.put(patternHash, subWord);
        }

        return plagiarismStrategy;
    }

    private ArrayList<Integer> findAllOccurrencesInOneDocument(PlagiarismStrategy<Integer, List<String>> plagiarizedDocumentHashes, Document checkingDocument, int m){
        ArrayList<Integer> occurrences = new ArrayList<>();
        List<String> contentOfCheckingDocument = checkingDocument.getWordsOfDocument();

        for(int i = 0; i <= checkingDocument.getNumOfWords() - m; i ++){
            List<String> subWord = contentOfCheckingDocument.subList(i, i + m);
            int subWordHash = subWord.hashCode();

            if(plagiarizedDocumentHashes.contains(subWordHash) && plagiarizedDocumentHashes.get(subWordHash).equals(subWord)){
                occurrences.add(i);
            }
        }

        return occurrences;
    }

}
