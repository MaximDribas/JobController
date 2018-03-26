package com.application.dao;

import com.application.entity.Company;

import java.util.Collection;

public interface CompanyStorage {

    Collection<Company> getAll();

    Company getByName(String companyName);

    void save(Company company);

    void update(Company company);

    boolean delete(String companyName);
}
