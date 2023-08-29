package ru.kopnin.bankservice.integration;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.CassandraContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.org.hamcrest.Matchers;
import ru.kopnin.bankservice.DTO.ClientDTO;
import ru.kopnin.bankservice.models.postgres.Client;
import ru.kopnin.bankservice.repositories.postgres.ClientRepositories;
import ru.kopnin.bankservice.repositories.postgres.LimitsRepositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@ContextConfiguration(initializers = {IntegrationTest.Initializer.class})
@Testcontainers
@AutoConfigureMockMvc
public class IntegrationTest {
    private static final String KEYSPACE_NAME = "test";
    @Autowired
    private ClientRepositories clientRepositories;
    private LimitsRepositories limitsRepositories;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15.3")
            .withDatabaseName("mydb")
            .withUsername("myuser")
            .withPassword("mypass")
            .withInitScript("db.sql");


    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword(),
                    "spring.liquibase.enabled=false"
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
    @Container
    private static final CassandraContainer cassandra = (CassandraContainer) new CassandraContainer("cassandra:3.11.2")
            .withExposedPorts(9042);

    @BeforeAll
    static void setupCassandraConnectionProperties() {
        System.setProperty("spring.data.cassandra.keyspace-name", KEYSPACE_NAME);
        System.setProperty("spring.data.cassandra.contact-points", cassandra.getContainerIpAddress());
        System.setProperty("spring.data.cassandra.port", String.valueOf(cassandra.getMappedPort(9042)));

        createKeyspace(cassandra.getCluster());
    }

    static void createKeyspace(Cluster cluster) {
        try(Session session = cluster.connect()) {
            session.execute("CREATE KEYSPACE IF NOT EXISTS " + KEYSPACE_NAME + " WITH replication = \n" +
                    "{'class':'SimpleStrategy','replication_factor':'1'};");
        }
    }

    @Test
    @Transactional
    public void clientCountShouldBeCorrect() {
        long count = clientRepositories.count();
        assertEquals(3, count);
    }
    @Test
    public void getAllClient() throws Exception{
        mockMvc
                .perform(get("/clients"))
                .andDo(print())
                .andExpect(jsonPath("$",hasSize(3)));
    }
    @Test
    public void shouldAddClient() throws Exception{
        ClientDTO mockClient  = new ClientDTO();
        mockClient.setBankAccountNumber("0000000004");

        System.out.println(objectMapper.writeValueAsString(mockClient));

        mockMvc.
                perform(post("/clients/add")
                        .content(objectMapper.writeValueAsString(mockClient))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
