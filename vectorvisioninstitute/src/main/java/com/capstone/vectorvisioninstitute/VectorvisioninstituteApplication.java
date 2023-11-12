package com.capstone.vectorvisioninstitute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//Author: Ricardo Rivas

@SpringBootApplication
//Annotations used for enabling the JPA functionality.
@EnableJpaRepositories("com.capstone.vectorvisioninstitute.repository")
@EntityScan("com.capstone.vectorvisioninstitute.model")
//Enable Auditing
//TODO: IMPORTANT WHEN TESTING COMMENT OUT THIS LINE OF CODE!
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class VectorvisioninstituteApplication {

	public static void main(String[] args) {
		SpringApplication.run(VectorvisioninstituteApplication.class, args);
	}

}
