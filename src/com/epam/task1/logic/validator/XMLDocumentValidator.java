package com.epam.task1.logic.validator;

import com.epam.task1.bean.domain.Domain;
import com.epam.task1.logic.validator.exception.DocumentValidatorException;
import com.epam.task1.logic.reader.XMLReader;
import com.epam.task1.logic.reader.exception.XMLReaderException;

import java.io.File;
import java.io.IOException;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Zheny Chaichits on 07.12.2015.
 */
public class XMLDocumentValidator implements DocumentValidator {

    private Stack<Domain> openTagStack = new Stack<>();

    private static final String ATTRIBUTE_VALIDATION = "(?<ATTRNAME>\\w+)=\"(?<ATTRVALUE>[^\"]*)\"";
    private static Pattern patternAttrValidation = Pattern.compile(ATTRIBUTE_VALIDATION);

    private boolean isAttributeValid(String attributes) {
        attributes = attributes.replaceAll("\\s", "");
        Matcher matcherAttr = patternAttrValidation.matcher(attributes);
        int totalMatchesLength = 0;

        while (matcherAttr.find()) {
            totalMatchesLength = totalMatchesLength + matcherAttr.group().length();
        }

        if (totalMatchesLength == attributes.length()) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isDocumentValid(File file) throws DocumentValidatorException, IOException {
        XMLReader xmlReader = null;
        try {
            xmlReader = new XMLReader(file);
            Domain currentDomain = null;
            while ((currentDomain = xmlReader.readTag()) != null) {
                switch (currentDomain.getDomainType()) {
                    case OPEN_TAG:
                        openTagStack.push(currentDomain);
                        if (currentDomain.hasAttributes()) {
                            if (!isAttributeValid(currentDomain.getAttributes())) {
                                throw new DocumentValidatorException("Found invalid attribute declaration");
                            }
                        }
                        break;
                    case CLOSE_TAG:
                        if (openTagStack.peek().getName().equals(currentDomain.getName())) {
                            openTagStack.pop();
                        }
                        break;
                }
            }
            if (openTagStack.size() == 0) {
                return true;
            }
        } catch (XMLReaderException e) {
            throw new DocumentValidatorException("Error while document validating", e);
        } finally {
            xmlReader.close();
        }
        return false;
    }
}
