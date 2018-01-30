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
    @PostMapping("company")
    public ResponseEntity<Void> post(@RequestBody Company company, UriComponentsBuilder builder) {
        boolean flag = companyService.post(company);
        if (flag == false) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/company/{id}").buildAndExpand(company.getCompany_id()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    @PutMapping("company")
    public ResponseEntity<Company> patch(@RequestBody Company company) {
        companyService.patch(company);
        return new ResponseEntity<Company>(company, HttpStatus.OK);
    }
    @DeleteMapping("company/{name}")
    public ResponseEntity<Void> delete(@PathVariable("name") String name) {
        companyService.delete(name);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
