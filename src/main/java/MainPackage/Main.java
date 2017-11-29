package MainPackage;
import java.io.*;

/**
 * Created by User on 22.11.2017.
 */

public class Main {
    private static CompaniesDatabase companiesDatabase=new CompaniesDatabase();
    private static boolean stop;

    public Main() throws Exception {
    }

    public static void main(String[] args) throws Exception {
        companiesDatabase.load();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (!stop) {
            try {
                menu(reader);
            } catch (NoCompanyException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Input Error!");
            }
        }
    }

    private static void menu(BufferedReader reader) throws Exception {
        System.out.println();
        System.out.println("Click: 1 - create the Company, 2 - print all Companies, 3 - print Company by name, 4 - remove Company, 0 - Exit the program");

        int number = Integer.parseInt(reader.readLine());

        switch (number){
            case 1:
                companiesDatabase.createCompany();
                break;
            case 2:
                companiesDatabase.printAllCompanies();
                break;
            case 3:
                companiesDatabase.printCompanyByName();
                break;
            case 4:
                companiesDatabase.removeCompany();
                break;
            case 0:
                closeProgram(reader);
                break;
            default:
                throw new Exception();
        }
    }

    private static void closeProgram(BufferedReader reader) throws Exception {
        companiesDatabase.save();
        reader.close();
        stop=true;
        System.out.println("The program is closed!");
    }
}
