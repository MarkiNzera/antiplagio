import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class DocumentReader {
    public static Document read(String nameOfDocumente, String path){

        StringBuilder words = new StringBuilder(" ");

        try{
            File file = new File(path);
            Scanner scanner = new Scanner(file);

            while(scanner.hasNext()){
                String word = scanner.next();
                words.append(word).append(" ");
            }
            scanner.close();

            return new Document(nameOfDocumente, words.toString());
        } catch (FileNotFoundException e){
            System.err.println("O arquivo não pode ser encontrado");
        }

        return null;
    }

}
