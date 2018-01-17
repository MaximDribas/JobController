package MainPackage;

        import java.util.Collection;
        import javax.persistence.EntityManager;
        import javax.persistence.PersistenceContext;
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
        String sql = "SELECT * FROM companies";
        return (Collection<Company>)entityManager.createQuery(sql).getResultList();
    }

    @Override
    public Company findByName(String companyName) {
        return entityManager.find(Company.class,companyName);
    }

    @Override
    public boolean remove(String companyName) {
        entityManager.remove(findByName(companyName));
        return true;
    }
}
