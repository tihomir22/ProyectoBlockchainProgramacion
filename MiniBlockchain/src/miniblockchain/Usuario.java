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
public class Usuario {

    private String nombre;
    private String dni;
    private String correo;
    private boolean documentacion;
    private String numTel;
    public double inversion;
    public ArrayList<Wallet> wallets = new ArrayList();
    //public ArrayList<Transaccion> transaccioneU = new ArrayList();

    public Usuario(String nombre, String dni, String correo, boolean documentacion, String numTel, double inversion) {
        this.nombre = nombre;
        this.dni = dni;
        this.correo = correo;
        this.documentacion = documentacion;
        this.numTel = numTel;
        this.inversion = inversion;
    }

    public void mostrarDatosUsuario() {
        System.out.println("NOMBRE " + "\t" + "DNI" + "\t" + "DOCUMENTACION" + "\t" + "NUMTEL   " + "\t" + "INVERSION" + "\t");
        System.out.println(this.nombre + "\t" + this.dni + "\t" + this.documentacion + "\t" + this.numTel + "\t" + this.inversion + "€" + "\t");

        System.out.println("Wallets del usuario, vista rapida :");
        for (int i = 0; i < this.wallets.size(); i++) {
            System.out.println("");
            System.out.println("************************************");
            System.out.println(this.wallets.get(i).getClavePublica());
            System.out.println(this.wallets.get(i).getClavePrivada());
            System.out.println(this.wallets.get(i).getBalanceDolares() + "$");
            System.out.println("************************************");
        }

    }

    public void mostrarWalletsUsuario() {

        for (int i = 0; i < this.wallets.size(); i++) {
            System.out.println("");
            this.asciiWallet();
            System.out.println("************************************");
            System.out.println(this.wallets.get(i).getClavePublica());
            System.out.println(this.wallets.get(i).getClavePrivada());
            System.out.println(this.wallets.get(i).getDueño());
            System.out.println(this.wallets.get(i).getMonedas().size() + "Numero de criptomonedas");
            System.out.println(this.wallets.get(i).getBalanceDolares() + "$");
            System.out.println("");
            System.out.println("************************************");
            this.asciiMoneda();
            if (!this.wallets.get(i).getMonedas().isEmpty()) {
                for (int j = 0; j < this.wallets.get(i).getMonedas().size(); j++) {
                    this.wallets.get(i).getMonedas().get(j).mostrarCriptomoneda();
                    System.out.println("Su monto total ascende a " + this.wallets.get(i).calcularDolares() + "€");
                    this.inversion = this.wallets.get(i).calcularDolares();
                }
            }
            System.out.println("************************************");
        }
    }

    public Wallet buscarWallet(String busqueda) {
        for (int i = 0; i < this.wallets.size(); i++) {
            if (this.wallets.get(i).getClavePublica().equalsIgnoreCase(busqueda)) {
                return this.wallets.get(i);

            }
        }
        return null;
    }

    /*
    public void mostrarTransaccionesUsuario() {
        for (int i = 0; i < this.transaccioneU.size(); i++) {
            System.out.println("Fecha" + this.transaccioneU.get(i).getFecha());
            System.out.println("Destinatario" + this.transaccioneU.get(i).getDestinatario().getClavePublica());
            System.out.println("Emisor" + this.transaccioneU.get(i).getEmisor().getClavePublica());
            System.out.println("Importe " + this.transaccioneU.get(i).getImporteDolar());
        }
    }
     */

    public void asciiWallet() {
        System.out.println("____    __    ____  ___       __       __       _______ .___________.\n"
                + "\\   \\  /  \\  /   / /   \\     |  |     |  |     |   ____||           |\n"
                + " \\   \\/    \\/   / /  ^  \\    |  |     |  |     |  |__   `---|  |----`\n"
                + "  \\            / /  /_\\  \\   |  |     |  |     |   __|      |  |     \n"
                + "   \\    /\\    / /  _____  \\  |  `----.|  `----.|  |____     |  |     \n"
                + "    \\__/  \\__/ /__/     \\__\\ |_______||_______||_______|    |__|     \n"
                + "                                                                    ");
    }

    public void asciiMoneda() {
        System.out.println(".___  ___.   ______   .__   __.  _______  _______       ___           _______.\n"
                + "|   \\/   |  /  __  \\  |  \\ |  | |   ____||       \\     /   \\         /       |\n"
                + "|  \\  /  | |  |  |  | |   \\|  | |  |__   |  .--.  |   /  ^  \\       |   (----`\n"
                + "|  |\\/|  | |  |  |  | |  . `  | |   __|  |  |  |  |  /  /_\\  \\       \\   \\    \n"
                + "|  |  |  | |  `--'  | |  |\\   | |  |____ |  '--'  | /  _____  \\  .----)   |   \n"
                + "|__|  |__|  \\______/  |__| \\__| |_______||_______/ /__/     \\__\\ |_______/    \n"
                + "                                                                              ");
    }

    /*
    public ArrayList<Transaccion> getTransaccioneU() {
        return transaccioneU;
    }

    public void setTransaccioneU(ArrayList<Transaccion> transaccioneU) {
        this.transaccioneU = transaccioneU;
    }
     */
    public ArrayList<Wallet> getWallets() {
        return wallets;
    }

    public void setWallets(ArrayList<Wallet> wallets) {
        this.wallets = wallets;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isDocumentacion() {
        return documentacion;
    }

    public void setDocumentacion(boolean documentacion) {
        this.documentacion = documentacion;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public double getInversion() {
        return inversion;
    }

    public void setInversion(double inversion) {
        this.inversion = inversion;
    }

    public void añadirWallet(Wallet w) {
        this.wallets.add(w);
    }

}
