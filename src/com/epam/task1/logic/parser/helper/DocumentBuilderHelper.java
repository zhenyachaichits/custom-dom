package com.epam.task1.logic.parser.helper;

import com.epam.task1.bean.dom.Attr;
import com.epam.task1.bean.dom.Element;
import com.epam.task1.bean.domain.Domain;
import com.epam.task1.logic.parser.DomHandler;
import com.epam.task1.logic.parser.exception.DocumentBuilderException;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Zheny Chaichits on 11/27/2015.
 */
public class DocumentBuilderHelper implements DomHandler {

    private Stack<Domain> openTagStack = new Stack<>();
    private Stack<Element> elementStack = new Stack<>();

    private static final String ATTRIBUTE = "(?<ATTRNAME>\\w+)=\"(?<ATTRVALUE>[^\"]*)\"";

    private static final String ATTRIBUTE_NAME = "ATTRNAME";
    private static final String ATTRIBUTE_VALUE = "ATTRVALUE";

    private static Pattern patternAttr = Pattern.compile(ATTRIBUTE);


    private void addAttributesToElement(String attributes, Element element) {
        Matcher matcherAttr = patternAttr.matcher(attributes);
        while (matcherAttr.find()) {
            Attr attr = new Attr();
            attr.setNodeName(matcherAttr.group(ATTRIBUTE_NAME));
            attr.setNodeValue(matcherAttr.group(ATTRIBUTE_VALUE));
            element.setAttribute(attr);
        }
    }

    @Override
    public void handleFullTag(Domain currentDomain) {
        String name = currentDomain.getName();
        String attributes = currentDomain.getAttributes();
        String content = currentDomain.getContent();

        Element newElement = new Element();
        newElement.setNestingLevel(openTagStack.size() - 1);

        newElement.setNodeName(name);
        if (attributes != null && !"".equals(attributes)) {
            addAttributesToElement(attributes, newElement);
        }

        newElement.setNodeValue(content);

        elementStack.push(newElement);
    }

    @Override
    public void handleOpenTag(Domain currentDomain) {
        openTagStack.push(currentDomain);
    }

    @Override
    public void handleSelfClosingTag(Domain currentDomain) {
        String name = currentDomain.getName();

        String attributes = currentDomain.getAttributes();

        int nestingLevel = 0;

        Element newElement = new Element();

        newElement.setNodeName(name);
        if (attributes != null && !"".equals(attributes)) {
            addAttributesToElement(attributes, newElement);
        }

        if (!elementStack.isEmpty()) {
            nestingLevel = openTagStack.size() - 1;
            newElement.setNestingLevel(nestingLevel);
        }

        elementStack.push(newElement);
    }

    @Override
    public void handleClosingTag(Domain currentDomain) {
        String name = currentDomain.getName();

        Domain tagToClose = openTagStack.pop();

        String attributes = tagToClose.getAttributes();

        int nestingLevel = 0;

        Element newElement = new Element();

        newElement.setNodeName(name);
        if (attributes != null && !"".equals(attributes)) {
            addAttributesToElement(attributes, newElement);
        }

        if (!elementStack.isEmpty()) {
            nestingLevel = elementStack.peek().getNestingLevel();
            newElement.setNestingLevel(nestingLevel - 1);
        }

        while (elementStack.size() != 0 && elementStack.peek().getNestingLevel() == nestingLevel) {
            Element newChild = elementStack.pop();
            newElement.appendChild(newChild);
        }

        elementStack.push(newElement);

    }

    @Override
    public Element getRootElement() throws DocumentBuilderException {
        if (elementStack.size() == 1) {
            return elementStack.pop();
        }
        throw new DocumentBuilderException("Impossible to find root element");
    }

}
