package MainPackage.service;
import MainPackage.dao.ICompanyStorageDAO;
import MainPackage.entity.Company;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CompanyService {

    private ICompanyStorageDAO storageDAO;

    public CompanyService(ICompanyStorageDAO storageDAO) {
        this.storageDAO = storageDAO;
    }

    public Collection<Company> getAll() {
        return storageDAO.getAll();
    }

    public Company getByName(String companyName) {
        if (checkNameWithRegExp(companyName)) {
            return storageDAO.getByName(companyName);
        } else
            return null;
    }

    public void save(Company company) {
        if (checkNameWithRegExp(company.getCompanyName())&&checkUrlWithRegExp(company.getCompanyUrl())&&checkMailWithRegExp(company.getCompanyMail()))
            storageDAO.save(company);
    }

    public void update(Company company) {
        if (checkNameWithRegExp(company.getCompanyName())&&checkUrlWithRegExp(company.getCompanyUrl())&&checkMailWithRegExp(company.getCompanyMail()))
            storageDAO.update(company);
    }

    public boolean delete(String companyName) {
        if (checkNameWithRegExp(companyName))
            return storageDAO.delete(companyName);
        else
            return false;
    }

    public boolean checkNameWithRegExp(String companyName){
        return companyName.length() >= 3 && companyName.length() <= 255;
    }

    public boolean checkMailWithRegExp(String companyMail){
        Pattern p = Pattern.compile("^[-a-z0-9!#$%&'*+/=?^_`{|}~]+(\\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*@([a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\\.)*(aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$");
        Matcher m = p.matcher(companyMail);
        return m.matches();
    }

    public boolean checkUrlWithRegExp(String companyMail){
        Pattern p = Pattern.compile("^http\\://[a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,3}(/\\S*)?$");
        Matcher m = p.matcher(companyMail);
        return m.matches();
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
