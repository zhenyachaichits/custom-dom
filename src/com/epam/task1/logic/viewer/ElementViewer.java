package com.epam.task1.logic.viewer;

import com.epam.task1.bean.dom.Attr;
import com.epam.task1.bean.dom.Element;
import com.epam.task1.bean.dom.collection.NodeList;

import javax.naming.OperationNotSupportedException;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Created by Zheny Chaichits on 10.12.2015.
 */
public class ElementViewer implements Viewable {
    private Element element;

    public ElementViewer() {
    }

    public ElementViewer(Element element) {
        this.element = element;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    @Override
    public void view(OutputStream os) {
        try {
            PrintStream ps = new PrintStream(os);
            ps.println("\tElement name: " + element.getNodeName());
            String content = element.getNodeValue();
            if(content != null) {
                ps.println("\tElement value: " + element.getNodeValue());
            }

            NodeList attributes = element.getAttributes();
            AttrViewer attrViewer = new AttrViewer();

            ps.println("\tElement has " + attributes.size() + " attributes.");
            ps.println(DELIMETER);

            for (int i = 0; i < attributes.size(); i++) {
                ps.println("Attribute #" + (i + 1));
                attrViewer.setAttribute((Attr) attributes.item(i));
                attrViewer.view(os);
            }

            ps.println(DELIMETER);

            NodeList childNodes = element.getChildNodes();
            ElementViewer elementViewer = new ElementViewer();

            ps.println("\tElement has " + childNodes.size() + " child nodes.");
            ps.println(DELIMETER);

            for (int i = 0; i < childNodes.size(); i++) {
                ps.println("Child #" + (i + 1));
                elementViewer.setElement((Element) childNodes.item(i));
                elementViewer.view(os);
            }

            ps.println(DELIMETER);

        } catch (OperationNotSupportedException e) {
            System.err.println("Element viewer failure. Unsupported operation request");
        }
    }
}
