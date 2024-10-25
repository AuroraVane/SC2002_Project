import java.io.File;  // Import the File class
import java.io.IOException;

public class TextFileCreator {
    public void createFile(String FILE_PATH){
        try {
            File myObj = new File(FILE_PATH);
            if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
            } else {
            System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
