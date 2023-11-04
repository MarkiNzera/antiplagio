import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Document implements Comparable<Document>{
    private final String nameOfDocument;
    private final String contentOfDocument;
    private final ArrayList<String> wordsOfDocument;
    private final int length;
    private final int numOfWords;


    public Document(String name, String words){
        nameOfDocument = name;
        contentOfDocument = preprocess(words);
        wordsOfDocument = loadWordsOfDocument();
        length = contentOfDocument.length();
        numOfWords = wordsOfDocument.size();
    }

    public String getContentOfDocument(){ return contentOfDocument;}

    public String getNameOfDocument(){
        return nameOfDocument;
    }

    public ArrayList<String> getWordsOfDocument(){
        return wordsOfDocument;
    }

    public void getSnippetOfDocument(int init, int end){
        for(int i = init; i < end; i++){
            System.out.printf(wordsOfDocument.get(i) + " ");
        }
        System.out.println();
    }

    public int getLength(){
        return length;
    }
    public int getNumOfWords(){
        return numOfWords;
    }

    public void printAllWords() {
        for (String word : wordsOfDocument) {
            System.out.println(word);
        }
    }

    private ArrayList<String> loadWordsOfDocument(){
        return new ArrayList<>(Arrays.asList(contentOfDocument.split(" ")));
    }

    private String preprocess(String words){
        StringTokenizer tokenizer = new StringTokenizer(words);

        StringBuilder tokens = new StringBuilder();
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            tokens.append(token).append(" ");
        }

        return tokens.toString();
    }

    @Override
    public int compareTo(Document other){
        return getContentOfDocument().compareTo(other.getContentOfDocument());
    }

}
