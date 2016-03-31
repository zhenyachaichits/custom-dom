package com.epam.task1.logic.reader.helper;

import com.epam.task1.bean.domain.Domain;
import com.epam.task1.bean.domain.type.DomainType;
import com.epam.task1.logic.reader.exception.XMLReaderException;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Zheny Chaichits on 12/1/2015.
 */
public class XMLReaderHelper {

    public static final String COMMENT = "\\s*<!--[\\w\\W]*--";
    public static final String FULL_TAG = "\\s*<(?<OPENTAG>[a-zA-Z][a-zA-Z0-9]*)(?<ATTRIBUTES>[=\"\\s\\w]*)>(?<CONTENT>[^<>]*?)</(?<CLOSETAG>\\1)";
    public static final String SELF_CLOSING_TAG = "\\s*<(?<OPENTAG>[a-zA-Z][a-zA-Z0-9]*)(?<ATTRIBUTES>[=\"\\s\\w]*)/";
    public static final String DECLARATION = "\\s*<\\?[xX][mM][lL](?<ATTRIBUTES>[=\"\\s\\w\\.\\-]*?)\\?";
    public static final String OPEN_TAG = "\\s*<(?<OPENTAG>[a-zA-Z][a-zA-Z0-9-]*)(?<ATTRIBUTES>[=\"\\s\\w]*)";
    public static final String CLOSING_TAG = "\\s*</(?<CLOSETAG>[a-zA-Z][a-zA-Z0-9-]*)";

    private static final String OPEN_TAG_GROUP = "OPENTAG";
    private static final String ATTRIBUTES_GROUP = "ATTRIBUTES";
    private static final String CONTENT_GROUP = "CONTENT";
    private static final String CLOSE_TAG_GROUP = "CLOSETAG";

    private static Pattern fullTagPattern = Pattern.compile(FULL_TAG);
    private static Pattern openTagPattern = Pattern.compile(OPEN_TAG);
    private static Pattern selfClosingTagPattern = Pattern.compile(SELF_CLOSING_TAG);
    private static Pattern closingTagPattern = Pattern.compile(CLOSING_TAG);
    private static Pattern declarationPattern = Pattern.compile(DECLARATION);
    private static Pattern commentPattern = Pattern.compile(COMMENT);
    private static Pattern lastClosingTagPattern = Pattern.compile(CLOSING_TAG + ">");


    public static Domain findFullTag(Scanner scanner) throws XMLReaderException {
        String tag = scanner.next(FULL_TAG);
        Matcher matcher = fullTagPattern.matcher(tag);
        if (matcher.find()) {
            String name = matcher.group(OPEN_TAG_GROUP);
            String attributes = matcher.group(ATTRIBUTES_GROUP);
            String content = matcher.group(CONTENT_GROUP);
            return new Domain(name, attributes, content, DomainType.FULL_TAG_WITH_CONTENT);
        } else {
            throw new XMLReaderException("Error found in full tag finder");
        }
    }

    public static Domain findOpenTag(Scanner scanner) throws XMLReaderException {
        String tag = scanner.next(OPEN_TAG);
        Matcher matcher = openTagPattern.matcher(tag);
        if (matcher.find()) {
            String name = matcher.group(OPEN_TAG_GROUP);
            String attributes = matcher.group(ATTRIBUTES_GROUP);
            return new Domain(name, attributes, DomainType.OPEN_TAG);
        } else {
            throw new XMLReaderException("Error found in open tag finder");
        }
    }

    public static Domain findSelfCloseTag(Scanner scanner) throws XMLReaderException {
        String tag = scanner.next(SELF_CLOSING_TAG);
        Matcher matcher = selfClosingTagPattern.matcher(tag);
        if (matcher.find()) {
            String name = matcher.group(OPEN_TAG_GROUP);
            String attributes = matcher.group(ATTRIBUTES_GROUP);
            return new Domain(name, attributes, DomainType.SELF_CLOSE_TAG);
        } else {
            throw new XMLReaderException("Error found in self-closing tag finder");
        }
    }

    public static Domain findCloseTag(Scanner scanner) throws XMLReaderException {
        String tag = scanner.next(CLOSING_TAG);
        Matcher matcher = closingTagPattern.matcher(tag);
        if (matcher.find()) {
            String name = matcher.group(CLOSE_TAG_GROUP);
            return new Domain(name, DomainType.CLOSE_TAG);
        } else {
            throw new XMLReaderException("Error found in closing tag finder");
        }
    }

    public static Domain findDeclaration(Scanner scanner) throws XMLReaderException {
        String tag = scanner.next(DECLARATION);
        Matcher matcher = declarationPattern.matcher(tag);
        if (matcher.find()) {
            String name = "XML Declaration";
            return new Domain(name, DomainType.DECLARATION);
        } else {
            throw new XMLReaderException("Error found in declaration finder");
        }
    }

    public static Domain findComment(Scanner scanner) throws XMLReaderException {
        String tag = scanner.next(COMMENT);
        Matcher matcher = commentPattern.matcher(tag);
        if (matcher.find()) {
            String name = "Comment";
            return new Domain(name, DomainType.COMMENT);
        } else {
            throw new XMLReaderException("Error found in comment finder");
        }
    }

    public static Domain findLastCloseTag(Scanner scanner) throws XMLReaderException {
        scanner.nextLine();
        String tag = scanner.nextLine();
        Matcher matcher = lastClosingTagPattern.matcher(tag);
        if (matcher.find()) {
            String name = matcher.group(CLOSE_TAG_GROUP);
            return new Domain(name, DomainType.CLOSE_TAG);
        } else {
            throw new XMLReaderException("Error found in last closing tag finder");
        }
    }
}
