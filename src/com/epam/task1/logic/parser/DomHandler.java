package com.epam.task1.logic.parser;

import com.epam.task1.bean.dom.Element;
import com.epam.task1.bean.domain.Domain;
import com.epam.task1.logic.parser.exception.DocumentBuilderException;

/**
 * Created by Zheny Chaichits on 12/3/2015.
 */
public interface DomHandler {
    void handleFullTag(Domain currentDomain);

    void handleOpenTag(Domain currentDomain);

    void handleClosingTag(Domain currentDomain);

    void handleSelfClosingTag(Domain currentDomain);

    Element getRootElement() throws DocumentBuilderException;
}
