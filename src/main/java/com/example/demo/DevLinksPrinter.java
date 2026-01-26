import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DevLinksPrinter {

    @EventListener(ApplicationReadyEvent.class)
    public void printLinks() {
        String base = "http://localhost:8080";
        System.out.println("Swagger UI: " + base + "/swagger-ui/index.html");
        System.out.println("OpenAPI JSON: " + base + "/v3/api-docs");
        System.out.println("H2 Console: " + base + "/h2-console");
    }
}
