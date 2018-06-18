package net.atopecode.pruebamongodb.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.sun.security.ntlm.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.UnknownHostException;
import java.rmi.server.ServerCloneException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan("net.atopecode.pruebamongodb")
@EnableMongoRepositories("net.atopecode.pruebamongodb")
public class ApplicationConfig {

    public static String getDataBaseName(){
        return "admin";
    }

    public static String getServerBD(){
        return "localhost";
    }

    public static String getHostBD() { return "127.0.0.1"; }
    public static int getServerPort(){ return 27017; }

    public static UserCredentials getUserCredentials(){
        UserCredentials userCredentials = new UserCredentials("testuser", "testuser");
        return userCredentials;
    }

    public static MongoCredential getMongoCredentials(){
        MongoCredential mongoCredential = MongoCredential.createCredential("myadmin", getDataBaseName(), "myadmin".toCharArray());
        return mongoCredential;
    }

    public static ServerAddress getServerAddress() throws UnknownHostException{
        ServerAddress serverAddress = new ServerAddress(getHostBD(), getServerPort());
        return serverAddress;
    }

    /*
     * Use the standard Mongo driver API to create a com.mongodb.MongoClient instance.
     */
    @Bean(name = "mongoClient")
    public MongoClient mongoClient() throws UnknownHostException {
        //return new MongoClient("localhost");
        List<MongoCredential> credentials = new ArrayList<MongoCredential>();
        credentials.add(getMongoCredentials());
        return new MongoClient(getServerAddress(), credentials);
    }

    /*
     * Factory bean that creates the com.mongodb.MongoClient instance
     */
    @Bean
    public MongoClientFactoryBean mongoClientFactoryBean() {
        MongoClientFactoryBean factoryBean = new MongoClientFactoryBean();
        factoryBean.setHost(getServerBD());
        MongoCredential mongoCredential = getMongoCredentials();
        factoryBean.setCredentials(new MongoCredential[]{ mongoCredential });
        return factoryBean;
    }

    public @Bean MongoDbFactory mongoDbFactory() throws UnknownHostException {
        return new SimpleMongoDbFactory(mongoClient(), getDataBaseName());
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MongoDbFactory mongoDbFactory = mongoDbFactory();
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory);
        //MongoTemplate mongoTemplate1 = new MongoTemplate(mongoClient(), getDataBaseName());
        return mongoTemplate;
    }
}
