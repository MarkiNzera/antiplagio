

public class Main{
    public static void main(String[] args){
        HashTable<String, String> hashTable = new HashTable<>();

        hashTable.put("Agenor", "Fodase");

        System.out.println(hashTable.get("Agenor"));
    }
}