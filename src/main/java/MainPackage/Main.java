package MainPackage;
import java.io.*;
import java.sql.*;

/**
 * Created by User on 22.11.2017.
 */

public class Main {
    private Storage storage = new PostgresStorage();
    private boolean stop;

    public Main() throws SQLException {
    }

    public static void main(String[] args) throws Exception {
        //РАБОЧИЙ КОД (PostgresStorage не работает):
        /*try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/storagedb";
            String login = "postgres";
            String password = "qwer1234";
            Connection con = DriverManager.getConnection(url, login, password);
            try {
                Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM companies");
                while (resultSet.next()) {
                    String str = resultSet.getString("companies_id") + " " +
                            resultSet.getString(2)+ " "+resultSet.getString(3)+ " "+
                    resultSet.getString(4)+ " "+resultSet.getString(5);
                    System.out.println(str);
                }
                //resultSet.close();
                //statement.close();
            } finally {
                //con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Main main = new Main();
        while (!main.stop) {
            try {
                main.menu(reader);
            } catch (NoCompanyException e) {
                System.out.println("There is no such Company - "+e.getMessage()+"! Try again!");
            } catch (Exception e) {
                System.out.println("Input Error!");
            }
        }
    }

    private void menu(BufferedReader reader) throws Exception {
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

    private void createCompany(BufferedReader reader) throws IOException {
        System.out.println("Enter the name: ");
        String name=reader.readLine().toUpperCase();
        System.out.println("Enter the url: ");
        String url=reader.readLine();
        System.out.println("Enter the mail: ");
        String mail=reader.readLine();
        Company company = new Company(name, url, mail);
        storage.save(company);
    }

    private void printAllCompanies() {
        for (Company company : storage.getAll()) {
            System.out.println(company);
        }
    }

    private void printCompanyByName(BufferedReader reader) throws NoCompanyException, IOException {
        System.out.println("Enter the name to print: ");
        String companyName = reader.readLine().toUpperCase();
        Company company = storage.findByName(companyName);
        if (company != null){
            System.out.println(company);
        } else {
            throw new NoCompanyException(companyName);
        }
    }

    private void removeCompany(BufferedReader reader) throws NoCompanyException, IOException {
        System.out.println("Enter the name to remove Company: ");
        String companyName = reader.readLine().toUpperCase();
        if (storage.remove(companyName)) {
            System.out.println("The company "+companyName+" is deleted!");
        }
        else {
            throw new NoCompanyException(companyName);
        }
    }

    private void closeProgram(BufferedReader reader) throws Exception {
        storage.persist();
        reader.close();
        stop=true;
        System.out.println("The program is closed!");
    }
}
