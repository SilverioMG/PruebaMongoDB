package net.atopecode.pruebamongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import net.atopecode.pruebamongodb.config.ApplicationConfig;
import net.atopecode.pruebamongodb.model.Persona;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

public class Application {

    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        MongoTemplate mongoTemplate = context.getBean(MongoTemplate.class);
        MongoClient mongoClient = (MongoClient)context.getBean("mongoClient");

        try{
            MongoDatabase db = mongoClient.getDatabase("local");
            String stringDB = db.toString();
            System.out.println(stringDB);
            db.drop();
            //Persona persona = new Persona("Very");
            //mongoTemplate.save(persona);
        }
        catch(Exception ex){
            System.out.println(ex.getStackTrace());
        }
    }
}
