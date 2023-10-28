import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import opennlp.tools.stemmer.PorterStemmer;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

public class Main{
    public static void main(String[] args){
//        testDocumentReader();
        testPlagiarismChecker();
    }

//    public static void testFullAplication(){
//        String text1 = DocumentReader.read("texts/text1.txt");
//        String text2 = DocumentReader.read("texts/text2.txt");
//        String plagiarismText = DocumentReader.read("texts/plagiarism.txt");
//
//        PlagiarismChecker plagiarismChecker = new PlagiarismChecker(6);
//
//    }

    public static void testPlagiarismChecker(){
        Document text1 = DocumentReader.read("documento sobre hash", "texts/text1.txt");
        Document text2 = DocumentReader.read("documento sobre arvore", "texts/text2.txt");
        Document plagiarismText = DocumentReader.read("documento plaiado", "texts/plagiarism.txt");

        PlagiarismChecker plagiarismChecker = new PlagiarismChecker(6);

        plagiarismChecker.addDocument(text1);
        plagiarismChecker.addDocument(text2);

        plagiarismChecker.checkPlagiarism(plagiarismText, 3);

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
        Document document = DocumentReader.read("texto sobre hash", "texts/text1.txt");

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