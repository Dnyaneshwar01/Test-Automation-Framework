package com.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtility {

    //Due to singleton Design --> We mark constructor as private
    private LoggerUtility() {
    }

    public static Logger getLogger(Class<?> clazz) {
        Logger logger = null;
        if (logger == null) {
            logger = LogManager.getLogger(clazz);
        }
        return logger;
    }
}
