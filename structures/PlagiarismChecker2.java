import java.util.ArrayList;

public class PlagiarismChecker2 {
    private ArrayList<Document> documents;
    private final int primeNumber = 101;

    public PlagiarismChecker2(){
        documents = new ArrayList<>();
    }

    public void addDocument(Document newDocument){
        documents.add(newDocument);
    }

    public HashTable<Integer, Integer> findSubstrings(String text, String pattern){
        HashTable<Integer, Integer> occurrences = new HashTable<>();
        int textLength = text.length();
        int patternLength = pattern.length();

        // Calcular o hash da substring de padrão
        int patternHash = pattern.hashCode();

        for (int i = 0; i <= textLength - patternLength; i++) {
            // Calcular o hash da substring do texto de tamanho patternLength
            String subText = text.substring(i, i + patternLength);
            int subTextHash = subText.hashCode();

            if (patternHash == subTextHash && pattern.equals(subText)) {
                // Se os hashes coincidirem e as substrings forem iguais, encontramos uma correspondência
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