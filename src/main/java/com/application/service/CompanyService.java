package com.application.service;

import com.application.ValidationException;
import com.application.dao.CompanyStorage;
import com.application.entity.Company;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CompanyService {

    public static final int MIN_COMPANY_NAME_LENGTH = 3;
    public static final int MAX_COMPANY_NAME_LENGTH = 255;
    private static final Pattern MAIL_PATTERN = Pattern.compile("^[-a-z0-9!#$%&'*+/=?^_`{|}~]+(\\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*@([a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\\.)*(aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$");
    private static final Pattern URL_PATTERN = Pattern.compile("^http\\://[a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,3}(/\\S*)?$");

    private CompanyStorage storageDAO;

    public CompanyService(CompanyStorage storageDAO) {
        this.storageDAO = storageDAO;
    }

    public Collection<Company> getAll() {
        return storageDAO.getAll();
    }

    public Company getByName(String companyName) {
        checkNameLength(companyName);
        return storageDAO.getByName(companyName);
    }

    public void save(Company company) {
        validate(company);
        storageDAO.save(company);
    }

    public void update(Company company) {
        validate(company);
        storageDAO.update(company);
    }

    public boolean delete(String companyName) {
        checkNameLength(companyName);
        return storageDAO.delete(companyName);
    }

    private void validate(Company company) {
        checkNameLength(company.getCompanyName());
        checkUrlWithRegExp(company.getCompanyUrl());
        checkMailWithRegExp(company.getCompanyMail());
    }

    private void checkNameLength(String companyName) {
        if (companyName.length() <= MIN_COMPANY_NAME_LENGTH) {
            throw new ValidationException(
                    String.format("Company name is too short. " +
                                    "It must be longer than %s character.",
                            MIN_COMPANY_NAME_LENGTH)
            );
        }
        if (companyName.length() >= MAX_COMPANY_NAME_LENGTH) {
            throw new ValidationException(
                    String.format("Company name is too long. " +
                                    "It must be shooter than %s character.",
                            MAX_COMPANY_NAME_LENGTH)
            );
        }
    }

    private void checkMailWithRegExp(String companyMail) {
        Matcher m = MAIL_PATTERN.matcher(companyMail);
        if (!m.matches()) {
            throw new ValidationException("Invalid mail address");
        }
    }

    private void checkUrlWithRegExp(String companyUrl) {
        Matcher m = URL_PATTERN.matcher(companyUrl);
        if (!m.matches()) {
            throw new ValidationException("Invalid url provided");
        }
    }
}