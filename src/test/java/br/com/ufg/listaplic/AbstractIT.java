package br.com.ufg.listaplic;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SqlGroup({
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:setup.sql"})
})
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@ContextConfiguration(classes = {ListAplicApplication.class})
@ComponentScan(basePackages = "br.com.ufg.listaplic")
@PropertySource(value = "classpath:application-test.yaml", ignoreResourceNotFound = true)
public abstract class AbstractIT {

    @BeforeClass
    public static void init() {
        FixtureFactoryLoader.loadTemplates("br.com.ufg.listaplic.template");
    }

}