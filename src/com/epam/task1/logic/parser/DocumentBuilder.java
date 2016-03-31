package com.epam.task1.logic.parser;

import com.epam.task1.bean.dom.Document;
import com.epam.task1.bean.dom.Element;
import com.epam.task1.bean.domain.Domain;
import com.epam.task1.logic.parser.exception.DocumentBuilderException;
import com.epam.task1.logic.validator.DocumentValidator;
import com.epam.task1.logic.validator.exception.DocumentValidatorException;
import com.epam.task1.logic.validator.XMLDocumentValidator;
import com.epam.task1.logic.reader.XMLReader;
import com.epam.task1.logic.reader.exception.XMLReaderException;

import java.io.File;
import java.io.IOException;

/**
 * Created by Zheny Chaichits on 11/24/2015.
 */
public class DocumentBuilder {
    private static DocumentBuilder instance = new DocumentBuilder();

    private DocumentBuilder() {
    }

    public static DocumentBuilder getInstance() {
        return instance;
    }


    private void buildDocument(File file, Document document, DomHandler helper) throws DocumentBuilderException, IOException {
        XMLReader xmlReader = null;
        try {
            xmlReader = new XMLReader(file);
            Domain currentDomain = null;
            while ((currentDomain = xmlReader.readTag()) != null) {
                switch (currentDomain.getDomainType()) {
                    case FULL_TAG_WITH_CONTENT:
                        helper.handleFullTag(currentDomain);
                        break;
                    case OPEN_TAG:
                        helper.handleOpenTag(currentDomain);
                        break;
                    case SELF_CLOSE_TAG:
                        helper.handleSelfClosingTag(currentDomain);
                        break;
                    case CLOSE_TAG:
                        helper.handleClosingTag(currentDomain);
                        break;
                }
            }

            Element root = helper.getRootElement();
            document.setDocumentElement(root);
        } catch (XMLReaderException e) {
            throw new DocumentBuilderException("Error while document building", e);
        } finally {
            xmlReader.close();
        }
    }


    public Document parse(File file, DomHandler helper) throws DocumentBuilderException, IOException, DocumentValidatorException {
        Document document = new Document();
        DocumentValidator validator = new XMLDocumentValidator();
        if (validator.isDocumentValid(file)) {
            buildDocument(file, document, helper);
            return document;
        } else throw new DocumentValidatorException("XML document is invalid");
    }
}
