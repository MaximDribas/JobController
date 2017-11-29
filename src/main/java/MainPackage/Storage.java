package MainPackage;

import java.io.IOException;

/**
 * Created by User on 28.11.2017.
 */
public interface Storage {

    void createCompany() throws IOException;

    void printAllCompanies();

    void printCompanyByName() throws NoCompanyException, IOException;

    void removeCompany() throws NoCompanyException, IOException;

    void load() throws ClassNotFoundException;

    void save();
}
