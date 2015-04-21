package com.nwt.util;

import org.apache.log4j.Logger;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

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
}