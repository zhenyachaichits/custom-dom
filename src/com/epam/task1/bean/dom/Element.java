package com.epam.task1.bean.dom;

import com.epam.task1.bean.dom.collection.NodeList;
import com.epam.task1.bean.dom.type.NodeType;

import javax.naming.OperationNotSupportedException;

/**
 * Created by Zheny Chaichits on 11/24/2015.
 */
public class Element implements Node {

    private String nodeName;
    private String nodeValue;
    private NodeList childNodes;
    private NodeList attributes;
    private int nestingLevel;

    public Element() {
        childNodes = new NodeList();
        attributes = new NodeList();
    }

    public Element(String tagName) {
        this.nodeName = tagName;
        childNodes = new NodeList();
        attributes = new NodeList();
    }

    public Node appendChild(Node nodeToAdd) {
        childNodes.add(nodeToAdd);
        return nodeToAdd;
    }

    public void setNodeValue(String nodeValue) {
        this.nodeValue = nodeValue;
    }

    public void setAttribute(Attr attr) {
        attributes.add(attr);
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public boolean hasAttributes() {
        return !attributes.isEmpty();
    }

    public int getNestingLevel() {
        return nestingLevel;
    }

    public void setNestingLevel(int nestingLevel) {
        this.nestingLevel = nestingLevel;
    }

    @Override
    public NodeType getNodeType() {
        return NodeType.ELEMENT;
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
        return childNodes;
    }

    @Override
    public NodeList getAttributes() throws OperationNotSupportedException {
        return attributes;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Element element = (Element) obj;

        if (nestingLevel != element.nestingLevel) {
            return false;
        }
        if (!nodeName.equals(element.nodeName)) {
            return false;
        }
        if (nodeValue != null ? !nodeValue.equals(element.nodeValue) : element.nodeValue != null) {
            return false;
        }
        if (childNodes != null ? !childNodes.equals(element.childNodes) : element.childNodes != null) {
            return false;
        }
        return !(attributes != null ? !attributes.equals(element.attributes) : element.attributes != null);

    }

    @Override
    public int hashCode() {
        int result = nodeName != null ? nodeName.hashCode() : 0;
        result = 31 * result + (nodeValue != null ? nodeValue.hashCode() : 0);
        result = 31 * result + (childNodes != null ? childNodes.hashCode() : 0);
        result = 31 * result + (attributes != null ? attributes.hashCode() : 0);
        result = 31 * result + nestingLevel;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getName() + " @ ");
        builder.append(", nodeName: " + nodeName);
        builder.append(", nodeValue: " + nodeValue);
        builder.append(", childNodes: " + childNodes);
        builder.append(", attributes: " + attributes);
        return builder.toString();
    }

}
