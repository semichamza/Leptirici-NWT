package com.nwt.util;

import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.namespace.QName;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Almin on 03-Apr-15.
 */
class LinkJaxb
{
    private URI uri;
    private String rel;
    private String method;
    private Map<QName, Object> params;

    public LinkJaxb()
    {
        this(null, null);
    }

    public LinkJaxb(URI uri)
    {
        this(uri, null);
    }

    public LinkJaxb(URI uri, Map<QName, Object> map)
    {
        this.uri = uri;
        this.params = map != null ? map : new HashMap<QName, Object>();
    }

    @XmlAttribute (name = "href")
    public URI getUri()
    {
        return uri;
    }

    public void setUri(URI uri)
    {
        this.uri = uri;
    }

    @XmlAnyAttribute
    public Map<QName, Object> getParams()
    {
        return params;
    }

    public void setParams(Map<QName, Object> params)
    {
        this.params = params;
    }

    public String getMethod()
    {
        return method;
    }

    public void setMethod(String method)
    {
        this.method = method;
    }

    public String getRel()
    {
        return rel;
    }

    public void setRel(String rel)
    {
        this.rel = rel;
    }
}
