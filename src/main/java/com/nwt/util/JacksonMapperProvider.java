package com.nwt.util;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * Created by glasshark on 06-Apr-15.
 */
@Provider
@Produces (MediaType.APPLICATION_JSON)
public class JacksonMapperProvider implements ContextResolver<ObjectMapper>
{

    public static ObjectMapper getMapper()
    {
        ObjectMapper mapper = new ObjectMapper();

        mapper.registerModule(new Hibernate4Module());
        AnnotationIntrospector jackson = new JacksonAnnotationIntrospector();
        mapper.setAnnotationIntrospector(jackson);
        mapper.disable(MapperFeature.USE_GETTERS_AS_SETTERS);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper;
    }

    @Override
    public ObjectMapper getContext(Class<?> aClass)
    {
        return JacksonMapperProvider.getMapper();
    }
}
