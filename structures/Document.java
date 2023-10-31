import java.util.ArrayList;
import java.util.StringTokenizer;

public class Document {
    private final String nameOfDocument;
    private final String contentOfDocument;

    private int length;

    public Document(String name, String words){
        nameOfDocument = name;
        contentOfDocument = preprocess(words);
        length = contentOfDocument.length();
    }

    public String getContentOfDocument(){ return contentOfDocument;}

    public String[] getWordsOfDocument(){
        return contentOfDocument.split(" ");
    }

    public String getNameOfDocument(){
        return nameOfDocument;
    }

    public void getSnippetOfDocument(int init, int end){
        for(int i = init; i < end; i++){
            System.out.printf(contentOfDocument.charAt(i) + "");
        }
        System.out.println();
    }

    public void printAllWords(){
        for(String word : contentOfDocument.split(" ")){
            System.out.println(word);
        }
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

}
