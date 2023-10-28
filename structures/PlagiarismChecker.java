import java.util.ArrayList;

public class PlagiarismChecker {
    private ArrayList<Document> documents;
    private final int primeNumber = 101;
    private HashTable<Long, Document> documentIndex = new HashTable<>();
    private int n;

    public PlagiarismChecker(int n){
        documents = new ArrayList<>();
        this.n = n;
    }

    public void addDocument(Document content){
        documents.add(content);
    }

    public void indexDocument(String documentName, ArrayList<String> words){
        for(int i = 0; i < words.size(); i++){
            String wordsSequence = getNWordSequence(words, i, n);
            long hash = hash(wordsSequence);

            if(!documentIndex.contains(hash)){
                documentIndex.put(hash, new Document(documentName, words));
            }
        }
    }

    public void checkPlagiarism(Document userDocument){
        ArrayList<String> userWords = userDocument.getWordOfDocument();

        for(int i = 0; i < userWords.size(); i++){
            String wordSequence = getNWordSequence(userWords, i, n);
            long hash = hash(wordSequence);

            if(documentIndex.contains(hash)){
                Document matchingDocument = documentIndex.get(hash);
                int positionInUserDoc = i;
                int positionInMatchedDoc = matchingDocument.getWordOfDocument().indexOf(wordSequence);

                System.out.println("Plágio encontrado em " + matchingDocument.getNameOfDocument() +
                        " na posição " + positionInMatchedDoc +
                        ", no documento do usuário na posição " + positionInUserDoc);
            }
        }
    }

//    public void findPlagiarismOfAllDocuments(Document text){
//        for(Document document : documents){
//            search(document, text);
//        }
//    }

//    private ArrayList<Integer> search(String[] text, String[] pattern){
//        ArrayList<Integer> positionOfPlagiarisms = new ArrayList<>();
//        int patternLength = pattern.length();
//        int textLength = text.length();
//        long patternHash = hash(pattern, patternLength);
//        long textHash = hash(text.substring(0, patternLength), patternLength);
//
//        for(int i = 0; i <= textLength - patternLength; i++){
//            if(patternHash == textHash && text.substring(i, i + patternLength).equals(pattern)){
//                positionOfPlagiarisms.add(i);
//            }
//            if(i < textLength - patternLength){
//                textHash = recalculateHash(text, textHash, i, patternLength);
//            }
//        }
//
//        return positionOfPlagiarisms;
//    }

    private long hash(String str) {
        long hash = 0;
        for (int i = 0; i < str.length(); i++) {
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

    private String getNWordSequence(ArrayList<String> words, int position, int n){
        if(position < 0 || position + n > words.size()){
            return null;
        }

        StringBuilder wordSequence = new StringBuilder();
        for(int i = position; i < position + n; i++){
            wordSequence.append(words.get(i)).append(" ");
        }

        wordSequence.setLength(wordSequence.length() - 1);

        return wordSequence.toString();
    }

}
