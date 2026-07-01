package demo.client;

import demo.model.Person;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import org.jboss.logging.Logger;

@Path("/quarkus/hello")
public class QuarkusEndpoint {

    private static final Logger logger = Logger.getLogger(QuarkusEndpoint.class);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void receiver(Person person) {
        logger.info("Received File content on Rest API, mocking another application\n" + person.getName() + " born on: " + person.getBirthday());
    }
}
