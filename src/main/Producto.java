package main;

public class Producto {
    private int id;
    private int stock;
    private float peso;
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public Producto(int codigo, int stock, float peso,String color) {
        this.id = codigo;
        this.stock = stock;
        this.peso = peso;
        this.color=color;
    }
}
