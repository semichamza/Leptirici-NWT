package com.nwt.util;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Almin on 03-Apr-15.
 */
public class LinkAdapter extends XmlAdapter<LinkJaxb, Link>
{
    public LinkAdapter()
    {
    }

    public Link unmarshal(LinkJaxb v)
    {
        Link.Builder builder = Link.fromUri(v.getUri());
        for (Map.Entry<QName, Object> entry : v.getParams().entrySet())
        {
            builder.param(entry.getKey().getLocalPart(), entry.getValue().toString());
        }
        return builder.build();
    }

    public LinkJaxb marshal(Link v)
    {
        Map<QName, Object> params = new HashMap<>();
        for (Map.Entry<String, String> entry : v.getParams().entrySet())
        {
            params.put(new QName("", entry.getKey()), entry.getValue());
        }
        return new LinkJaxb(v.getUri(), params);
    }
}