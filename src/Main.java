import java.util.ArrayList;
import java.util.LinkedList;

public class Main{
    public static void main(String[] args){
        HashTable<String, String> hashTable = new HashTable<>();

        try{
            hashTable.put("Agenor", "Fodase");
            hashTable.put("Agenor", "Fodase2");

        } catch (Exception e){
            e.printStackTrace();
        }

        try{
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