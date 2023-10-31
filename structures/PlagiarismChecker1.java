import java.util.ArrayList;

public class PlagiarismChecker1 {
    private ArrayList<Document> documents;
    private final int primeNumber = 101;

    public PlagiarismChecker1(){
        documents = new ArrayList<>();
    }

    public void addDocument(Document newDocument){
        documents.add(newDocument);
    }

    public HashTable<Integer, Integer> checkPlagiarism(String text, String plagiarism, int m){
        HashTable<Integer, Integer> patternHashes = new HashTable<>();

        int textLength = text.length();
        int plagiarismLength = plagiarism.length();

        if(plagiarismLength < m){
            return null;
        }

        int patternHash = plagiarism.hashCode();

        for (int i = 0; i <= textLength - plagiarismLength; i++) {
            String subText = text.substring(i, i + plagiarismLength);
            int subTextHash = subText.hashCode();

            if (patternHash == subTextHash && plagiarism.equals(subText)) {
                for(int j = 0; j <= plagiarismLength - m; j += m){
                    String subword = plagiarism.substring(j, j + m);
                    if(text.contains(subword)){
                        patternHashes.put(i + j, i + j + m - 1);

                    }
                }
            }
        }

        return patternHashes;
    }

    public HashTable<Integer, Integer> findSubstrings(String text, String plagiarism){
        HashTable<Integer, Integer> occurrences = new HashTable<>();
        int textLength = text.length();
        int patternLength = plagiarism.length();

        int patternHash = plagiarism.hashCode();

        for (int i = 0; i <= textLength - patternLength; i++) {
            String subText = text.substring(i, i + patternLength);
            int subTextHash = subText.hashCode();

            if (patternHash == subTextHash && plagiarism.equals(subText)) {
                occurrences.put(i, i + patternLength - 1);
            }
        }

        return occurrences;
    }

//    public ArrayList<Integer> search(String text, String pattern){
//        ArrayList<Integer> indices = new ArrayList<>();
//        int patternLength = pattern.length();
//        int textLength = text.length();
//
//        long patternHash = hash(pattern, patternLength);
//        long textHash = hash(text.substring(0, patternLength), patternLength);
//
//        for(int i = 0; i <= textLength - patternLength; i++){
//            if (patternHash == textHash && text.substring(i, i + patternLength).equals(pattern)){
//                indices.add(i);
//            }
//
//            if(i < textLength - patternLength){
//                textHash = recalculateHash(text, textHash, i, patternLength);
//            }
//        }
//
//        return indices;
//
//    }

    private long hash(String str, int len){
        long hash = 0;
        for(int i = 0; i < len; i++){
            hash += (long) str.charAt(i) * Math.pow(primeNumber, i);
        }

        return hash;
    }

    public long recalculateHash(String text, long oldHash, int oldIndex, int m){
        long newHash = oldHash - text.charAt(oldIndex);
        newHash /= primeNumber;
        newHash += (long) text.charAt(oldIndex + m) * Math.pow(primeNumber, m - 1);
        return newHash;
    }
}
