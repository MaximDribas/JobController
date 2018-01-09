package MainPackage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * Created by User on 20.12.2017.
 */
public class HibernateStorage implements Storage {
    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    Session session = sessionFactory.openSession();


    @Override
    public void persist() {
        session.close();
        sessionFactory.close();
    }

    @Override
    public void save(Company company) {
        Transaction transaction = session.beginTransaction();
        session.save(company);
        transaction.commit();
    }

    @Override
    public Collection<Company> getAll() {
        Query<Company> query = session.createQuery("FROM Company ", Company.class);
        return query.list();
    }

    @Override
    public Collection<Company> findByName(String companyName) {
        Query<Company> query = session.createQuery("FROM Company WHERE name =:companyName", Company.class);
        query.setParameter("companyName", companyName);
        return query.list();
    }

    @Override
    @Transactional
    public boolean remove(String companyName) {
        Query query = session.createQuery("DELETE Company WHERE name = :companyName");
        query.setParameter("companyName", companyName);

        Transaction transaction = session.beginTransaction();
        int updatedResults = query.executeUpdate();
        transaction.commit();
        return updatedResults > 0;
    }
}
