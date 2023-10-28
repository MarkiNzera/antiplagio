import java.util.ArrayList;

public class Document {
    private final String nameOfDocument;
    private final ArrayList<String> wordOfDocument;

    public Document(String name, ArrayList<String> words){
        nameOfDocument = name;
        wordOfDocument = words;
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

}
