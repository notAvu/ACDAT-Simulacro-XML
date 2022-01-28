package main;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.OutputStream;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        ArrayList<Producto> productosList= new ArrayList<>();
        productosList.add(new Producto("127826", "Una patata",2));
        productosList.add(new Producto("484532", "Dos patata",4));
        productosList.add(new Producto("798986", "Muchas patata",2450));

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
        productosList.forEach(producto -> appendProduct(finalDocument, root, producto));
        writeXml(document, System.out);
    }

    private static void appendProduct(Document document, Element root, Producto producto) {
        Element pater= document.createElement("producto");
        root.appendChild(pater);
        Element codigo= document.createElement("codigo");
        codigo.setTextContent(producto.codigo);
        pater.appendChild(codigo);
        Element descripcion= document.createElement("descripcion");
        descripcion.setTextContent(producto.descripcion);
        pater.appendChild(descripcion);
        Element precio= document.createElement("precio");
        precio.setTextContent(String.valueOf(producto.precio));
        pater.appendChild(precio);
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