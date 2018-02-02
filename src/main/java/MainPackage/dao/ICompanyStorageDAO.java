package MainPackage.dao;

import MainPackage.entity.Company;

import java.util.Collection;

public interface ICompanyStorageDAO {

    Collection<Company> getAll();

    Company getByName(String companyName);

    void save(Company company);

    void update(Company company);

    boolean delete(String companyName);
}
