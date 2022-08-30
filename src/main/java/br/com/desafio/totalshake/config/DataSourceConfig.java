package br.com.desafio.totalshake.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


@Configuration
@EnableJpaRepositories(basePackages = "br.com.desafio.totalshake.domain.repository")
@EnableTransactionManagement
public class DataSourceConfig {

    @Autowired
    private Environment environment;

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        final HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        hikariConfig.setJdbcUrl(environment.getProperty("spring.datasource.url"));
        hikariConfig.setUsername(environment.getProperty("spring.datasource.username"));
        hikariConfig.setPassword(environment.getProperty("spring.datasource.password"));
        hikariConfig.setMaximumPoolSize(1);
        hikariConfig.setMinimumIdle(1);
        return new HikariDataSource(hikariConfig);
    }
}
