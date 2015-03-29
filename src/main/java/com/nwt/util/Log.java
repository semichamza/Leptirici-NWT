package com.nwt.util;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by glasshark on 29-Mar-15.
 */
@Qualifier
@Target ({FIELD, TYPE, METHOD})
@Retention (RUNTIME)
public @interface Log
{
}
