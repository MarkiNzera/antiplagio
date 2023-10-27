import java.util.ArrayList;

public class PlagiarismChecker {
    private HashTable<String, Document> documentHash;
    private final int primeNumber = 101;

    public PlagiarismChecker(){
        documentHash = new HashTable<>();
    }

    public void addDocument(String documentId, ArrayList<String> content){
        documentHash.put(documentId, new Document(content));
    }

    private ArrayList<Integer> search(String text, String pattern){
        ArrayList<Integer> positionOfPlgiarisms = new ArrayList<>();
        int patternLength = pattern.length();
        int textLength = text.length();
        long patternHash = hash(pattern, patternLength);
        long textHash = hash(text.substring(0, patternLength), patternLength);

        for(int i = 0; i <= textLength - patternLength; i++){
            if(patternHash == textHash && text.substring(i, i + patternLength).equals(pattern)){
                positionOfPlgiarisms.add(i);
            }
            if(i < textLength - patternLength){
                textHash = recalculateHash(text, textHash, i, patternLength);
            }
        }

    }

    private long hash(String str, int len) {
        long hash = 0;
        for (int i = 0; i < len; i++) {
            hash += (long) str.charAt(i) * Math.pow(primeNumber, i);
        }
        return hash;
    }

    private long recalculateHash(String text, long oldHash, int oldIndex, int patternLength){
        long newHash = oldHash - text.charAt(oldIndex);
        newHash /= primeNumber;
        newHash += (long) text.charAt(oldIndex + patternLength) * Math.pow(primeNumber, patternLength - 1);
        return newHash;
    }

//    public void checkPlagiarism(String inputDocument, int m){
//        for(HashTable.HashNode<String, Document> node: documentHash.nodeSet()){
//            String documentId = node.getKey();
//            Document document = node.getValue();
//            ArrayList<String> content = document.getWordOfDocument();
//            for(int i = 0; i <= content.size(); i++){
//                String sequence = content.substring(i, i + m);
//                if(inputDocument.contains(sequence)){
//                    System.out.println("Plagiarism found in document: " + documentId);
//                    System.out.println("Plagiarized sequence: " + sequence);
//                }
//            }
//        }
//    }

}
