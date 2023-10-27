import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class DocumentReader {
    public static String read(String path){

        String words = "";

        try{
            File file = new File(path);
            Scanner scanner = new Scanner(file);

            while(scanner.hasNext()){
                String word = scanner.next();
                words += " " + word;
            }
            scanner.close();

            return words;
        } catch (FileNotFoundException e){
            System.err.println("O arquivo não pode ser encontrado");
        }

        return null;
    }

}
