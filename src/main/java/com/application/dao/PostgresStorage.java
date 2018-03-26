package com.application.dao;

import com.application.entity.Company;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Transactional
@Repository
public class PostgresStorage implements CompanyStorage {
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public Collection<Company> getAll() {
        String hql = "FROM Company as comp ORDER BY comp.companyName";
        return (Collection<Company>) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public Company getByName(String companyName) {
        String hql = "FROM Company WHERE companyName =:companyName";
        Company company = (Company) entityManager.createQuery(hql).setParameter("companyName", companyName).getSingleResult();
        return company;
    }

    @Override
    public void save(Company company) {
        entityManager.persist(company);
    }

    @Override
    public void update(Company company) {
        Company companyStorage = getByName(company.getCompanyName());
        companyStorage.setCompanyUrl(company.getCompanyUrl());
        companyStorage.setCompanyMail(company.getCompanyMail());
        companyStorage.setLastDate(company.getLastDate());
        entityManager.flush();
    }

    @Override
    public boolean delete(String companyName) {
        String hql = "DELETE FROM Company WHERE companyName =:companyName";
        int i = entityManager.createQuery(hql).setParameter("companyName", companyName).executeUpdate();
        return i != 0;
    }
}
