package io.resttestgen.Database;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.core.env.ConfigurableEnvironment;

import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.Map;
import java.util.Properties;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Main.class);


        application.addInitializers(context -> {
            ConfigurableEnvironment environment = context.getEnvironment();
            MutablePropertySources propertySources = environment.getPropertySources();

            Properties properties = new Properties();


            String dbIp = System.getenv("DOCKER_IP_HOST");

            properties.setProperty("db.ip", dbIp != null ? dbIp : "localhost");

            propertySources.addFirst(new PropertiesPropertySource("Personalized property", properties));
        });
        application.run(args);
    }
}
