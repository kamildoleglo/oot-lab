package pl.edu.agh.school.guice;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;
import pl.edu.agh.logger.ConsoleMessageSerializer;
import pl.edu.agh.logger.FileMessageSerializer;
import pl.edu.agh.logger.IMessageSerializer;
import pl.edu.agh.school.persistence.IPersistenceManager;
import pl.edu.agh.school.persistence.SerializablePersistenceManager;

public class SchoolModule extends AbstractModule {
    @Override
    public void configure(){
        bind(IPersistenceManager.class).to(SerializablePersistenceManager.class);
        bind(String.class)
                .annotatedWith(Names.named("teachersStorageFileName"))
                .toInstance("guice-teachers.dat");
        bind(String.class)
                .annotatedWith(Names.named("classStorageFileName"))
                .toInstance("guice-classes.dat");

        Multibinder<IMessageSerializer> loggers
                = Multibinder.newSetBinder(binder(), IMessageSerializer.class);

        loggers.addBinding().to(FileMessageSerializer.class);
        loggers.addBinding().to(ConsoleMessageSerializer.class);

        //bind(IMessageSerializer.class).to(FileMessageSerializer.class);

        bind(String.class)
                .annotatedWith(Names.named("logFilename"))
                .toInstance("persistence.log");
    }
}
