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

    @Override
    public void post(Company company) {
        entityManager.persist(company);
    }

    @Override
    public void patch(Company company) {

    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<Company> getAll() {
        String hql = "FROM Company as comp ORDER BY comp.company_name";
        return (Collection<Company>)entityManager.createQuery(hql).getResultList();
    }

    @Override
    public Collection<Company> getByName(String companyName) {
        String hql = "FROM Company WHERE company_name =:companyName";
        return (Collection<Company>) entityManager.createQuery(hql).setParameter("companyName",companyName).getResultList();
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
