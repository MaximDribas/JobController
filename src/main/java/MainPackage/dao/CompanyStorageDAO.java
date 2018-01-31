package MainPackage.dao;

        import java.util.Collection;
        import javax.persistence.EntityManager;
        import javax.persistence.PersistenceContext;

        import MainPackage.entity.Company;
        import org.springframework.stereotype.Repository;
        import org.springframework.transaction.annotation.Transactional;
@Transactional
@Repository
public class CompanyStorageDAO implements ICompanyStorageDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public Collection<Company> getAll() {
        String hql = "FROM Company as comp ORDER BY comp.company_name";
        return (Collection<Company>)entityManager.createQuery(hql).getResultList();
    }

    @Override
    public Company getByName(String companyName) {
        String hql = "FROM Company WHERE company_name =:companyName";
        Company company = (Company) entityManager.createQuery(hql).setParameter("companyName",companyName).getSingleResult();
        return company;
    }

    @Override
    public void post(Company company) {
        entityManager.persist(company);
    }

    @Override
    public void patch(Company company) {
        Company companyStorage = getByName(company.getCompany_name());
        companyStorage.setCompany_url(company.getCompany_url());
        companyStorage.setCompany_mail(company.getCompany_mail());
        companyStorage.setLastDate(company.getLastDate());
        entityManager.flush();
    }

    @Override
    public boolean delete(String companyName) {
        String hql = "DELETE FROM Company WHERE company_name =:companyName";
        int i = entityManager.createQuery(hql).setParameter("companyName",companyName).executeUpdate();
        if (i==0)
            return false;
        else
            return true;
    }
}
