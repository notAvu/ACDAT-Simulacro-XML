package main;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.OutputStream;
import java.io.StringWriter;
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
        for (Producto p:productosList) {
            Element pater=document.createElement("producto");
            root.appendChild(pater);
            Element codigo= document.createElement("codigo");
            codigo.setTextContent(p.codigo);
            pater.appendChild(codigo);
            Element descripcion=document.createElement("descripcion");
            descripcion.setTextContent(p.descripcion);
            pater.appendChild(descripcion);
            Element precio=document.createElement("precio");
            precio.setTextContent(String.valueOf(p.precio));
            pater.appendChild(precio);
        }
        writeXml(document, System.out);
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

//public class JaxbExample
//{
//    public static void main(String[] args)
//    {
//        //Java object. We will convert it to XML.
//        Employee employee = new Employee(1, "Lokesh", "Gupta", new Department(101, "IT"));
//
//        //Method which uses JAXB to convert object to XML
//        jaxbObjectToXML(employee);
//    }
//
//    private static void jaxbObjectToXML(Producto employee)
//    {
//        try
//        {
//            //Create JAXB Context
//            JAXBContext jaxbContext = JAXBContext.newInstance(Producto.class);
//
//            //Create Marshaller
//            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//
//            //Required formatting??
//            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//
//            //Print XML String to Console
//            StringWriter sw = new StringWriter();
//
//            //Write XML to StringWriter
//            jaxbMarshaller.marshal(employee, sw);
//
//            //Verify XML Content
//            String xmlContent = sw.toString();
//            System.out.println( xmlContent );
//
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
//    }
//}