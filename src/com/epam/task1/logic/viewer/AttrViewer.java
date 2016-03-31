package com.epam.task1.logic.viewer;

import com.epam.task1.bean.dom.Attr;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Created by Zheny Chaichits on 09.12.2015.
 */
public class AttrViewer implements Viewable {

    private Attr attribute;

    public AttrViewer(){}

    public AttrViewer(Attr attribute){
        this.attribute = attribute;
    }

    public Attr getAttribute() {
        return attribute;
    }

    public void setAttribute(Attr attribute) {
        this.attribute = attribute;
    }

    @Override
    public void view(OutputStream os) {
        PrintStream ps = new PrintStream(os);
        ps.println("\tAttribute name: " + attribute.getNodeName());
        ps.println("\tAttribute value: " + attribute.getNodeValue());
    }
}
