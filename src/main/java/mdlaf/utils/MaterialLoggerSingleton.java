package mdlaf.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class MaterialLoggerSingleton {

    static {}

    private static MaterialLoggerSingleton SINGLETON;

    public static MaterialLoggerSingleton getInstance() {
        if (SINGLETON == null) {
            SINGLETON = new MaterialLoggerSingleton();
        }
        return SINGLETON;
    }

    private Map<Class, Logger> cacheLoggers = new HashMap<>();

    private MaterialLoggerSingleton() { }

    public void debug(Class clazz, String message){
        this.commonGuardMethod(clazz, message);
        Logger logger = this.getLoggerByClass(clazz);
        logger.debug(message);
    }

    public void info(Class clazz, String message){
        this.commonGuardMethod(clazz, message);
        Logger logger = this.getLoggerByClass(clazz);
        logger.info(message);
    }

    public void error(Class clazz, String message){
        this.commonGuardMethod(clazz, message);
        Logger logger = this.getLoggerByClass(clazz);
        logger.error(message);
    }

    private void commonGuardMethod(Class clazz, String message){
        if(clazz == null || message == null){
            //TODO make a good message
            throw new IllegalArgumentException("class or message null");
        }
    }

    private Logger getLoggerByClass(Class clazz){
        if(clazz == null){
            throw new IllegalArgumentException("Class null");
        }
        if(cacheLoggers.containsKey(clazz)){
            return cacheLoggers.get(clazz);
        }
        Logger logger = LoggerFactory.getLogger(clazz);
        cacheLoggers.put(clazz, logger);
        return logger;
    }
}
