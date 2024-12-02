package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// Конфигурационный класс Spring:
@Configuration
@ComponentScan(basePackages = "controllers,services,repositories,entities")
public class AppConfig {
}
