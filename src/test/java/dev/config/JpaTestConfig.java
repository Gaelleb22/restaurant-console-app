package dev.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({JpaConfig.class, JdbcTestConfig.class})
public class JpaTestConfig {

}
