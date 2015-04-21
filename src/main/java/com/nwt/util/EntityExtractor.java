package com.nwt.util;

/**
 * Created by glasshark on 18-Apr-15.
 */
public interface EntityExtractor<T, C>
{
    T extract(C c);
}
