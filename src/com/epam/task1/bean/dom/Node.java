package com.epam.task1.bean.dom;


import com.epam.task1.bean.dom.collection.NodeList;
import com.epam.task1.bean.dom.type.NodeType;

import javax.naming.OperationNotSupportedException;

/**
 * Created by Zheny Chaichits on 11/24/2015.
 */
public interface Node {
    NodeType getNodeType();

    String getNodeName();

    String getNodeValue();

    NodeList getChildNodes() throws OperationNotSupportedException;

    NodeList getAttributes() throws OperationNotSupportedException;

}
