package kr.co.sist.etmarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EtmarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(EtmarketApplication.class, args);
	}

}
