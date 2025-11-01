/*Múltiples usuarios:
El cajero debe tener 2 o más cuentas, cada una con su PIN y saldo independiente.
Al inicio, el usuario elige qué cuenta quiere usar.
Cambio de PIN:
Agregar una opción al menú para cambiar el PIN.
Validar que el nuevo PIN no sea igual al anterior y confirmarlo dos veces.
*/

package cajeroAutomatico;

import java.util.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class cajeroAutomatico {
    public static void main(String[] args) {
        //Definicion de variables
        //int pinCorrecto = 1234;
        int intentos = 0;
        int pinIngresado;
        int opcionMenu;
        //double saldo = 100000.00;
        int nuevoPin;
        double monto;
        int numeroDeUsuario;

        //creamos una lista de maps como una lista de diccionarios
        List<Map<String, Object>> usuarios = new ArrayList<>();
        //Creacion de cuentas a traves de clases

        HashMap<String, Object> usuario1 = new HashMap<>();
        usuario1.put("Usuario", 2566);
        usuario1.put("PIN", 1994);
        usuario1.put("SALDO", 25000.00);

        HashMap<String, Object> usuario2 = new HashMap<>();
        usuario2.put("Usuario", 2567);
        usuario2.put("PIN", 1995);
        usuario2.put("SALDO", 2000.00);

        HashMap<String, Object> usuario3 = new HashMap<>();
        usuario3.put("Usuario", 2568);
        usuario3.put("PIN", 1996);
        usuario3.put("SALDO", 5000.00);

        //Agregamos los usuarios a nuestra lista de usuarios
        usuarios.add(usuario1);
        usuarios.add(usuario2);
        usuarios.add(usuario3);


        //Objeto scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al Cajero Automatico Patito Feo");


        //Validacion del usuario
        System.out.println("Ingrese su numero de usuario:");
        numeroDeUsuario = scanner.nextInt();

        //Validacion del PIN
        //Uso de bucle do-while que es repetir hasta que..

int indiceUsuario = -1;
        for (int i = 0; i<usuarios.size(); i++) {
            int numeroGuardado = (int) usuarios.get(i).get("Usuario");
          //  System.out.println(usuarios.get(numeroDeUsuario).get(i));

            //comparo si es igual al numero que se ingreso
            if (numeroGuardado == numeroDeUsuario) {
                indiceUsuario = i;
                System.out.println("Usuario encontrado");
            }
        }
        if (indiceUsuario == -1) {
            System.out.println("Usuario no encontrado");
        } else {
            //agarro el usuario con el indice que encontre
            Map<String, Object> usuario = usuarios.get(indiceUsuario);
        do {
                System.out.println("Ingrese el PIN: ");
                pinIngresado = scanner.nextInt();
                intentos++;
                if (pinIngresado != (int) usuario.get("PIN") && intentos < 3) {
                    System.out.println("PIN incorrecto. Intento " + intentos + " de 3.");
                }

            } while (pinIngresado != (int) usuario.get("PIN") && intentos < 3);


            if (pinIngresado == (int) usuario.get("PIN")) {
                System.out.println("Acceso concedido");


                //LOGICA PRINCIPAL DEL CAJERO

                do {
                    System.out.println("Menu principal:");
                    System.out.println("1. Consultar saldo");
                    System.out.println("2. Depositar dinero");
                    System.out.println("3. Retirar dinero");
                    System.out.println("4. Modificar PIN");
                    System.out.println("5. Salir");
                    System.out.println("----------------------");
                    System.out.println("Elija una opcion: ");

                    opcionMenu = scanner.nextInt();
                    switch (opcionMenu) {
                        case 1:
                            System.out.printf("Su saldo actual es: $%.2f%n", usuario.get("SALDO"));
                            break;
                        case 2:
                            System.out.println("Cantidad de dinero que desea depositar: ");
                            monto = scanner.nextDouble();
                            if (monto > 0) {
                                double nuevoSaldo = (double) usuario.get("SALDO") + monto;
                                usuario.put("SALDO", nuevoSaldo);
                                System.out.printf("Deposito realizado. Nuevo saldo: $%.2f%n", usuario.get("SALDO"));
                            } else {
                                System.out.println("Monto no valido.");
                            }
                            break;
                        case 3:
                            System.out.println("Cantidad de dinero que desea retirar: ");
                            monto = scanner.nextDouble();
                            double saldoActual = (double) usuario.get("SALDO");

                            if (monto > 0 && monto <= saldoActual) {
                                double nuevoSaldo = saldoActual - monto;
                                usuario.put("SALDO", nuevoSaldo);
                                System.out.printf("Retiro exitoso. Nuevo saldo: $%.2f%n", nuevoSaldo);
                            } else if (monto > 0) {
                                System.out.println("Fondos insuficientes.");
                            } else {
                                System.out.println("Cantidad invalida.");
                            }
                            break;
                        case 4:
                            System.out.println("Ingrese el nuevo PIN:");
                            nuevoPin = scanner.nextInt();
                            if (nuevoPin == (int) usuario.get("PIN")) {
                                System.out.println("El nuevo PIN no puede ser igual al anterior.");
                            } else {
                                System.out.println("Confirme el nuevo PIN:");
                                int confirmacion = scanner.nextInt();
                                if (confirmacion == nuevoPin) {
                                    usuario.put("PIN", nuevoPin);
                                    System.out.println("PIN actualizado correctamente.");
                                } else {
                                    System.out.println("Los PIN no coinciden.");
                                }
                            }
                            break;

                        case 5:
                            System.out.println("Saliendo del sistema...");
                            break;
                        default:
                            System.out.println("Opcion invalida.");
                            break;
                    }
                } while (opcionMenu != 5);

            } else{
            System.out.println("Ha excedido el numero de intentos. Tarjeta bloqueada.");
        }

        }
        System.out.println("Gracias por usar el cajero.");
        scanner.close();

    }


}
