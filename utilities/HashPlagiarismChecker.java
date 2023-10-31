import java.util.ArrayList;

public class HashPlagiarismChecker {
    private ArrayList<Document> documents;

    public HashPlagiarismChecker(){
        documents = new ArrayList<>();
    }

    public void addDocument(Document newDocument){
        documents.add(newDocument);
    }

    public HashTable<Document, HashTable<Integer, Integer>> checkPlagiarismInAllDocuments(Document plagiarizedDocument, int m){
        HashTable<Document, HashTable<Integer, Integer>> occurrencesInAllDocuments = new HashTable<>();
        for(Document document : documents){
            HashTable<Integer, Integer> occurrences = checkPlagiarism(document.getContentOfDocument(),
                    plagiarizedDocument.getContentOfDocument(), m);

            occurrencesInAllDocuments.put(document, occurrences);
        }

        return occurrencesInAllDocuments;
    }

    public HashTable<Integer, Integer> checkPlagiarism(String checkingText, String plagiarizedText, int m){
        int textLength = checkingText.length();
        int plagiarismLength = plagiarizedText.length();

        if(plagiarismLength < m){
            m = plagiarismLength;
        }

        HashTable<Integer, String> patternHashes = findPatternHashes(plagiarizedText, plagiarismLength, m);

        HashTable<Integer, Integer> occurrences = new HashTable<>();

        for(HashTable.HashNode<Integer, String> hash : patternHashes.nodeSet()){
            String patternText = hash.getValue();
            int patternHash = hash.getKey();
            int patternLength = patternText.length();

            for(int i = 0; i <= textLength - patternLength; i += 1){
                String subText = checkingText.substring(i, i + patternLength);
                int subTextHash = subText.hashCode();

                if(patternHash == subTextHash && patternText.equals(subText)){
                    occurrences.put(i, i + patternLength - 1);
                }
            }
        }

        return occurrences;
    }

    private HashTable<Integer, String> findPatternHashes(String plagiarism, int plagiarismLength, int m){
        HashTable<Integer, String> patternHashes = new HashTable<>();
        for(int i = 0; i < plagiarismLength - m; i++){
            String substring = plagiarism.substring(i, i + m);
            int patternHash = substring.hashCode();
            patternHashes.put(patternHash, substring);
        }

        return patternHashes;
    }

}
