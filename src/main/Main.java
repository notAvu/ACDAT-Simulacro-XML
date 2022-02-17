package main;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        ArrayList<Producto> productosList = new ArrayList<>();
        productosList.add(new Producto(127826, 123, 2.4f, "azul"));
        productosList.add(new Producto(484532, 13, 23.4f,"negro"));
        productosList.add(new Producto(798986, 414, 22f, "amarillo"));

        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre del fichero:");
        String nombreFichero=sc.next();
        UtilidadesXML.exportarXML(productosList, new File(nombreFichero));
    }
}