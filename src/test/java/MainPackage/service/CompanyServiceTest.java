package MainPackage.service;

import MainPackage.dao.CompanyStorageDAO;
import MainPackage.dao.ICompanyStorageDAO;
import org.junit.*;

import static org.junit.Assert.*;

public class CompanyServiceTest {

    private CompanyService companyService;

    public CompanyServiceTest() {
    }

    @Before
    public void setUp() throws Exception {
        companyService = new CompanyService(new CompanyStorageDAO());
    }

    @After
    public void tearDown() throws Exception {
        companyService=null;
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

}