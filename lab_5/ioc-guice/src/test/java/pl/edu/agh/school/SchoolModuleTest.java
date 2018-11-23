package pl.edu.agh.school;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.edu.agh.logger.Logger;
import pl.edu.agh.school.guice.SchoolModule;


@RunWith(MockitoJUnitRunner.class)
public class SchoolModuleTest {

    private Injector injector;

    @Before
    public void setUp() {
        this.injector = Guice.createInjector(new SchoolModule());
    }

    @Test
    public void testLoggerSingleton() {
        Logger logger = injector.getInstance(Logger.class);
        assertEquals(logger, injector.getInstance(Logger.class));
    }


}
