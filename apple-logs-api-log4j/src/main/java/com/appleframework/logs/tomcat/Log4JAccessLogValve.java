package com.appleframework.logs.tomcat;

import org.apache.catalina.valves.AccessLogValve;
import org.apache.log4j.Logger;

/**
 * @author hill.hu
 */
public class Log4JAccessLogValve  extends AccessLogValve {
    private final Logger logger = Logger.getLogger(this.getClass());

    protected static final String info1 ="com.appleframework.logs.tomcat.Log4JAccessLogValve";

    @Override
    public void log(String message) {
         logger.info(message);
    }

    @Override
    public String getInfo()
    {
        return info1;
    }

    @Override
    protected void open()
    {
    }
}
