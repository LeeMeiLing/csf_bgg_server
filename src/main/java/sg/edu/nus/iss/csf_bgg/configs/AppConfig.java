package sg.edu.nus.iss.csf_bgg.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class AppConfig {

    @Bean
    public WebMvcConfigurer configureCors() {
        return new EnableCors("/api/*", "*");
    }
    
    @Value("${spring.datasource.mongo.url}")
    private String mongoUrl;
    
    @Bean
    public MongoTemplate createMongoTemplate() {
        MongoClient client = MongoClients.create(mongoUrl);
        return new MongoTemplate(client, "bgg");
    }

    
}
