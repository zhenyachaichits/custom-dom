package com.epam.task1.bean.domain;

import com.epam.task1.bean.domain.type.DomainType;

/**
 * Created by Zheny Chaichits on 12/1/2015.
 */
public class Domain {
    private String name;
    private String attributes;
    private String content;
    private DomainType domainType;

    public Domain(String name, DomainType domainType) {
        this.name = name;
        this.domainType = domainType;
    }

    public Domain(String name, String attributes, DomainType domainType) {
        this.name = name;
        this.attributes = attributes;
        this.domainType = domainType;
    }

    public Domain(String name, String attributes, String content, DomainType domainType) {
        this.name = name;
        this.attributes = attributes;
        this.content = content;
        this.domainType = domainType;
    }

    public String getName() {
        return name;
    }

    public String getAttributes() {
        return attributes;
    }

    public String getContent() {
        return content;
    }

    public DomainType getDomainType() {
        return domainType;
    }

    public boolean hasAttributes() {
        if (attributes != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Domain domain = (Domain) obj;

        if (!name.equals(domain.name)) return false;
        if (attributes != null ? !attributes.equals(domain.attributes) : domain.attributes != null) return false;
        if (content != null ? !content.equals(domain.content) : domain.content != null) return false;
        return domainType == domain.domainType;

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (attributes != null ? attributes.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (domainType != null ? domainType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() + " @ name: " + name
                + ", type: " + domainType + ", content: " + content;
    }
}
