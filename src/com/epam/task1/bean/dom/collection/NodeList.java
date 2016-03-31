package com.epam.task1.bean.dom.collection;

import com.epam.task1.bean.dom.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zheny Chaichits on 11/29/2015.
 */
public class NodeList {

    private List<Node> nodeList;

    public NodeList(){
        nodeList = new ArrayList<>();
    }

    public void add(Node element){
        nodeList.add(element);
    }

    public boolean isEmpty(){
        return nodeList.isEmpty();
    }

    public int size(){
        return nodeList.size();
    }

    public Node item(int i){
        return nodeList.get(i);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getName() + " @ ");
        for(int i = 0; i < nodeList.size(); i++) {
            builder.append("\n#" + i + "\n"+ nodeList.get(i));
        }
        return builder.toString();
    }
}
