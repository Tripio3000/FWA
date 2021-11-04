package edu.school_21.cinema.config;

import edu.school_21.cinema.repositories.UserDaoImpl;
import edu.school_21.cinema.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.sql.DataSource;

@Configuration
@ComponentScan("edu.school_21.cinema")
@PropertySource("../application.properties")
public class SpringConfig {

    @Value("${datasource.driver}")
    private String driverClassName;
    @Value("${datasource.url}")
    private String url;
    @Value("${datasource.usrname}")
    private String userName;
    @Value("${datasoruce.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public UserService userService(UserDaoImpl userDao, PasswordEncoder passwordEncoder) {
        return new UserService(userDao, passwordEncoder);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
