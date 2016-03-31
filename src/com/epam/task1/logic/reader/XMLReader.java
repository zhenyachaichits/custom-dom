package com.epam.task1.logic.reader;

import com.epam.task1.bean.domain.Domain;
import com.epam.task1.logic.reader.exception.XMLReaderException;
import com.epam.task1.logic.reader.helper.XMLReaderHelper;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Zheny Chaichits on 11/24/2015.
 */
public class XMLReader implements Closeable {

    private Scanner scanner;

    public XMLReader(File file) throws FileNotFoundException {
        scanner = new Scanner(file);
        scanner.useDelimiter(">(?=\\s*<)");
    }

    public Domain readTag() throws IOException, XMLReaderException {
        if (scanner.hasNext(XMLReaderHelper.DECLARATION)) {
            return XMLReaderHelper.findDeclaration(scanner);
        }
        if (scanner.hasNext(XMLReaderHelper.COMMENT)) {
            return XMLReaderHelper.findComment(scanner);
        }
        if (scanner.hasNext(XMLReaderHelper.OPEN_TAG)) {
            return XMLReaderHelper.findOpenTag(scanner);
        }
        if (scanner.hasNext(XMLReaderHelper.CLOSING_TAG)) {
            return XMLReaderHelper.findCloseTag(scanner);
        }
        if (scanner.hasNext(XMLReaderHelper.SELF_CLOSING_TAG)) {
            return XMLReaderHelper.findSelfCloseTag(scanner);
        }
        if (scanner.hasNext(XMLReaderHelper.FULL_TAG)) {
            return XMLReaderHelper.findFullTag(scanner);
        }
        if (scanner.hasNext()) {
            return XMLReaderHelper.findLastCloseTag(scanner);
        }
        if (!scanner.hasNextLine()) {
            return null;
        }
        throw new XMLReaderException();
    }

    @Override
    public void close() throws IOException {
        scanner.close();
    }
}
