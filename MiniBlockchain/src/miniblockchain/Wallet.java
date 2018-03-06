/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniblockchain;

import java.util.ArrayList;

/**
 *
 * @author sportak
 */
public class Wallet {

    private String clavePrivada;
    private String clavePublica;
    private double balanceDolares;
    public Usuario dueño;

    public ArrayList<Criptomonedas> monedas = new ArrayList();
    public ArrayList<Transaccion> listaTransW = new ArrayList();

    public Wallet(String clavePrivada, String clavePublica, double balanceDolares, Usuario dueño) {
        this.clavePrivada = clavePrivada;
        this.clavePublica = clavePublica;
        this.balanceDolares = balanceDolares;
        this.dueño = dueño;
    }

    Wallet() {

    }

    public String generarCPublica(Usuario activo) {
        String clave;
        int numAle = (int) (Math.random() * 100);
        clave = "DSSWH" + activo.getDni().substring(1, 3) + activo.getNumTel().substring(1, 3) + numAle;
        return clave;

    }

    public String generarCPrivada(Usuario activo) {
        String clave;
        int numAle = (int) (Math.random() * 100);
        int numAle2 = (int) (Math.random() * 100);
        int numAle3 = (int) (Math.random() * 100);
        clave = numAle + "LOEFF" + activo.getDni().substring(1, 3) + activo.getNumTel().substring(1, 3) + numAle2 + "CP" + activo.getDni().substring(1, 3) + activo.getDni().substring(5, 7) + numAle3;
        return clave;

    }

    public void añadirCriptomoneda(Criptomonedas c) {
        this.monedas.add(c);

    }
    public double calcularDolares() {
        double importe = 0;
        double aux;
        for (int i = 0; i < this.monedas.size(); i++) {
            aux = this.monedas.get(i).getCantidad() * this.monedas.get(i).getPrecioDolares();
            importe += aux;
        }
        return importe;
    }
    public boolean comprobarDestinatario(Wallet destinatario) {
        if (this.balanceDolares == 0) { // comprobamos que haya fondos
            System.out.println("El emisor no tiene fondos");
            return false;
        }
        if (destinatario.getClavePublica().equalsIgnoreCase("")) { // comprobamos que tenga clave publica
            System.out.println("El destinatario no tiene clave publica");
            return false;
        }
        return true;
    }
    public Criptomonedas buscarCriptomoneda(String nombre) {
        for (int j = 0; j < this.monedas.size(); j++) {
            if (nombre.equalsIgnoreCase(this.monedas.get(j).getNombre())) {
                return this.monedas.get(j);
            }
        }
        return null;
    }

    public void listarTransacciones() {
        System.out.println("Emisor" + "\t" + "Destinatario" + "\t" + "\t" + "Fecha" + "\t" + "\t" + "\t" + "\t" + "Importe" + "\t");
        for (int i = 0; i < this.listaTransW.size(); i++) {
            System.out.print(this.listaTransW.get(i).getEmisor().getClavePublica() + "\t" + this.listaTransW.get(i).getDestinatario().getClavePublica() + "\t" + this.listaTransW.get(i).getFecha() + "\t" + this.listaTransW.get(i).getImporteDolar());
            System.out.println("");
        }
    }

    public ArrayList<Criptomonedas> getMonedas() {
        return monedas;
    }

    public void setMonedas(ArrayList<Criptomonedas> monedas) {
        this.monedas = monedas;
    }

    public ArrayList<Transaccion> getListaTransW() {
        return listaTransW;
    }

    public void setListaTransW(ArrayList<Transaccion> listaTransW) {
        this.listaTransW = listaTransW;
    }

    public Usuario getDueño() {
        return dueño;
    }

    public void setDueño(Usuario dueño) {
        this.dueño = dueño;
    }

    public String getClavePrivada() {
        return clavePrivada;
    }

    public void setClavePrivada(String clavePrivada) {
        this.clavePrivada = clavePrivada;
    }

    public String getClavePublica() {
        return clavePublica;
    }

    public void setClavePublica(String clavePublica) {
        this.clavePublica = clavePublica;
    }

    public double getBalanceDolares() {
        return balanceDolares;
    }

    public void setBalanceDolares(double balanceDolares) {
        this.balanceDolares = balanceDolares;
    }

}
