package com.nwt.util;

import org.apache.log4j.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * Created by glasshark on 28-Mar-15.
 */

public class LoggingFactory
{
    @Produces
    @Log
    public Logger produceLogger(InjectionPoint injectionPoint)
    {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }
}
