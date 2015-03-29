package com.nwt.util;

import com.nwt.entities.GenId;
import org.apache.log4j.Logger;

import javax.persistence.*;

/**
 * Created by glasshark on 27-Mar-15.
 */
public class LifeCycleListener
{
    private Logger logger = Logger.getLogger(LifeCycleListener.class);

    @PostPersist
    void postPersist(Object object)
    {
        logger.debug(object.getClass().getName() + " {" + object.toString() + "} persisted.");
    }

    @PostUpdate
    void postUpdate(Object object)
    {
        logger.debug(object.getClass().getName() + " {" + object.toString() + "} updated.");
    }

    @PostRemove
    void postRemove(Object object)
    {
        logger.debug(object.getClass().getName() + " {" + object.toString() + "} removed.");
    }

    private boolean isGenId(Object object)
    {
        return object instanceof GenId;
    }
}