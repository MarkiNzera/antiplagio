import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class DocumentReader {
    public static ArrayList<String> read(String path){

        ArrayList<String> words = new ArrayList<>();

        try{
            File file = new File(path);
            Scanner scanner = new Scanner(file);

            while(scanner.hasNext()){
                String word = scanner.next();
                words.add(word);
            }
            scanner.close();

            return words;
        } catch (FileNotFoundException e){
            System.err.println("O arquivo não pode ser encontrado");
        }

        return null;
    }

}
