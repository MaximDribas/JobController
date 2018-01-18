package MainPackage;

        import java.util.Collection;
        import javax.persistence.EntityManager;
        import javax.persistence.PersistenceContext;
        import javax.persistence.Query;

        import org.springframework.stereotype.Repository;
        import org.springframework.transaction.annotation.Transactional;
@Transactional
@Repository
public class SpringStorageDAO implements IStorageDAO{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void persist() {

    }

    @Override
    public void save(Company company) {
        entityManager.persist(company);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<Company> getAll() {
        String hql = "FROM Company as comp ORDER BY comp.company_name";
        return (Collection<Company>)entityManager.createQuery(hql).getResultList();
    }

    @Override
    public Company findByName(String companyName) {
        Query query = entityManager.createQuery("FROM Company WHERE name =:companyName", Company.class);
        query.setParameter("companyName", companyName);
        return (Company) query.getResultList();
    }

    @Override
    public boolean remove(String companyName) {
        entityManager.remove(findByName(companyName));
        return true;
    }
}
