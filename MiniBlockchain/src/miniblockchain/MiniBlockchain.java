/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniblockchain;

import java.util.Calendar;
import java.util.Scanner;

/**
 *
 * @author sportak
 */
public class MiniBlockchain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int opcion;
        Scanner teclado = new Scanner(System.in);
        Exchange binance = new Exchange();
        Usuario usuario1 = null;
        Usuario activo = null;
        Wallet destinatario = null;
        Wallet wallet1 = null;
        Wallet activoW = null;
        Criptomonedas bitcoin;
        Criptomonedas ethereum;
        Criptomonedas iota;
        Criptomonedas monedaA;
        Transaccion transaccion1 = null;
        String nombre, dni, correo, telefono;
        Calendar fecha = Calendar.getInstance();
        String clavepublica, claveprivada, clavepublica2;
        double inversion, cantidadF;
        boolean documentacion;

        System.out.println(fecha.getTime());
        do {
            mostrarMenu();
            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    binance.mostrarExchange();
                    mostrarBitcoin();
                    break;
                case 2:
                    //Se le permite al usuario elegir dos opciones, el usuario automatico siempre será el mismo, util para detectar repeticiones de usuarios
                    System.out.println("1 Prefiere introducir manualmente o 2 usuarios de forma automatica?");
                    opcion = teclado.nextInt();
                    //Opcion 1 para introduccion manual y opcion 2 para introduccion automatica
                    if (opcion == 1) {
                        System.out.println("Introduzca nombre del  usuario, por ejemplo Marcos");
                        teclado.nextLine();
                        nombre = teclado.nextLine();
                        System.out.println("Introduzca dni , por ejemplo 12345678A");
                        dni = teclado.nextLine();
                        System.out.println("Introduzca correo , por ejemplo MarcosDoesHomework@gmail.com");
                        correo = teclado.nextLine();
                        System.out.println("Introduzca telefono, por ejemplo 666999500");
                        telefono = teclado.nextLine();
                        System.out.println("Introduzca inversion, 5000 5500 5500,50 etc etcF");
                        inversion = teclado.nextDouble();
                        if (binance.comprobarIdentidad(nombre, dni, correo, telefono, inversion)) {
                            usuario1 = new Usuario(nombre, dni, correo, false, telefono, inversion);
                            binance.añadirUsuario(usuario1);
                        } else {
                            System.out.println("El usuario no pudo añadirse");
                        }
                        

                    }
                    if (opcion == 2) {
                        usuario1 = new Usuario("Tihomir", "X5514136R", "tihomir_alcudia3@hotmail.com", false, "603680594", 1000);
                        //Antes de añadir un usuario se comprueba si este existe, si existe devuelve un mensaje de error al usuario y no se añade
                        binance.añadirUsuario(usuario1);
                    }
                    break;
                case 3:
                    System.out.println("Introduzca dni del usuario, el usuario automatico es x5514136r");
                    teclado.nextLine();
                    dni = teclado.nextLine();
                    if (binance.buscarUsuario(dni) != null) {
                        activo = binance.buscarUsuario(dni);
                    } else {
                        System.out.println("No existe tal usuario");
                    }
                    break;

                case 4:
                    if (activo != null) {
                        System.out.println("Wallets creadas exitosamente");
                        wallet1 = new Wallet();
                        //Generacion de atributos de wallet, claves 
                        clavepublica = wallet1.generarCPublica(activo);
                        wallet1.setClavePublica(clavepublica);
                        claveprivada = wallet1.generarCPrivada(activo);
                        wallet1.setClavePrivada(claveprivada);
                        wallet1.setBalanceDolares(wallet1.calcularDolares());
                        //Se añaden al usuario, cada usuario puede tener mas de un wallet
                        activo.añadirWallet(wallet1);
                        activo.mostrarDatosUsuario();
                    } else {
                        System.out.println("Debes seleccionar un usuario primero");
                    }
                    break;

                case 5:
                    bitcoin = new Criptomonedas("Bitcoin", 10000, true, 161, 1);
                    ethereum = new Criptomonedas("Ether", 860.352, true, 96, 1);
                    iota = new Criptomonedas("IOTA", 2.32432, false, 7, 1);
                    System.out.println("Las monedas fueron dadas de alta y añadidas a su cartera");
                    activoW = wallet1;
                    activoW.añadirCriptomoneda(iota);
                    activoW.añadirCriptomoneda(bitcoin);
                    activoW.añadirCriptomoneda(ethereum);
                    activoW.setBalanceDolares(activoW.calcularDolares());

                    activo.mostrarWalletsUsuario();
                    break;

                case 6:
                    boolean ComprobarDestinatario = true;
                    boolean comprobarCriptomoneda = true;

                    System.out.println("Introduzca clave publica del usuario destinatario"); // se debe copiar y pegar la clave dle destinatario

                    teclado.nextLine();
                    clavepublica2 = teclado.nextLine();
                    destinatario = binance.buscarWallet(clavepublica2); //Tengo  el wallet destino
                    if (clavepublica2.equalsIgnoreCase(activoW.getClavePublica())) {
                        ComprobarDestinatario = false;
                        System.out.println("No puedes enviarte a ti mismo");
                    }
                    if (clavepublica2.isEmpty() && ComprobarDestinatario) {
                        ComprobarDestinatario = false;
                        System.out.println("Debes insertar una clave publica antes de continuar");
                    }
                    if (destinatario == null && ComprobarDestinatario) {
                        ComprobarDestinatario = false;
                        System.out.println("No existe la cartera introducida");
                    }

                    if (ComprobarDestinatario) {
                        ////////////////////////////////////////////////////////////////////////////////////
                        System.out.println("Desde que wallet quieres enviar fondos? ");
                        activo.mostrarWalletsUsuario();
                        clavepublica = teclado.nextLine();
                        if (clavepublica2.equalsIgnoreCase(clavepublica)) {
                            ComprobarDestinatario = false;
                            System.out.println("No puedes enviar a la misma clave publica");
                        }
                        if (ComprobarDestinatario) {
                            activoW = activo.buscarWallet(clavepublica);
                            System.out.println("Que quieres enviar? Introduzca nombre de criptomoneda");
                            nombre = teclado.nextLine();
                            System.out.println("Que cantidad quieres enviar?");
                            cantidadF = teclado.nextDouble();
                            monedaA = activoW.buscarCriptomoneda(nombre);
                            if (cantidadF == 0 || cantidadF == 0.0) {
                                System.out.println("No puedes enviar 0 criptomonedas");
                                comprobarCriptomoneda = false;
                            }
                            if (comprobarCriptomoneda) {
                                if (monedaA != null) {
                                    System.out.println("La  clave publica del emisor es " + activoW.getClavePublica());
                                    System.out.println("La clave publica del destinatario es " + destinatario.getClavePublica());
                                    if (activoW.comprobarDestinatario(destinatario)) {
                                        System.out.println("Entramos");
                                        transaccion1 = new Transaccion();
                                        transaccion1.setDestinatario(destinatario);
                                        transaccion1.setEmisor(activoW);
                                        transaccion1.setFecha(fecha.getTime());
                                        transaccion1.setImporteDolar(monedaA.getPrecioDolares() * cantidadF);
                                        if (transaccion1.procesarTransaccion(activoW, destinatario, monedaA, cantidadF)) {
                                            System.out.println("TRANSACCION REALIZADA CORRECTAMENTE");
                                        } else {
                                            System.out.println("No se pudo realizar la transaccion");
                                        }

                                    }

                                }
                            }
                        }
                    }
                    break;
                case 20:
                    activo.mostrarDatosUsuario();
                    activo.mostrarWalletsUsuario();
                    activoW.listarTransacciones();

                    break;

            }

        } while (opcion != 0);
    }

    public static void mostrarMenu() {

        System.out.println("1.-Mostrar informacion sobre el exchange");
        System.out.println("2.-Dar de alta al usuario");
        System.out.println("3.-Seleccionar usuario");
        System.out.println("4.-Generar clave publica y privada de su wallet");
        System.out.println("5.-Generar criptomonedas en cartera, 1 BTC,1 ETH,1 IOTA. ");
        System.out.println("6.-Enviar Criptomonedas a la cartera de otro usuario");
        System.out.println("20.-Simplemente mostrar TODOS LOS DATOS del usuario seleccionado");
        System.out.println("");
        System.out.println("****************************************************");
        System.out.println("Aclaracion, para crear las claves de la wallet se usan los strings de las variables del usuario junto a funciones de la Clase Math Random,  se deben introducir datos correctos para que se generen correctamente las claves");
        System.out.println("Los usuarios deben ser declarados y con sus claves generadas para realizar transacciones");

    }

    public static void mostrarBitcoin() {
        System.out.println(" .----------------.  .----------------.  .-----------------. .----------------.  .-----------------. .----------------.  .----------------. \n"
                + "| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |\n"
                + "| |   ______     | || |     _____    | || | ____  _____  | || |      __      | || | ____  _____  | || |     ______   | || |  _________   | |\n"
                + "| |  |_   _ \\    | || |    |_   _|   | || ||_   \\|_   _| | || |     /  \\     | || ||_   \\|_   _| | || |   .' ___  |  | || | |_   ___  |  | |\n"
                + "| |    | |_) |   | || |      | |     | || |  |   \\ | |   | || |    / /\\ \\    | || |  |   \\ | |   | || |  / .'   \\_|  | || |   | |_  \\_|  | |\n"
                + "| |    |  __'.   | || |      | |     | || |  | |\\ \\| |   | || |   / ____ \\   | || |  | |\\ \\| |   | || |  | |         | || |   |  _|  _   | |\n"
                + "| |   _| |__) |  | || |     _| |_    | || | _| |_\\   |_  | || | _/ /    \\ \\_ | || | _| |_\\   |_  | || |  \\ `.___.'\\  | || |  _| |___/ |  | |\n"
                + "| |  |_______/   | || |    |_____|   | || ||_____|\\____| | || ||____|  |____|| || ||_____|\\____| | || |   `._____.'  | || | |_________|  | |\n"
                + "| |              | || |              | || |              | || |              | || |              | || |              | || |              | |\n"
                + "| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |\n"
                + " '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------' ");
    }

}
