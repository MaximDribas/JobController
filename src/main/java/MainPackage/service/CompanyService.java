package MainPackage.service;

import MainPackage.ValidationException;
import MainPackage.dao.ICompanyStorageDAO;
import MainPackage.entity.Company;
import org.mockito.InjectMocks;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CompanyService {

    public static final int MIN_COMPANY_NAME_LENGTH = 3;
    public static final int MAX_COMPANY_NAME_LENGTH = 255;
    private static final Pattern MAIL_PATTERN = Pattern.compile("^[-a-z0-9!#$%&'*+/=?^_`{|}~]+(\\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*@([a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\\.)*(aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$");
    private static final Pattern URL_PATTERN = Pattern.compile("^http\\://[a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,3}(/\\S*)?$");

    private ICompanyStorageDAO storageDAO;

    public CompanyService(ICompanyStorageDAO storageDAO) {
        this.storageDAO = storageDAO;
    }

    public Collection<Company> getAll() {
        return storageDAO.getAll();
    }

    public Company getByName(String companyName) {
        checkNameLength(companyName);
        return storageDAO.getByName(companyName);
    }

    public void save(Company company) {
        validate(company);
        storageDAO.save(company);
    }

    private void validate(Company company) {
        checkNameLength(company.getCompanyName());
        checkUrlWithRegExp(company.getCompanyUrl());
        checkMailWithRegExp(company.getCompanyMail());
    }

    public void update(Company company) {
        validate(company);
        storageDAO.update(company);
    }

    public boolean delete(String companyName) {
        checkNameLength(companyName);
        return storageDAO.delete(companyName);
    }

    private void checkNameLength(String companyName) {
        if (companyName.length() <= MIN_COMPANY_NAME_LENGTH) {
            throw new ValidationException(
                    String.format("Company name is too short. " +
                                    "It must be longer than %s character.",
                            MIN_COMPANY_NAME_LENGTH)
            );
        }
        if (companyName.length() >= MAX_COMPANY_NAME_LENGTH) {
            throw new ValidationException(
                    String.format("Company name is too long. " +
                                    "It must be shooter than %s character.",
                            MAX_COMPANY_NAME_LENGTH)
            );
        }
    }

    private void checkMailWithRegExp(String companyMail) {
        Matcher m = MAIL_PATTERN.matcher(companyMail);
        if (!m.matches()) {
            throw new ValidationException("Invalid mail address");
        }
    }

    private void checkUrlWithRegExp(String companyUrl) {
        Matcher m = URL_PATTERN.matcher(companyUrl);
        if (!m.matches()) {
            throw new ValidationException("Invalid url provided");
        }
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