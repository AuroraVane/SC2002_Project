import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.Scanner;

public class TextFileWriter {

    public static void WriteFile(String FILE_PATH) {
        try {
            FileWriter Writer = new FileWriter(FILE_PATH);
            System.out.println("Enter 0 to exit");
            Scanner sc=new Scanner(System.in);
            while (true){
                String x=sc.nextLine();
                if (x=="0"){
                    break;
                }
                else{
                    Writer.write(x);
                }
            }
            sc.close();
            //prevent resource leaks
            Writer.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
}
