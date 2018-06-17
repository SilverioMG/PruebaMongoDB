package net.atopecode.pruebamongodb;

import net.atopecode.pruebamongodb.config.ApplicationConfig;
import net.atopecode.pruebamongodb.model.Persona;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

public class Application {

    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        MongoTemplate mongoTemplate = context.getBean(MongoTemplate.class);

        try{
            Persona persona = new Persona("Very");
            mongoTemplate.save(persona);
        }
        catch(Exception ex){
            System.out.println(ex.getStackTrace());
        }
    }
}
