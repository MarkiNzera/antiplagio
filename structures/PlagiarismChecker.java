import java.util.ArrayList;

public class PlagiarismChecker {
    private ArrayList<Document> documents;
    private final int primeNumber = 101;

    public PlagiarismChecker(){
        documents = new ArrayList<>();
    }

    public void addDocument(ArrayList<String> content){
        documents.add(new Document(content));
    }

    public void addDocument(Document document){
        documents.add(document);
    }

//    public void findPlagiarismOfAllDocuments(Document text){
//        for(Document document : documents){
//            search(document, text);
//        }
//    }

    private ArrayList<Integer> search(String[] text, String[] pattern){
        ArrayList<Integer> positionOfPlagiarisms = new ArrayList<>();
        int patternLength = pattern.length();
        int textLength = text.length();
        long patternHash = hash(pattern, patternLength);
        long textHash = hash(text.substring(0, patternLength), patternLength);

        for(int i = 0; i <= textLength - patternLength; i++){
            if(patternHash == textHash && text.substring(i, i + patternLength).equals(pattern)){
                positionOfPlagiarisms.add(i);
            }
            if(i < textLength - patternLength){
                textHash = recalculateHash(text, textHash, i, patternLength);
            }
        }

        return positionOfPlagiarisms;
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
