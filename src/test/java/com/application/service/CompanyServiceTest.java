package com.application.service;

import com.application.ValidationException;
import com.application.dao.CompanyStorage;
import com.application.entity.Company;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CompanyServiceTest {

    private static final String CORRECT_URL = "http://anyhost.com";
    private static final String CORRECT_MAIL = "any@mail.com";

    @Mock
    private CompanyStorage companyStorageDAOMock;

    @InjectMocks
    private CompanyService companyService;

    @Captor
    private ArgumentCaptor<Company> argumentCaptor;

    public CompanyServiceTest() {
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnFalse_whenNotDeleteCompany() throws Exception {
        String nameDoesNotExist = "nameDoesNotExist";

        when(companyStorageDAOMock.delete(nameDoesNotExist)).thenReturn(false);
        boolean resultReturn = companyService.delete(nameDoesNotExist);
        assertEquals(false, resultReturn);

        verify(companyStorageDAOMock).delete(nameDoesNotExist);
    }

    @Test
    public void shouldReturnCompany_whenNameIsCorrect() throws Exception {
        String name = "CorrectName";
        Company company = new Company(name, CORRECT_URL, CORRECT_MAIL);

        when(companyStorageDAOMock.getByName(name)).thenReturn(company);

        Company resultCompany = companyService.getByName(name);

        assertEquals(resultCompany, company);
    }

    @Test
    public void shouldSaveCompany_whenNameIsCorrect() throws Exception {
        String expectedName = "CorrectName";
        Company company = new Company(expectedName, CORRECT_URL, CORRECT_MAIL);

        companyService.save(company);

        verify(companyStorageDAOMock).save(argumentCaptor.capture());
        assertEquals(company, argumentCaptor.getValue());
    }

    @Test
    public void shouldThrowException_whenNameIsTooShort() throws Exception {
        String shortName = "n";
        Company company = new Company(shortName, CORRECT_URL, CORRECT_MAIL);
        String expectedMessage = "Company name is too short. " +
                "It must be longer than 3 character.";

        try {
            companyService.save(company);
        } catch (ValidationException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    public void shouldThrowException_whenNameIsTooLong() throws Exception {
        String longName = "Very looooooooooooooooooooooooooooooooooooooooo" +
                "ooooooooooooooooooooooooooooooooooooooooooooooooooooooooo" +
                "ooooooooooooooooooooooooooooooooooooooooooooooooooooooooo" +
                "ooooooooooooooooooooooooooooooooooooooooooooooooooooooooo" +
                "ooooooooooooooooooooooooooooooooooooooooooooooooooooooooo" +
                "ooooooooooooooooooooooooooooooooooooooooooooooooooooooooo" +
                "ooooooooooooooooooooooooooooong name";
        Company company = new Company(longName, CORRECT_URL, CORRECT_MAIL);
        String expectedMessage = "Company name is too long. " +
                "It must be shooter than 255 character.";

        try {
            companyService.save(company);
        } catch (ValidationException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowException_whenCompanyNameIsTooShort() throws Exception {
        companyService.getByName("");
    }

}