import java.util.ArrayList;
import java.util.List;

public class Main{
    public static void main(String[] args){
//        testDocumentReader();
//        avlTreeTest();
        testPlagiarismChecker();
//        String teste = "Grafos são estruturas matemáticas fundamentais que desempenham um papel crucial em várias áreas da ciência da computação,\n" +
//                "matemática discreta e engenharia de redes. Eles são utilizados para modelar uma variedade de problemas do mundo real, desde\n" +
//                "redes de computadores até sistemas de transporte e relações sociais.";
//        String[] teste2 = teste.split(" ");
//
//        System.out.println(teste2.length);
    }

    public static void testPlagiarismChecker(){
        Document text1 = DocumentReader.read("documento sobre hash", "texts/avlText.txt");
        Document text2 = DocumentReader.read("documento sobre arvore", "texts/hashText.txt");
        Document text3 = DocumentReader.read("documento sobre listas ligadas", "texts/linkedListText.txt");
        Document text4 = DocumentReader.read("documento sobre fila", "texts/queueText.txt");
        Document text5 = DocumentReader.read("documento sobre pilha", "texts/stackText.txt");
        Document text6 = DocumentReader.read("documento sobre grafo", "texts/graphText.txt");
        Document plagiarizedText = DocumentReader.read("documento plagiado", "texts/plagiarism.txt");

        PlagiarismChecker plagiarismChecker = new PlagiarismChecker();

        plagiarismChecker.addDocument(text1);
        plagiarismChecker.addDocument(text2);
        plagiarismChecker.addDocument(text3);
        plagiarismChecker.addDocument(text4);
        plagiarismChecker.addDocument(text5);
        plagiarismChecker.addDocument(text6);

        int m = 26;

        HashTable<Integer, List<String>> hashTable = new HashTable<>();
        AvlTree<Integer, List<String>> avlTree = new AvlTree<>();

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
        Document document = DocumentReader.read("texto sobre hash", "texts/hashText .txt");

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
    }
}