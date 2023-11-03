import java.util.ArrayList;

public class Main{
    public static void main(String[] args){
//        testDocumentReader();
        testPlagiarismChecker2();
//        avlTreeTest();
    }

    public static void testPlagiarismChecker2(){
        Document text1 = DocumentReader.read("documento sobre hash", "texts/text1.txt");
        Document text2 = DocumentReader.read("documento sobre arvore", "texts/text2.txt");
        Document plagiarizedText = DocumentReader.read("documento plagiado", "texts/plagiarism.txt");

        PlagiarismChecker plagiarismChecker = new PlagiarismChecker();

        plagiarismChecker.addDocument(text1);
        plagiarismChecker.addDocument(text2);

        HashTable<Integer, Integer> hashTable = new HashTable<>();
        AvlTree<Integer, Integer> avlTree = new AvlTree<>();

        Pairs<Document, PlagiarismStrategy<Integer, Integer>> teste1 = plagiarismChecker.checkPlagiarism(plagiarizedText, 176, hashTable);

        for(Pairs.Pair<Document, PlagiarismStrategy<Integer, Integer>> pair : teste1.pairs){
            System.out.println("Plagios encontrados no documento: " + pair.getKey().getNameOfDocument() + ": ");
            System.out.println();
            Document currentDocument = pair.getKey();
            for(NodeStrategy<Integer, Integer> node : pair.getValue().nodeSet()){
                System.out.println("Início: " + node.getKey() + ", Fim: " + node.getValue());
                System.out.print("Trecho: ");
                currentDocument.getSnippetOfDocument(node.getKey(), node.getValue());
            }
            System.out.println();

        }

    }

//    public static void testPlagiarismChecker(){
//        Document text1 = DocumentReader.read("documento sobre hash", "texts/text1.txt");
//        Document text2 = DocumentReader.read("documento sobre arvore", "texts/text2.txt");
//        Document plagiarizedText = DocumentReader.read("documento plagiado", "texts/plagiarism.txt");
//
//        PlagiarismChecker plagiarismChecker = new PlagiarismChecker();
//
//        plagiarismChecker.addDocument(text1);
//        plagiarismChecker.addDocument(text2);
//
//        HashTable<Document, PlagiarismStrategy<Integer, Integer>> strategyHash = new HashTable<>();
//        AvlTree<Document, PlagiarismStrategy<Integer, Integer>> strategyTree = new AvlTree<>();
//
//        // HashTable<Document, HashTable<Integer, Integer>> teste1 = (HashTable) plagiarismChecker.checkPlagiarism(plagiarizedText, 176, strategyHash);
//        AvlTree<Document, AvlTree<Integer, Integer>> teste2 = (AvlTree) plagiarismChecker.checkPlagiarism(plagiarizedText, 176, strategyTree);
//
//        for(NodeStrategy<Document, PlagiarismStrategy<Integer, Integer>> documentNode : teste2.nodeSet()){
//            System.out.println("Plagios encontrados no documento: " + documentNode.getKey().getNameOfDocument() + ": ");
//            System.out.println();
//            Document currentDocument = documentNode.getKey();
//            for(HashTable.HashNode<Integer, Integer> node : documentNode.getValue().nodeSet()){
//                System.out.println("Início: " + node.getKey() + ", Fim: " + node.getValue());
//                System.out.print("Trecho: ");
//                currentDocument.getSnippetOfDocument(node.getKey(), node.getValue());
//            }
//            System.out.println();
//
//        }
//
//    }

    public static void testDocumentReader(){
        Document document = DocumentReader.read("texto sobre hash", "texts/text1.txt");

        document.printAllWords();
    }

    public static void avlTreeTest(){
        AvlTree<Integer, Integer> avlTree = new AvlTree<>();

        avlTree.put(3, 3);
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
        for(NodeStrategy<String, String> node : hashTable.nodeSet()){
            System.out.println("chave: " + node.getKey() + ", valor: " + node.getValue());
        }
    }
}