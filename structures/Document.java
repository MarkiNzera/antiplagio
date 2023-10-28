import java.util.ArrayList;
import java.util.StringTokenizer;

public class Document {
    private final String nameOfDocument;
    private final String wordOfDocument;

    public Document(String name, String words){
        nameOfDocument = name;
        wordOfDocument = preprocess(words);
    }

    public String[] getWordOfDocument(){
        return wordOfDocument.split(" ");
    }

    public String getNameOfDocument(){
        return nameOfDocument;
    }

    public void printAllWords(){
        for(String word : wordOfDocument.split(" ")){
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
