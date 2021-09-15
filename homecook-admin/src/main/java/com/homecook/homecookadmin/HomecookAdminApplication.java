package com.homecook.homecookadmin;

import com.homecook.homecookdomain.repository.impl.DefaultCustomJpaRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.homecook.*")
@EnableJpaRepositories(basePackages = "com.homecook.homecookdomain.repository", repositoryBaseClass = DefaultCustomJpaRepository.class)
@EntityScan(basePackages = "com.homecook.homecookdomain.model")
public class HomecookAdminApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(HomecookAdminApplication.class, args);
    }
}
