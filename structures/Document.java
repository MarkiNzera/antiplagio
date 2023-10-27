import java.util.ArrayList;

public class Document {
    private final String wordOfDocument;

    public Document(String words){
        wordOfDocument = words;
    }

    public String getWordOfDocument(){
        return wordOfDocument;
    }

    public void printAllWords(){
        for(String word : wordOfDocument.split(" ")){
            System.out.println(word);
        }
    }

}
