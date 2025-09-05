package pro.sky.service_bank_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "pro.sky.service_bank_test.repository")
@EntityScan(basePackages = "pro.sky.service_bank_test.model")
public class ServiceBankTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceBankTestApplication.class, args);
	}

}
