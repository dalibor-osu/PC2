package Animals;

import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors

public class CatImpl implements Animal {

    private final String s = "Meow";
    @Override
    public void sound() {
        System.out.println(s);
    }

    @Override
    public void saveToFile() {
        try {
            File file = new File("cat.txt");
            if (file.exists())
                file.delete();

            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Error occurred when creating a file");
            return;
        }

        try {
            FileWriter writer = new FileWriter("cat.txt");
            writer.write(s);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error occurred when writing to file");
        }
    }
}
