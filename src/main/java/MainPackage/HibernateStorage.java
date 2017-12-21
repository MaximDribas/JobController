package MainPackage;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.util.Collection;
import java.util.List;

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
        final List<Company> COMPANY_LIST;
        Criteria criteria = session.createCriteria(Company.class);
        COMPANY_LIST = criteria.list();
        return COMPANY_LIST;
    }

    @Override
    public Collection<Company> findByName(String companyName) {
        final List<Company> COMPANY_LIST;
        Criteria criteria = session.createCriteria(Company.class);
        criteria.add(Restrictions.eq("name",companyName));
        COMPANY_LIST = criteria.list();
        return COMPANY_LIST;
    }    /*public Company findByName(String companyName) {
        Company company = session.get(Company.class,1);
        return company;
    }*/

    @Override
    public boolean remove(String companyName) {
        Transaction transaction = session.beginTransaction();
        String s = String.format("DELETE FROM companies WHERE name = '%s';", companyName);
        session.createSQLQuery(s).executeUpdate();
        transaction.commit();

        return true;
    }
}
