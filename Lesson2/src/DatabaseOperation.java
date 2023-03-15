import java.util.Scanner;

public class DatabaseOperation {
    public static void main(String[] args) {
        Database[] dabaseArray = new Database[3];

        Scanner sc = new Scanner(System.in);

        new Scanner(System.in);

        for (int i = 0; i < 3; i++)
        {
            System.out.println("Zadejte jmeno " + (i + 1) + ". zamestnance: ");
            String name = sc.nextLine();
            System.out.println("Zadejte rok narozeni " + (i + 1) + ". zamestnance: ");
            int year = sc.nextInt();
            sc.nextLine();
            dabaseArray[i] = new Database(name, year);
        }

        System.out.println("Zadejte maximalni hodnotu uvazku: ");
        float max = sc.nextFloat();
        Database.SetMax(max);

        while(true)
        {
            System.out.println("Zadejte cislo zamestnance: ");
            int index = sc.nextInt() - 1;
            System.out.println("Zadejte uvazek: ");
            float uvazek = sc.nextFloat();

            if (dabaseArray[index].SetUvazek(uvazek))
            {
                System.out.println("Uvazek byl uspesne pridelen.");
            }
            else
            {
                System.out.println("Uvazek nebyl pridelen, protoze presahuje maximalni hodnotu.");
            }
        }
    }
}