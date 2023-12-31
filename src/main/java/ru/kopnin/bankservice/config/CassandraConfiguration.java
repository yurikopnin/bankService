package ru.kopnin.bankservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories("ru.kopnin.bankservice.repositories.cassandra")
public class CassandraConfiguration extends AbstractCassandraConfiguration {

 private CassandraConfigurationProperties cassandraConfigurationProperties;
@Autowired
    public CassandraConfiguration(CassandraConfigurationProperties cassandraConfigurationProperties) {
        this.cassandraConfigurationProperties = cassandraConfigurationProperties;
    }

    @Override
    protected String getKeyspaceName() {
        return cassandraConfigurationProperties.getKeySpace();
    }

    @Override
    public String getContactPoints() {
        return cassandraConfigurationProperties.getContactPoints();
    }

    @Override
    public int getPort() {
        return cassandraConfigurationProperties.getPort();
    }

    public String getDataCenter() {
        return cassandraConfigurationProperties.getDataCenter();
    }


}
