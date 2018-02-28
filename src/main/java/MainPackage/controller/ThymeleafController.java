package MainPackage.controller;

import MainPackage.entity.Company;
import MainPackage.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

/**
 * Created by User on 23.02.2018.
 */
@Controller
@RequestMapping("/thymeleafController")
public class ThymeleafController {
    private CompanyService companyService;

    public ThymeleafController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RequestMapping("/index")
    public String index(Model model) {
        return "index";
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
        Collection<Company> list=companyService.getAll();
        model.addAttribute("allCompaniesList",list);
        return "allCompanies";
    }
    @RequestMapping("/getByName")
    public String getByName(
            @RequestParam(value="xxx", required=false, defaultValue="World")String name, Model model) {
        Company company = companyService.getByName(name);
        String id = String.valueOf(company.getCompanyId());
        String url = company.getCompanyUrl();
        String email = company.getCompanyMail();
        String date = String.valueOf(company.getLastDate());

        model.addAttribute("name", name);
        model.addAttribute("id", id);
        model.addAttribute("url", url);
        model.addAttribute("email", email);
        model.addAttribute("date", date);

        return "getByName";
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
