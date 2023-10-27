import java.util.ArrayList;
import java.util.LinkedList;

public class Main{
    public static void main(String[] args){
        testDocumentReader();


    }

    public static void testFullAplication(){
        
    }

    public static void testPlagiarismChecker(){
        String[] text1 = {"A", "B", "A", "B", "D", "A", "B", "A",
                "C", "D", "A", "B", "A", "B", "C", "A", "B", "A", "B"};
        String[] pattern1 = {"A", "B", "A", "B", "C", "A", "B", "A", "B"};

        String text = "ABABDABACDABABCABAB";
        String pattern = "ABABCABAB";
        ArrayList<Integer> indices = search(text, pattern);

        if (indices.isEmpty()) {
            System.out.println("Padrão não encontrado no texto.");
        } else {
            System.out.println("Ocorrências do padrão nos índices:");
            for (int index : indices) {
                System.out.println(index);
            }
        }
    }

    private static final int PRIME = 101; // Número primo para a função de hash

    public static ArrayList<Integer> search(String text, String pattern) {
        ArrayList<Integer> indices = new ArrayList<>();
        int m = pattern.length();
        int n = text.length();
        long patternHash = hash(pattern, m);
        long textHash = hash(text.substring(0, m), m);

        for (int i = 0; i <= n - m; i++) {
            if (patternHash == textHash && text.substring(i, i + m).equals(pattern)) {
                indices.add(i);
            }
            if (i < n - m) {
                textHash = recalculateHash(text, textHash, i, m);
            }
        }

        return indices;
    }

    private static long hash(String str, int len) {
        long hash = 0;
        for (int i = 0; i < len; i++) {
            hash += (long) str.charAt(i) * Math.pow(PRIME, i);
        }
        return hash;
    }

    private static long recalculateHash(String text, long oldHash, int oldIndex, int m) {
        long newHash = oldHash - text.charAt(oldIndex);
        newHash /= PRIME;
        newHash += (long) text.charAt(oldIndex + m) * Math.pow(PRIME, m - 1);
        return newHash;
    }

    public static void testDocumentReader(){
        String words = DocumentReader.read("texts/text1.txt");

        Document document = new Document(words);
        document.printAllWords();
    }

    public static void testHash(){
        HashTable<String, String> hashTable = new HashTable<>();


        hashTable.put("Agenor", "Fodase");
        hashTable.put("Agenor", "Fodase2");
        hashTable.remove("Pedro");



        System.out.println(hashTable.get("Agenor"));

        ArrayList<String> valuesForKeyAgenor = hashTable.findAll("Agenor");
        for(String value : valuesForKeyAgenor){
            System.out.printf(value + " ");
        }
    }
}