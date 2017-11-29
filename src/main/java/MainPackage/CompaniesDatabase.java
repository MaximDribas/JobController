package MainPackage;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 28.11.2017.
 */
public class CompaniesDatabase implements Storage {
    private Map<String, Company> companyMap = new HashMap<>();
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void createCompany() throws IOException {
        System.out.println("Enter the name: ");
        String name=reader.readLine().toUpperCase();
        System.out.println("Enter the url: ");
        String url=reader.readLine();
        System.out.println("Enter the mail: ");
        String mail=reader.readLine();
        companyMap.put(name, new Company(name,url,mail));
    }

    @Override
    public void printAllCompanies() {
        for (Map.Entry <String, Company> next : companyMap.entrySet()) {
            System.out.println(next.getValue());
        }
    }

    @Override
    public void printCompanyByName() throws NoCompanyException, IOException {
        System.out.println("Enter the name to print: ");
        String companyName = reader.readLine().toUpperCase();
        if (companyMap.containsKey(companyName)){
            System.out.println(companyMap.get(companyName));
        } else {
            System.out.println("There is no such Company! Try again!");
            throw new NoCompanyException(companyName);
        }
    }

    @Override
    public void removeCompany() throws NoCompanyException, IOException {
        System.out.println("Enter the name to remove Company: ");
        String companyName = reader.readLine().toUpperCase();
        if (companyMap.containsKey(companyName)) {
            //String name = COMPANY_MAP.get(companyName).getName();
            companyMap.remove(companyName);
        }
        else {
            System.out.println("There is no such Company! Try again!");
            throw new NoCompanyException(companyName);
        }
    }

    @Override
    public void load() throws ClassNotFoundException {
        try {
            ObjectInputStream in =  new ObjectInputStream (new FileInputStream("objects.dat"));
            companyMap.putAll((Map<String, Company>) in.readObject());
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found! Created new database!");
        } catch (IOException e){
            System.out.println("Input/Output Exception!");
            //stop=true;
        }
    }

    @Override
    public void save() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("objects.dat"));
            out.writeObject(companyMap);
            out.close();
            reader.close();
        } catch (FileNotFoundException e){
            System.out.println("File not found!");
        } catch (IOException e){
            System.out.println("Input/Output Exception!");
        }
    }
}
