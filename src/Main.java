import java.util.ArrayList;
import java.util.LinkedList;

public class Main{
    public static void main(String[] args){
        testDocumentReader();
    }

    public static void testDocumentReader(){
        ArrayList<String> words = DocumentReader.read("texts/text1.txt");

        for(String word : words){
            System.out.println(word);
        }
    }

    public static void testHash(){
        HashTable<String, String> hashTable = new HashTable<>();

        try{
            hashTable.put("Agenor", "Fodase");
            hashTable.put("Agenor", "Fodase2");
            hashTable.remove("Pedro");

        } catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(hashTable.get("Agenor"));

        ArrayList<String> valuesForKeyAgenor = hashTable.findAll("Agenor");
        for(String value : valuesForKeyAgenor){
            System.out.printf(value + " ");
        }
    }
}