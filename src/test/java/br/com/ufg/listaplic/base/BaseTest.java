package br.com.ufg.listaplic.base;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public abstract class BaseTest {

    @BeforeClass
    public static void init () {
        FixtureFactoryLoader.loadTemplates("br.com.ufg.listaplic.template");
    }

}
