import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class DocumentReader {
    public static Document read(String nameOfDocumente, String path){

        // ArrayList<String> words = new ArrayList<>();
        StringBuilder words = new StringBuilder(" ");

        try{
            File file = new File(path);
            Scanner scanner = new Scanner(file);

            while(scanner.hasNext()){
                String word = scanner.next();
                words.append(word + " ");
            }
            scanner.close();

            Document newDocument = new Document(nameOfDocumente, words.toString());

            return newDocument;
        } catch (FileNotFoundException e){
            System.err.println("O arquivo não pode ser encontrado");
        }

        return null;
    }

}
