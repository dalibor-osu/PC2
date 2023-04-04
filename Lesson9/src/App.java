import Animals.*;
import Employees.Employee;
import Employees.EmployeeType;
import Employees.Manager;
import Employees.Secretarian;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstraktní třídu je vhodné využít jako šablonu pro třídy od ní odvozené. Na rozdíl od rozhraní může implementovat
 * datová pole a modifikátory přístupu. Rozhraní pouze popisuje vlastnosti třídy a obsahuje metody, nebo konstanty.
 * Odvozená třída může dědit pouze z jedné nadřazené třídy, ale může implementovat několik rozhraní.
 *
 * Enumeraci je vhodné využít u pevně daných množin prvků a místo Stringu se používá, jelikož je práce s ním jednodušší
 * a výpočetně méně náročná. Často najedeme enum například u bloků switch, kde je jeho využití optimálnější než použití Stringu.
 *
 * @author Dalibor Drevojanek
 */
public class App {
    public static void main(String[] args) {
        // Vytvoření zaměstnanců a jejich vložení do Listu
        Employee employee1 = new Employee("Martin".toCharArray(), "martin@email.com".toCharArray(), "123".toCharArray(), EmployeeType.ACTIVE);
        Employee employee2 = new Employee("Honza".toCharArray(), "honza@email.com".toCharArray(), "heslo".toCharArray(), EmployeeType.ACTIVE);
        Employee employee3 = new Employee("David".toCharArray(), "david@email.com".toCharArray(), "osdgio43t".toCharArray(), EmployeeType.DELETED);
        Employee employee4 = new Employee("Pepa".toCharArray(), "pepa@email.com".toCharArray(), "faktdobreheslo".toCharArray(), EmployeeType.INACTIVE);
        Employee employee5 = new Employee("Katka".toCharArray(), "katka@email.com".toCharArray(), "kjasoig983jk2t398".toCharArray(), EmployeeType.ACTIVE);

        List<Employee> listOfEmployees = new ArrayList<>();
        listOfEmployees.add(employee1);
        listOfEmployees.add(employee2);
        listOfEmployees.add(employee3);
        listOfEmployees.add(employee4);
        listOfEmployees.add(employee5);

        // Vytvoření sekretářek a manažera
        Secretarian secretarian1 = new Secretarian("Kuba".toCharArray(), "kuba@email.com".toCharArray(), "ojaoigjg".toCharArray(), EmployeeType.INACTIVE, 29);
        Secretarian secretarian2 = new Secretarian("Tereza".toCharArray(), "tereza@email.com".toCharArray(), "sdgwekjhboi".toCharArray(), EmployeeType.ACTIVE, 23);
        Manager<Secretarian> manager = new Manager<>("Majo".toCharArray(), "sef@email.com".toCharArray(), "iausghiaIAUSGHIUHASG".toCharArray(), EmployeeType.ACTIVE, listOfEmployees, secretarian2);

        // Volání metody pro seřazení zaměstnanců podle emailu
        manager.sortByEmployeesByEmail();

        // Výpis zaměstnanců
        for (Employee employee : manager.getListOfEmployees()) {
            System.out.println(employee);
        }

        // Výpis vztahu
        System.out.println(manager.getListOfRelationships());


        // Vytváření instancí zvířat a volání metody sound()
        AbstractAnimal cat = new Cat();
        cat.sound();
        AbstractAnimal dog = new Dog();
        dog.sound();
        AbstractAnimal pig = new Pig();
        pig.sound();
        AbstractAnimal cow = new Cow();
        cow.sound();
        AbstractAnimal goat = new Goat();
        goat.sound();

        Animal catImpl = new CatImpl();
        catImpl.sound();
        catImpl.saveToFile();
        Animal dogImpl = new DogImpl();
        dogImpl.sound();
        Animal pigImpl = new PigImpl();
        pigImpl.sound();
        Animal cowImpl = new CowImpl();
        cowImpl.sound();
        Animal goatImpl = new GoatImpl();
        goatImpl.sound();
    }
}