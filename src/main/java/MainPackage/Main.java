package MainPackage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 22.11.2017.
 */

public class Main implements Serializable{
    private static Map<String, Company> companyMap =null;
    private static boolean stop=false;

    public Main() throws Exception {
    }

    public static void main(String[] args) throws Exception {
        load();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (!stop) {
            try {
                menu(reader);
            } catch (Exception e) {
                System.out.println("Input Error!");
            }
        }
    }

    private static void load() throws Exception {
        try {
            ObjectInputStream in =  new ObjectInputStream (new FileInputStream("objects.dat"));
            companyMap = (Map<String, Company>) in.readObject();
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found! Created new database!");
            companyMap = new HashMap<>();
        } catch (IOException e){
            System.out.println("Input/Output Exception!");
            System.exit(1);
        }
    }

    private static void menu(BufferedReader reader) throws Exception {
        System.out.println();
        System.out.println("Click: 1 - create the Company, 2 - print all Companies, 3 - print Company by name, 4 - remove Company, 0 - Exit the program");

        int number = Integer.parseInt(reader.readLine());

        switch (number){
            case 1:
                createCompany(reader);
                break;
            case 2:
                printAllCompanies();
                break;
            case 3:
                printCompanyByName(reader);
                break;
            case 4:
                removeCompany(reader);
                break;
            case 0:
                closeProgram(reader);
                break;
            default:
                throw new Exception();
        }
    }

    private static void closeProgram(BufferedReader reader) throws Exception {
        save();
        reader.close();
        stop=true;
        System.out.println("The program is closed!");
    }

    private static void save() throws Exception {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("objects.dat"));
            out.writeObject(companyMap);
            out.close();
        } catch (FileNotFoundException e){
            System.out.println("File not found!");
        } catch (IOException e){
            System.out.println("Input/Output Exception!");
        }
    }

    private static void createCompany(BufferedReader reader) throws Exception {
        System.out.println("Enter the name: ");
        String name=reader.readLine().toUpperCase();
        System.out.println("Enter the url: ");
        String url=reader.readLine();
        System.out.println("Enter the mail: ");
        String mail=reader.readLine();
        companyMap.put(name, new Company(name,url,mail));
    }

    private static void printAllCompanies() {
        for (Map.Entry <String, Company> next : companyMap.entrySet()) {
            printCompany(next.getValue());
        }
    }

    private static void printCompanyByName(BufferedReader reader) throws Exception {
        try {
            System.out.println("Enter the name to print: ");
            printCompany(companyMap.get(reader.readLine().toUpperCase()));
        } catch (Exception e){
            System.out.println("There is no such Company! Try again!");
            throw e;
        }
    }

    private static void removeCompany(BufferedReader reader) throws Exception {
        try {
            System.out.println("Enter the name to remove Company: ");
            String name = companyMap.get(reader.readLine().toUpperCase()).getName();
            companyMap.remove(name);
        } catch (Exception e){
            System.out.println("There is no such Company! Try again!");
            throw e;
        }
    }

    private static void printCompany(Company company) {
        System.out.println();
        System.out.println("*** "+company.getName()+" *** information: ");
        System.out.println(company.getUrl());
        System.out.println(company.getMail());
        System.out.println(company.getLastDateSimpleFormat());
    }
}
