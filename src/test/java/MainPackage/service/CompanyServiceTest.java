package MainPackage.service;

import MainPackage.ValidationException;
import MainPackage.dao.CompanyStorageDAO;
import MainPackage.dao.ICompanyStorageDAO;
import MainPackage.entity.Company;
import org.junit.*;
import org.mockito.*;
import org.mockito.stubbing.OngoingStubbing;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class CompanyServiceTest {

    private static final String CORRECT_URL = "http://anyhost.com";
    private static final String CORRECT_MAIL = "any@mail.com";

    @Mock
    private ICompanyStorageDAO companyStorageDAOMock;

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
    public void getAll() throws Exception {
    }

    @Test
    public void getByName() throws Exception {
    }

    @Test
    public void save() throws Exception {

    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void shouldReturnFalse_whenNotDeleteCompany() throws Exception {
        //given
        String nameDoesNotExist = "nameDoesNotExist";

        //when
        when(companyStorageDAOMock.delete(nameDoesNotExist)).thenReturn(false);
        boolean resultReturn=companyService.delete(nameDoesNotExist);
        assertEquals(false,resultReturn);

        //then
        verify(companyStorageDAOMock).delete(nameDoesNotExist);

        //Verify that false returned if the company is not in the database.
        //Verify that companyStorageDAOMock.delete methods have been called.
    }

    @Test
    public void shouldReturnCompany_whenNameIsCorrect() throws Exception {
        //given
        String name = "CorrectName";
        Company company = new Company(name, CORRECT_URL, CORRECT_MAIL);
        //ise Mockito 'when' method here
        when(companyStorageDAOMock.getByName(name)).thenReturn(company);
        //when
        Company resultCompany = companyService.getByName(name);
        //then
        assertEquals(resultCompany, company);
        //Assert that expected company equals result company;
    }

    @Test
    public void shouldSaveCompany_whenNameIsCorrect() throws Exception {
        //given
        String expectedName = "CorrectName";
        Company company = new Company(expectedName, CORRECT_URL, CORRECT_MAIL);
        //when
        companyService.save(company);
        //then
        verify(companyStorageDAOMock).save(argumentCaptor.capture());
        assertEquals(company,argumentCaptor.getValue());
        //Using Mockito verify that storageDAO.save(company) method is called.
        //Using 'argument captor' you can check that company with expectedName is saved.
    }

    @Test
    public void shouldThrowException_whenNameIsTooShort() throws Exception {
        //given
        String shortName = "n";
        Company company = new Company(shortName, CORRECT_URL, CORRECT_MAIL);
        String expectedMessage="Company name is too short. "+
                "It must be longer than 3 character.";
        //when
        try {
            companyService.save(company);
        } catch (ValidationException e){
            assertEquals(expectedMessage,e.getMessage());
        }
        //then
        //Verify that exception is thrown, exception message
        // should be validated as well.
    }

    @Test
    public void shouldThrowException_whenNameIsTooLong() throws Exception {
        //given
        String longName = "Very looooooooooooooooooooooooooooooooooooooooo" +
                "ooooooooooooooooooooooooooooooooooooooooooooooooooooooooo" +
                "ooooooooooooooooooooooooooooooooooooooooooooooooooooooooo" +
                "ooooooooooooooooooooooooooooooooooooooooooooooooooooooooo" +
                "ooooooooooooooooooooooooooooooooooooooooooooooooooooooooo" +
                "ooooooooooooooooooooooooooooooooooooooooooooooooooooooooo" +
                "ooooooooooooooooooooooooooooong name";
        Company company = new Company(longName, CORRECT_URL, CORRECT_MAIL);
        String expectedMessage="Company name is too long. "+
        "It must be shooter than 255 character.";
        //when
        try {
            companyService.save(company);
        } catch (ValidationException e){
            assertEquals(expectedMessage,e.getMessage());
        }
        //then

        //Verify that exception is thrown, exception message
        // should be validated as well.
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowException_whenCompanyNameIsTooShort() throws Exception {
        //given
        //when
        companyService.getByName("");
        //then
    }

   /* @Test
    public void checkNameWithRegExp() throws Exception {
        System.out.println("checkNameWithRegExp");
        assertTrue(companyService.checkNameWithRegExp("companyName"));
    }

    @Test
    public void checkMailWithRegExp() throws Exception {
        System.out.println("checkMailWithRegExp");
        assertTrue(companyService.checkMailWithRegExp("order@point.net"));
    }

    @Test
    public void checkUrlWithRegExp() throws Exception {
        System.out.println("checkUrlWithRegExp");
        assertTrue(companyService.checkUrlWithRegExp("http://point.net"));

    }
*/
}