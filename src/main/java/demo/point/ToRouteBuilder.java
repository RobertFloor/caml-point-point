package demo.point;

import demo.model.Person;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        Logger logger = LoggerFactory.getLogger(ToRouteBuilder.class);


        from("kafka:person")
                .unmarshal().json(JsonLibrary.Jackson, Person.class)
                .marshal().json(JsonLibrary.Jackson)
                .to("http://localhost:8080/quarkus/hello");
//                .process(exchange ->
//                {
//                    Person person = exchange.getIn().getBody(Person.class);
//                    var response = String.format("Order received from %s, born on %s\n", person.getName(), person.getBirthday());
//                    exchange.getMessage().setBody(response);
//                    logger.info(response);
//                })
//                .to("file:output?fileName=orders.txt&fileExist=Append");
    }
}
