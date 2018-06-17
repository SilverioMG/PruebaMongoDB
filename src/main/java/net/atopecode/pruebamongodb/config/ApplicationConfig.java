package net.atopecode.pruebamongodb.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import java.net.UnknownHostException;

@Configuration
@ComponentScan("net.atopecode.pruebamongodb")
public class ApplicationConfig {

    public static String getDataBaseName(){
        return "local";
    }

    public static String getServerBD(){
        return "localhost";
    }

    public static UserCredentials getUserCredentials(){
        UserCredentials userCredentials = new UserCredentials("testuser", "testuser");
        return userCredentials;
    }

    /*
     * Use the standard Mongo driver API to create a com.mongodb.MongoClient instance.
     */
    @Bean
    public MongoClient mongoClient() throws UnknownHostException {
        return new MongoClient("localhost");
    }

    /*
     * Factory bean that creates the com.mongodb.MongoClient instance
     */
    @Bean
    public MongoClientFactoryBean mongoClientFactoryBean() {
        MongoClientFactoryBean factoryBean = new MongoClientFactoryBean();
        factoryBean.setHost(getServerBD());
        MongoCredential mongoCredential = MongoCredential.createCredential("testuser", getDataBaseName(), "testuser".toCharArray());
        factoryBean.setCredentials(new MongoCredential[]{ mongoCredential });
        return factoryBean;
    }

    public @Bean MongoDbFactory mongoDbFactory() throws UnknownHostException {
        return new SimpleMongoDbFactory(new MongoClient(), getDataBaseName());
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        //TODO... Ver como se añaden las credenciales y como se hace para que la B.D. no admita conexiones anónimas.
        //MongoTemplate mongoTemplate = new MongoTemplate(mongoClient(), getDataBaseName(), getUserCredentials());
        return mongoTemplate;
    }
}
