package org.hotel.injector;

import com.google.common.reflect.ClassPath;
import com.google.inject.AbstractModule;
import org.hotel.dao.*;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

public class MainInjector extends AbstractModule {
    private final String daoPackageName = "org.hotel.dao";
    @Override
    protected void configure() {
        try {
            Set<Class> classes = ClassPath.from(ClassLoader.getSystemClassLoader())
                    .getAllClasses()
                    .stream()
                    .filter(clazz -> clazz.getPackageName().equalsIgnoreCase(daoPackageName))
                    .map(clazz -> clazz.load())
                    .collect(Collectors.toSet());

            for(Class clazz: classes)
                bind(clazz).toConstructor(clazz.getConstructor());

        } catch (NoSuchMethodException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
