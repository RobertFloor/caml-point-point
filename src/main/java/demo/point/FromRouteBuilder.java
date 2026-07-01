package demo.point;

import demo.model.Person;
import jakarta.xml.bind.JAXBContext;
import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FromRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        JaxbDataFormat jaxb = new JaxbDataFormat(JAXBContext.newInstance(Person.class));
        Logger logger = LoggerFactory.getLogger(FromRouteBuilder.class);


        from("platform-http:/api/data?httpMethodRestrict=POST")
                .unmarshal(jaxb)

                .marshal().json(JsonLibrary.Jackson)
                .to(ExchangePattern.InOnly, "kafka:person");
    }
}
