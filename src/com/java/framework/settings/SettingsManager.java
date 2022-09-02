package com.java.framework.settings;

import java.util.ArrayList;

import java.io.File;
import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.FileOutputStream;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class SettingsManager {
    private ArrayList<Category> settingsCategories = new ArrayList<>();

    private final String ROOTELEMENTNAME = "Settings";

    public Category createCategory(String categoryName) throws IllegalArgumentException {
        if (!this.categoryExist(categoryName)) {
            Category newCategory = new Category(categoryName);
            settingsCategories.add(newCategory);
            return newCategory;
        } else throw new IllegalArgumentException();
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
        this.writeXmlDocument(fullFilePath, this.getXmlDocument());
    }

    private Document getXmlDocument() throws Exception {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document xmlDocument = docBuilder.newDocument();

        this.addToXml(xmlDocument);

        return xmlDocument;
    }

    public void addToXml(Document sourceDocument){
        Element localNode = sourceDocument.createElement(ROOTELEMENTNAME);
        sourceDocument.appendChild(localNode);

        for (int i = 0; i < settingsCategories.size(); i++) {
            settingsCategories.get(i).addToXml(sourceDocument, localNode);
        }
    }

    private void writeXmlDocument(String fullFilePath, Document xmlDocument) throws Exception {
        FileOutputStream outputStream = new FileOutputStream(fullFilePath);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(xmlDocument);
        StreamResult result = new StreamResult(outputStream);

        transformer.transform(source, result);
    }

    public void importConfiguration(String fullFilePath) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(fullFilePath));

            doc.getDocumentElement().normalize();

            NodeList importCategories = doc.getDocumentElement().getChildNodes();
            Node importCategory;
            String importCategoryName;

            Category targetCategory;

            NodeList importEntries;
            Node importEntry;
            String importEntryName;

            for (int i = 0; i < importCategories.getLength(); i++) {
                importCategory = importCategories.item(i);
                importCategoryName = importCategory.getNodeName();

                System.out.println(importCategoryName);

                if (this.categoryExist(importCategoryName)) {
                    targetCategory = this.getCategory(importCategoryName);
                    importEntries = importCategories.item(i).getChildNodes();

                    for (int x = 0; x < importEntries.getLength(); x++) {
                        importEntry = importEntries.item(x);
                        importEntryName = importEntry.getNodeName();

                        if (targetCategory.entryExist(importEntryName)) {
                            targetCategory.getEntry(importEntryName).setValue(importEntry.getTextContent());
                        }
                    }
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }
}