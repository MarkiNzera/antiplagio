import java.util.ArrayList;

public class Document {
    private final ArrayList<String> wordOfDocument;

    public Document(ArrayList<String> words){
        wordOfDocument = words;
    }

    public ArrayList<String> getWordOfDocument(){
        return wordOfDocument;
    }

    public void printAllWords(){
        for(String word : wordOfDocument){
            System.out.println(word);
        }
    }

}
