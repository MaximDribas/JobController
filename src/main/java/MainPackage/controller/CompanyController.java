package MainPackage.controller;

import MainPackage.entity.Company;
import MainPackage.service.ICompanyService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("user")
public class CompanyController {

    private ICompanyService companyService;

    public CompanyController(ICompanyService companyService) {
        companyService.post(new Company("point","www.point.net","order@point.net"));
        companyService.post(new Company("epam","www.epam.net","order@epam.net"));
        companyService.post(new Company("global","www.global.net","order@global.net"));
        this.companyService = companyService;
    }

    @GetMapping("company/{name}")
    public ResponseEntity<Company> getCompanyByName(@PathVariable("name") String name) {
        Company company = companyService.getByName(name);
        return new ResponseEntity<Company>(company, HttpStatus.OK);
    }
    @GetMapping("companies")
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> list = (List<Company>) companyService.getAll();
        return new ResponseEntity<List<Company>>(list, HttpStatus.OK);
    }
    @PostMapping("companyPost")
    public ResponseEntity<Void> post(@RequestBody Company company) {
        companyService.post(company);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
    @PutMapping("companyPut")
    public ResponseEntity<Company> patch(@RequestBody Company company) {
        companyService.patch(company);
        return new ResponseEntity<Company>(company, HttpStatus.OK);
    }
    @DeleteMapping("companyDel/{name}")
    public ResponseEntity<Void> delete(@PathVariable("name") String name) {
        companyService.delete(name);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
