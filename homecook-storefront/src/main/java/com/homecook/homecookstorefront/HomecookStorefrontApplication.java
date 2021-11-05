package com.homecook.homecookstorefront;

import com.homecook.homecookentity.repository.impl.DefaultCustomJpaRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.homecook.*")
@EnableJpaRepositories(basePackages = "com.homecook.homecookentity.repository", repositoryBaseClass = DefaultCustomJpaRepository.class)
@EntityScan(basePackages = "com.homecook.homecookentity.*")
public class HomecookStorefrontApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(HomecookStorefrontApplication.class, args);
    }

}
