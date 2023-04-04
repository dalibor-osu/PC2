package Animals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GoatImpl implements Animal{
    private final String s = "Meow";
    @Override
    public void sound() {
        System.out.println(s);
    }

    @Override
    public void saveToFile() {
        try {
            File file = new File("goat.txt");
            if (file.exists())
                file.delete();

            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Error occurred when creating a file");
            return;
        }

        try {
            FileWriter writer = new FileWriter("goat.txt");
            writer.write(s);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error occurred when writing to file");
        }
    }
}
