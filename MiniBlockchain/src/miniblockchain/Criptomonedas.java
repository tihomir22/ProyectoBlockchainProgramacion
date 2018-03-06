/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniblockchain;

/**
 *
 * @author sportak
 */
public class Criptomonedas {

    private String nombre;
    private double precioDolares;
    private boolean minable;
    private double capMerca;
    private double cantidad;

    public Criptomonedas(String nombre, double precioDolares, boolean minable, double capMerca, double cantidad) {
        this.nombre = nombre;
        this.precioDolares = precioDolares;
        this.minable = minable;
        this.capMerca = capMerca;
        this.cantidad = cantidad;
    }

    public void mostrarCriptomoneda() {
        System.out.println("Nombre" + "\t" + "Cap" + "\t" + "Minable" + "\t" + "Valor en $" + "\t" + "Cantidad" + "\t");
        System.out.println(this.nombre + "\t" + this.capMerca + "B$" + "\t" + this.minable + "\t" + this.precioDolares + "$" + "\t" + this.cantidad + "\t");
    }

    public String getNombre() {
        return nombre;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioDolares() {
        return precioDolares;
    }

    public void setPrecioDolares(double precioDolares) {
        this.precioDolares = precioDolares;
    }

    public boolean isMinable() {
        return minable;
    }

    public void setMinable(boolean minable) {
        this.minable = minable;
    }

    public double getCapMerca() {
        return capMerca;
    }

    public void setCapMerca(double capMerca) {
        this.capMerca = capMerca;
    }

}
