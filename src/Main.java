import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

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
        Document plagiarismText = DocumentReader.read("documento plagiado", "texts/plagiarism.txt");

        PlagiarismChecker plagiarismChecker = new PlagiarismChecker();

        plagiarismChecker.addDocument(text1);
        plagiarismChecker.addDocument(text2);

        System.out.println(plagiarismChecker.search(text1.getContentOfDocument(), plagiarismText.getContentOfDocument()));
        System.out.println(plagiarismChecker.search("abcdef", "def"));

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