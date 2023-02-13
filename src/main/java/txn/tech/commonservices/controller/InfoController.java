package txn.tech.commonservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import txn.tech.commonservices.config.AppConfig;
import txn.tech.commonservices.config.DatabaseConfig;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class InfoController {

    private static final String template = "Hello %s!";
    private final AtomicLong counter = new AtomicLong();
    @Autowired
    private AppConfig appConfig;

    @Autowired
    private DatabaseConfig databaseConfig;

    @GetMapping("/")
    public String getInfo(@RequestParam(value = "name", defaultValue = "World") String name) {
        return appConfig.getVersion()+"-";
    }

    @GetMapping("/database")
    public DatabaseConfig getDatabaseConfig() {
        return databaseConfig;
    }


}
