package com.java.framework.settings;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class SettingsManager {
    private ArrayList<Category> settingsCategories = new ArrayList<>();

    public Category createCategory(String categoryName) throws IllegalArgumentException {
        if (categoryExist(categoryName)) throw new IllegalArgumentException();
        Category newCategory = new Category(categoryName);
        settingsCategories.add(newCategory);
        return newCategory;
    }

    public Category getCategory(String categoryName) {
        return settingsCategories.get(getCategoryIndex(categoryName));
    }

    public boolean categoryExist(String categoryName) {
        return getCategoryIndex(categoryName) >= 0;
    }

    private int getCategoryIndex(String categoryName) {
        for (int i = 0; i < settingsCategories.size(); i++) {
            if (settingsCategories.get(i).getName() == categoryName) return i;
        }
        return -1;
    }

    public void exportConfiguration(String fullFilePath) throws Exception {
        this.writeXmlToFile(fullFilePath, this.getXmlRepresentation());
    }

    private Document getXmlRepresentation() throws Exception {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document xmlDocument = docBuilder.newDocument();

        Element root = xmlDocument.createElement("Settings");
        xmlDocument.appendChild(root);

        for (Category category: settingsCategories) {
            category.addToXml(xmlDocument, root);
        }

        return xmlDocument;
    }

    private void writeXmlToFile(String fullFilePath, Document xmlDocument) throws Exception {
        FileOutputStream outputStream = new FileOutputStream(fullFilePath);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(xmlDocument);
        StreamResult result = new StreamResult(outputStream);

        transformer.transform(source, result);
    }
}