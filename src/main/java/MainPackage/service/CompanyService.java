package MainPackage.service;
import MainPackage.dao.ICompanyStorageDAO;
import MainPackage.entity.Company;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CompanyService implements ICompanyService{

    private ICompanyStorageDAO storageDAO;

    public CompanyService(ICompanyStorageDAO storageDAO) {
        this.storageDAO = storageDAO;
    }

    @Override
    public Collection<Company> getAll() {
        return storageDAO.getAll();
    }

    @Override
    public Company getByName(String companyName) {
        Company company = storageDAO.getByName(companyName);
        return company;
    }

    @Override
    public void post(Company company) {
        storageDAO.post(company);
    }

    @Override
    public void patch(Company company) {
        storageDAO.patch(company);
    }

    @Override
    public boolean delete(String companyName) {
        return storageDAO.delete(companyName);
    }


    //private boolean stop;
    /*public void run(String... args)throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (!stop) {
            try {
                menu(reader);
            } catch (NoCompanyException e) {
                System.out.println("There is no such Company - "+e.getMessage()+"! Try again!");
            } catch (Exception e) {
                System.out.println("Input Error!");
            }
        }
    }

    private void menu(BufferedReader reader) throws Exception {
        System.out.println();
        System.out.println("Click: 1 - create the Company, 2 - print all Companies, 3 - print Company by name, 4 - delete Company, 0 - Exit the program");

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
        String name=reader.readLine();
        System.out.println("Enter the url: ");
        String url=reader.readLine();
        System.out.println("Enter the mail: ");
        String mail=reader.readLine();
        Company company = new Company(name, url, mail);
        storage.post(company);
    }

    private void printAllCompanies() {
        for (Company company : storage.getAll()) {
            System.out.println(company);
            System.out.println();
        }
    }

    private void printCompanyByName(BufferedReader reader) throws NoCompanyException, IOException {
        System.out.println("Enter the name to print: ");
        String companyName = reader.readLine();
        for (Company company:storage.getByName(companyName)) {
            System.out.println(company);
            System.out.println();
        }
    }

    private void removeCompany(BufferedReader reader) throws NoCompanyException, IOException {
        System.out.println("Enter the name to delete Company: ");
        String companyName = reader.readLine();
        if (storage.delete(companyName)) {
            System.out.println("The company "+companyName+" is deleted!");
        }
        else {
            throw new NoCompanyException(companyName);
        }
    }

    private void closeProgram(BufferedReader reader) throws Exception {
        reader.close();
        stop=true;
        System.out.println("The program is closed!");
    }*/
}
