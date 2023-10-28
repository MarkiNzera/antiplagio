import java.util.ArrayList;
import java.util.StringTokenizer;

public class Document {
    private final String nameOfDocument;
    private final ArrayList<String> wordOfDocument;

    public Document(String name, String words){
        nameOfDocument = name;
        wordOfDocument = preprocess(words);
    }

    public ArrayList<String> getWordOfDocument(){
        return wordOfDocument;
    }

    public String getNameOfDocument(){
        return nameOfDocument;
    }

    public void printAllWords(){
        for(String word : wordOfDocument){
            System.out.println(word);
        }
    }

    private ArrayList<String> preprocess(String words){
        StringTokenizer tokenizer = new StringTokenizer(words);

        ArrayList<String> tokens = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            tokens.add(token);
        }

        return tokens;
    }

}
