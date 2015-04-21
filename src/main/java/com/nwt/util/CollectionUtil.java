package com.nwt.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by glasshark on 18-Apr-15.
 */
public final class CollectionUtil
{
    private CollectionUtil()
    {
    }

    public static <T, C> List<T> extract(List<C> sourceList, EntityExtractor<T, C> extractor)
    {
        List<T> result = new ArrayList<T>();
        for (C compoundEntity : sourceList)
            result.add(extractor.extract(compoundEntity));
        return result;
    }
}
