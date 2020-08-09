package kganesh1795.security_tutorial.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {
	private String uri;
	private String db;

	@Override
	protected String getDatabaseName() {
		if (this.uri == null) {
			this.uri = System.getenv("MONGODB_URI");
			if (this.uri == null) {
				this.uri = "mongodb://localhost/security_tutorial";
			}
			this.db = this.uri.substring(uri.lastIndexOf("/") + 1);
		}
		return this.db;
	}

	@Override
	public MongoClient mongoClient() {
		this.getDatabaseName();
		MongoClient mc = MongoClients.create(this.uri);

			
		return mc;
	}
}