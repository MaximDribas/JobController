package MainPackage;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner{
    private final Main mainService;

    public Application(Main mainService) {
        this.mainService = mainService;
    }

    public static void main(String[] args) throws Exception{
        SpringApplication app = new SpringApplication(Application.class);
        app.run(args);
    }

    @Override
    public void run(String... strings) throws Exception {
        mainService.run();
    }
}
