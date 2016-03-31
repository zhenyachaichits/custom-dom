package com.epam.task1.bean.dom;

/**
 * Created by Zheny Chaichits on 11/24/2015.
 */
public class Document {
    private Element root;

    public Document() {
        root = new Element();
    }

    public void setDocumentElement(Element root) {
        this.root = root;
    }

    public Element getDocumentElement() {
        return root;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Document document = (Document) obj;

        return !(root != null ? !root.equals(document.root) : document.root != null);

    }

    @Override
    public int hashCode() {
        return root != null ? root.hashCode() : 0;
    }

    @Override
    public String toString() {
        return getClass().getName() + " @ root: " + root;
    }
}
