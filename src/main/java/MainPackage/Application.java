package MainPackage;

import MainPackage.service.CompanyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


/*@SpringBootApplication
public class Application implements CommandLineRunner{
    private final CompanyService companyService;

    public Application(CompanyService companyService) {
        this.companyService = companyService;
    }

    public static void main(String[] args) throws Exception{
        SpringApplication app = new SpringApplication(Application.class);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        companyService.run(args);
    }*/
}
