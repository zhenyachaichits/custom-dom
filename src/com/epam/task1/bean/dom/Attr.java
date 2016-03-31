package com.epam.task1.bean.dom;

import com.epam.task1.bean.dom.collection.NodeList;
import com.epam.task1.bean.dom.type.NodeType;

import javax.naming.OperationNotSupportedException;

/**
 * Created by Zheny Chaichits on 11/24/2015.
 */
public class Attr implements Node {

    private String nodeName;
    private String nodeValue;

    public Attr() {
    }

    public Attr(String nodeName, String nodeValue) {
        this.nodeName = nodeName;
        this.nodeValue = nodeValue;
    }

    public void setNodeName(String name) {
        nodeName = name;
    }

    public void setNodeValue(String value) {
        nodeValue = value;
    }

    @Override
    public NodeType getNodeType() {
        return NodeType.ATTR;
    }

    @Override
    public String getNodeName() {
        return nodeName;
    }

    @Override
    public String getNodeValue() {
        return nodeValue;
    }

    @Override
    public NodeList getChildNodes() throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

    @Override
    public NodeList getAttributes() throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Attr attr = (Attr) obj;

        if (nodeName != null ? !nodeName.equals(attr.nodeName) : attr.nodeName != null) {
            return false;
        }
        return !(nodeValue != null ? !nodeValue.equals(attr.nodeValue) : attr.nodeValue != null);

    }

    @Override
    public int hashCode() {
        int result = nodeName != null ? nodeName.hashCode() : 0;
        result = 31 * result + (nodeValue != null ? nodeValue.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getName() + " @ ");
        builder.append(", nodeName: " + nodeName);
        builder.append(", nodeValue: " + nodeValue);
        return builder.toString();
    }

}
