package MainPackage.controller;

import MainPackage.entity.Company;
import MainPackage.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        companyService.save(new Company("point","www.point.net","order@point.net"));
        companyService.save(new Company("epam","www.epam.net","order@epam.net"));
        companyService.save(new Company("global","www.global.net","order@global.net"));
        this.companyService = companyService;
    }

    @GetMapping("company/{name}")
    public ResponseEntity<Company> getCompanyByName(@PathVariable("name") String name) {
        Company company = companyService.getByName(name);
        return new ResponseEntity<Company>(company, HttpStatus.OK);
    }
    @GetMapping("company")
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> list = (List<Company>) companyService.getAll();
        return new ResponseEntity<List<Company>>(list, HttpStatus.OK);
    }
    @PostMapping("company")
    public ResponseEntity<Void> post(@RequestBody Company company) {
        companyService.save(company);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
    @PutMapping("company")
    public ResponseEntity<Company> patch(@RequestBody Company company) {
        companyService.update(company);
        return new ResponseEntity<Company>(company, HttpStatus.OK);
    }
    @DeleteMapping("company/{name}")
    public ResponseEntity<Void> delete(@PathVariable("name") String name) {
        companyService.delete(name);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
