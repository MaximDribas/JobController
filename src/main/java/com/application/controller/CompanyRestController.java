package com.application.controller;

import com.application.entity.Company;
import com.application.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("company")
public class CompanyRestController {

    private CompanyService companyService;

    public CompanyRestController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("{name}")
    public ResponseEntity<Company> getCompanyByName(@PathVariable("name") String name) {
        Company company = companyService.getByName(name);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> list = (List<Company>) companyService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Void> post(@RequestBody Company company) {
        companyService.save(company);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Company> patch(@RequestBody Company company) {
        companyService.update(company);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @DeleteMapping("{name}")
    public ResponseEntity<Void> delete(@PathVariable("name") String name) {
        companyService.delete(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
