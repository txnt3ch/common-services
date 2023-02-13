package txn.tech.commonservices.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {

    public String getVersion() {
        return version;
    }

    @Value("${app.version}")
    private String version;

}
