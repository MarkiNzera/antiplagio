import java.util.ArrayList;

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

        PlagiarismChecker1 plagiarismChecker = new PlagiarismChecker1();

        plagiarismChecker.addDocument(text1);
        plagiarismChecker.addDocument(text2);

        HashTable<Integer, Integer> teste1 = plagiarismChecker.checkPlagiarism(text1.getContentOfDocument(), plagiarismText.getContentOfDocument(), 200);
        HashTable<Integer, Integer> teste2 = plagiarismChecker.findSubstrings("abcdef", "def");

        for(HashTable.HashNode<Integer, Integer> node : teste1.nodeSet()){
            System.out.println("Início: " + node.getKey() + ", Fim: " + node.getValue());
            System.out.println("Trecho: ");
            text1.getSnippetOfDocument(node.getKey(), node.getValue());
        }

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

        System.out.println();
        for(HashTable.HashNode<String, String> node : hashTable.nodeSet()){
            System.out.println("chave: " + node.getKey() + ", valor: " + node.getValue());
        }
    }
}