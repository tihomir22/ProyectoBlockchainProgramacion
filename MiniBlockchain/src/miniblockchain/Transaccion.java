/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniblockchain;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author sportak
 */
public class Transaccion {

    private Date fecha;
    private double importeDolar;
    public Wallet destinatario;
    public Wallet emisor;

    public Transaccion(Date fecha, double importeDolar, Wallet destinatario, Wallet emisor) {
        this.fecha = fecha;
        this.importeDolar = importeDolar;
        this.destinatario = destinatario;
        this.emisor = emisor;
    }

    public Transaccion() {

    }

    public boolean procesarTransaccion(Wallet origen, Wallet destino, Criptomonedas moneda, Double cantidad) {
        //Codigo para restar al origen las monedas y comprobar que tenga fondos
        for (int i = 0; i < origen.monedas.size(); i++) {
            if (origen.monedas.get(i).getNombre().equalsIgnoreCase(moneda.getNombre())) {
                if (origen.monedas.get(i).getCantidad() > cantidad) {
                    origen.monedas.get(i).setCantidad(origen.monedas.get(i).getCantidad() - cantidad);
                } else {
                    System.out.println("No tiene fondos suficientes");
                    return false;
                }
            }
        }
        //Codigo para sumar al destino las monedas y actualizar sus monedas
        for (int j = 0; j < destino.monedas.size(); j++) {
            if (destino.monedas.get(j).getNombre().equalsIgnoreCase(moneda.getNombre())) {
                destino.monedas.get(j).setCantidad(destino.monedas.get(j).getCantidad() + cantidad);
            }
        }
        origen.listaTransW.add(this);
        origen.setBalanceDolares(origen.getBalanceDolares() - this.getImporteDolar());
        destino.listaTransW.add(this);
        destino.setBalanceDolares(destino.getBalanceDolares() + this.getImporteDolar());
        return true;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getImporteDolar() {
        return importeDolar;
    }

    public void setImporteDolar(double importeDolar) {
        this.importeDolar = importeDolar;
    }

    public Wallet getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Wallet destinatario) {
        this.destinatario = destinatario;
    }

    public Wallet getEmisor() {
        return emisor;
    }

    public void setEmisor(Wallet emisor) {
        this.emisor = emisor;
    }

}
