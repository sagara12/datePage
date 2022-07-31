package com.datePage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.datePage.request"}) // com.datePage.request.domain 하위에 있는 @Entity 클래스 scan*/
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.datePage.repository"}) // com.datePage.datePage.repository 하위에 있는 jpaRepository를 상속한 repository scan*/
public class DatePageApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatePageApplication.class, args);
	}

}
