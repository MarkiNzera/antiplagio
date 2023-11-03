import java.util.ArrayList;

public class Main{
    public static void main(String[] args){
//        testDocumentReader();
        testPlagiarismChecker();
//        avlTreeTest();
    }

    public static void testPlagiarismChecker(){
        Document text1 = DocumentReader.read("documento sobre hash", "texts/text1.txt");
        Document text2 = DocumentReader.read("documento sobre arvore", "texts/text2.txt");
        Document plagiarizedText = DocumentReader.read("documento plagiado", "texts/plagiarism.txt");

        PlagiarismChecker plagiarismChecker = new PlagiarismChecker();

        plagiarismChecker.addDocument(text1);
        plagiarismChecker.addDocument(text2);

        int m = 176;

        HashTable<Integer, String> hashTable = new HashTable<>();
        AvlTree<Integer, String> avlTree = new AvlTree<>();

        Pairs<Document, ArrayList<Integer>> testeArvore = plagiarismChecker.checkPlagiarism(plagiarizedText, m, avlTree);
        Pairs<Document, ArrayList<Integer>> testeHashTable = plagiarismChecker.checkPlagiarism(plagiarizedText, m, hashTable);

        System.out.println("Buscando plagios usando Arvores AVL");
        printPlagiarizedTexts(testeArvore, m);

        System.out.println();
        System.out.println();

        System.out.println("Buscando plagios usando Hash Tables");
        printPlagiarizedTexts(testeHashTable, m);

    }

    public static void printPlagiarizedTexts(Pairs<Document, ArrayList<Integer>> pairs, int m){
        for(Pairs.Pair<Document, ArrayList<Integer>> pair : pairs.pairs){
            System.out.println("Plagios encontrados no documento: " + pair.getKey().getNameOfDocument() + ": ");
            System.out.println();
            Document currentDocument = pair.getKey();
            for(Integer node : pair.getValue()){
                int init = node, end = init + m;
                System.out.println("Início: " + init + ", Fim: " + end);
                System.out.print("Trecho: ");
                currentDocument.getSnippetOfDocument(init, end);
            }
            System.out.println();

        }
    }

    public static void testDocumentReader(){
        Document document = DocumentReader.read("texto sobre hash", "texts/text1.txt");

        document.printAllWords();
    }

    public static void avlTreeTest(){
        AvlTree<Integer, Integer> avlTree = new AvlTree<>();

        avlTree.put(3, 3);
        avlTree.put(3, 7);
        avlTree.put(3, 8);
        avlTree.put(2, 2);
        avlTree.put(1, 1);
        avlTree.put(4, 4);
        avlTree.put(5, 5);
        avlTree.put(6, 6);
        avlTree.put(7, 7);
        avlTree.put(16, 16);
        avlTree.put(15, 15);
        avlTree.put(14, 14);

        avlTree.inOrder();

        System.out.println();
        ArrayList<Integer> valuesForKeyAgenor = avlTree.findAllK(3);
        for(Integer value : valuesForKeyAgenor){
            System.out.printf(value + " ");
        }


    }

    public static void testHash(){
        HashTable<String, String> hashTable = new HashTable<>();


        hashTable.put("Agenor", "Fodase");
        hashTable.put("Agenor", "Fodase2");
        hashTable.remove("Pedro");



        System.out.println(hashTable.get("Agenor"));

        ArrayList<String> valuesForKeyAgenor = hashTable.findAllK("Agenor");
        for(String value : valuesForKeyAgenor){
            System.out.printf(value + " ");
        }

        System.out.println();
        for(NodeStrategy<String, String> node : hashTable.nodeSet()){
            System.out.println("chave: " + node.getKey() + ", valor: " + node.getValue());
        }
    }
}