package main;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.List;

public class UtilidadesXML {
    public static void exportarXML(List<Producto> listadoProductos, File fichero){
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document=null;
        try {
            builder = factory.newDocumentBuilder();
            document = builder.newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        assert document != null;
        Element root=document.createElement("productos");
        document.appendChild(root);

        Document finalDocument = document;
        listadoProductos.forEach(producto -> appendProduct(finalDocument, root, producto));
        try {
            writeXml(document, new BufferedOutputStream(new FileOutputStream(fichero)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void appendProduct(Document document, Element root, Producto producto) {
        Element pater= document.createElement("producto");
        root.appendChild(pater);
        Attr attributeID= document.createAttribute("id");
        attributeID.setValue(String.valueOf(producto.getId()));
        pater.setAttributeNode(attributeID);
        Element stock= document.createElement("stock");
        stock.setTextContent(String.valueOf(producto.getStock()));
        pater.appendChild(stock);
        Element caracterisitcas= document.createElement("caracteristicas");
        pater.appendChild(caracterisitcas);
        Attr peso= document.createAttribute("peso");
        peso.setValue(String.valueOf(producto.getPeso()));
        caracterisitcas.setAttributeNode(peso);
        Attr color= document.createAttribute("color");
        color.setValue(String.valueOf(producto.getColor()));
        caracterisitcas.setAttributeNode(color);

        caracterisitcas.setAttribute("color", producto.getColor());
        //        peso.setTextContent(String.valueOf(producto.peso));
//        pater.appendChild(peso);
    }

    private static void writeXml(Document doc, OutputStream output) {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }

        assert transformer != null;
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }
}

