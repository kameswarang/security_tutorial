package kganesh1795.security_tutorial.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {
    @Value("${MONGODB_URI}")
    private String uri;
    
    private String db;
    
	@Override
	protected String getDatabaseName() {
	    if(this.db == null) {
	        this.db = this.uri.substring(uri.lastIndexOf("/")+1);
	    }
		return this.db;
	}

	@Override
	public MongoClient mongoClient() {
		MongoClient mc = MongoClients.create(this.uri);
		mc.getDatabase(this.getDatabaseName()).drop();
		
		return mc;
	}
}