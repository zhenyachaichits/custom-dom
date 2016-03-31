package com.epam.task1.main;

import com.epam.task1.bean.dom.Document;
import com.epam.task1.bean.dom.Element;
import com.epam.task1.logic.viewer.ElementViewer;
import com.epam.task1.logic.parser.DocumentBuilder;
import com.epam.task1.logic.parser.DomHandler;
import com.epam.task1.logic.parser.exception.DocumentBuilderException;
import com.epam.task1.logic.validator.exception.DocumentValidatorException;
import com.epam.task1.logic.parser.helper.DocumentBuilderHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Zheny Chaichits on 11/24/2015.
 */
public class Runner {
    public static void main(String[] args) {
        try {
            DocumentBuilder documentBuilder = DocumentBuilder.getInstance();
            File fileXML = new File("src/resource/Candies.xml");
            DomHandler helper = new DocumentBuilderHelper();

            Document document = documentBuilder.parse(fileXML, helper);
            Element root = document.getDocumentElement();
            ElementViewer elementViewer = new ElementViewer(root);

            FileOutputStream fos = new FileOutputStream(new File("src/resource/result.txt"));
            elementViewer.view(fos);
            elementViewer.view(System.out);

            System.out.println(root);

        } catch (DocumentBuilderException | IOException e) {
            e.printStackTrace();
        } catch (DocumentValidatorException e) {
            e.printStackTrace();
        }
    }
}
