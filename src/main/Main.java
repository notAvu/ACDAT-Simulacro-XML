package main;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        ArrayList<Producto> productosList = new ArrayList<>();
        productosList.add(new Producto(127826, "Una patata", 2));
        productosList.add(new Producto(484532, "Dos patata", 4));
        productosList.add(new Producto(798986, "Muchas patata", 2450));

        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre del fichero:");
        String nombreFichero=sc.next();
        UtilidadesXML.exportarXML(productosList, new File(nombreFichero));
    }
}