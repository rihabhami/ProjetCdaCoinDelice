package fr.eql.ai111.projet3.coindelice.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"fr.eql.ai111.projet3.coindelice.customer","fr.eql.ai111.projet3.coindelice.library" })
@EnableJpaRepositories(value = "fr.eql.ai111.projet3.coindelice.library.repository")
@EntityScan(value = "fr.eql.ai111.projet3.coindelice.library.model")
public class CustomerApplication {

	public static void main(String[] args) {

		SpringApplication.run(CustomerApplication.class, args);
	}

}
