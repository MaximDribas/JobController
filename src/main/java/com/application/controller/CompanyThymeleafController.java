package com.application.controller;

import com.application.entity.Company;
import com.application.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@Controller
@RequestMapping()
public class CompanyThymeleafController {
    private CompanyService companyService;

    public CompanyThymeleafController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RequestMapping("/index")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @RequestMapping("/addCompanyForm")
    public String addCompanyForm(Model model) {
        model.addAttribute("companyObject", new Company());
        return "addCompanyForm";
    }

    @RequestMapping(value = "/addCompanyResult", method = RequestMethod.POST)
    public String addCompanyResult(@ModelAttribute Company company, Model model) {
        companyService.save(company);
        model.addAttribute("companyName", company.getCompanyName());
        return "addCompanyResult";
    }

    @RequestMapping("/allCompanies")
    public String allCompanies(Model model) {
        Collection<Company> list = companyService.getAll();
        model.addAttribute("allCompaniesList", list);
        return "allCompanies";
    }

    @RequestMapping("/getByNameForm")
    public String getByNameForm(Model model) {
        model.addAttribute("companyObject", new Company());
        return "getByNameForm";
    }

    @RequestMapping("/getByNameResult")
    public String getByNameResult(@ModelAttribute Company companyObject, Model model) {
        String name = companyObject.getCompanyName();
        Company company = companyService.getByName(name);
        model.addAttribute("companyObject", company);
        return "getByNameResult";
    }

    @RequestMapping("/updateCompanyForm")
    public String updateCompanyForm(Model model) {
        model.addAttribute("companyObject", new Company());
        return "updateCompanyForm";
    }

    @RequestMapping(value = "/updateCompanyResult", method = RequestMethod.POST)
    public String updateCompanyResult(@ModelAttribute Company company, Model model) {
        companyService.update(company);
        model.addAttribute("companyName", company.getCompanyName());
        return "updateCompanyResult";
    }

    @RequestMapping("/deleteCompanyForm")
    public String deleteCompanyForm(Model model) {
        model.addAttribute("companyObject", new Company());
        return "deleteCompanyForm";
    }

    @RequestMapping(value = "/deleteCompanyResult", method = RequestMethod.POST)
    public String deleteCompanyResult(@ModelAttribute Company companyObject, Model model) {
        String companyName = companyObject.getCompanyName();
        companyService.delete(companyName);
        model.addAttribute("companyName", companyName);
        return "deleteCompanyResult";
    }
}
