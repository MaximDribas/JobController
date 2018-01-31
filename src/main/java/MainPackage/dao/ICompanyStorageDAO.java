package MainPackage.dao;

import MainPackage.entity.Company;

import java.util.Collection;

public interface ICompanyStorageDAO {

    Collection<Company> getAll();

    Company getByName(String companyName);

    void post(Company company);

    void patch(Company company);

    boolean delete(String companyName);
}
