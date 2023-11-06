package com.capstone.vectorvisioninstitute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//TODO: NOV 06 2023 - install sonarLint @38:00

@SpringBootApplication
//Annotations used for enabling the JPA functionality.
@EnableJpaRepositories("com.capstone.vectorvisioninstitute.repository")
@EntityScan("com.capstone.vectorvisioninstitute.model")
//Enable Auditing
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class VectorvisioninstituteApplication {

	public static void main(String[] args) {
		SpringApplication.run(VectorvisioninstituteApplication.class, args);
	}

}
