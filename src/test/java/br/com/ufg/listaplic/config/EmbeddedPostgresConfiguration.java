package br.com.ufg.listaplic.config;

import org.apache.commons.lang3.StringUtils;
import org.postgresql.ds.PGPoolingDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.context.event.EventListener;
import ru.yandex.qatools.embed.postgresql.PostgresExecutable;
import ru.yandex.qatools.embed.postgresql.PostgresProcess;
import ru.yandex.qatools.embed.postgresql.PostgresStarter;
import ru.yandex.qatools.embed.postgresql.config.AbstractPostgresConfig;
import ru.yandex.qatools.embed.postgresql.config.PostgresConfig;
import ru.yandex.qatools.embed.postgresql.distribution.Version;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.UUID;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.isNumeric;

@Profile("test")
@Configuration
@ConditionalOnExpression(value="${spring.embeddedpostgres.enable}")
@Scope("singleton")
@Primary
public class EmbeddedPostgresConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmbeddedPostgresConfiguration.class);

    @Value("${spring.embeddedpostgres.port}")
    private String port;

    @Value("${spring.embeddedpostgres.database}")
    private String database;

    @Value("${spring.embeddedpostgres.datadir}")
    private String dataDir;

    private PostgresProcess globalPostgresInstance;

    @Bean(destroyMethod = "stop")
    public PostgresProcess postgresProcess() throws IOException {
        if(!isNumeric(port)) {
            LOGGER.warn("Property spring.embeddedpostgres.port is not a number {}", port);
            throw new IllegalStateException(format("To start a embedded postgres the property spring.embeddedpostgres.port is not a number %s", port));
        }

        AbstractPostgresConfig.Storage storage = null;
        if(StringUtils.isNotBlank(dataDir)) {
            storage = new AbstractPostgresConfig.Storage(database, format("%s/tmp/%s", dataDir, UUID.randomUUID()));
        } else {
            storage = new AbstractPostgresConfig.Storage(database);
        }

        PostgresConfig postgresConfig = new PostgresConfig(
                Version.V9_6_11,
                new AbstractPostgresConfig.Net("localhost", Integer.valueOf(port)),
                storage,
                new AbstractPostgresConfig.Timeout()
        );

        PostgresStarter<PostgresExecutable, PostgresProcess> runtime = PostgresStarter.getDefaultInstance();
        PostgresExecutable exec = runtime.prepare(postgresConfig);
        globalPostgresInstance = exec.start();

        return globalPostgresInstance;
    }

    @Bean(destroyMethod = "close")
    @DependsOn("postgresProcess")
    DataSource dataSource(PostgresProcess postgresProcess) {
        PGPoolingDataSource dataSource = new PGPoolingDataSource();

        PostgresConfig postgresConfig = postgresProcess.getConfig();
        dataSource.setPortNumber(postgresConfig.net().port());
        dataSource.setServerName(postgresConfig.net().host());
        dataSource.setDatabaseName(postgresConfig.storage().dbName());

        return dataSource;
    }

    @EventListener({ ContextStoppedEvent.class, ContextClosedEvent.class })
    public void tearDown() {
        if(globalPostgresInstance != null) {
            globalPostgresInstance.stop();
        }
    }

}