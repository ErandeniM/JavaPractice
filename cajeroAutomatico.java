/*Múltiples usuarios:
El cajero debe tener 2 o más cuentas, cada una con su PIN y saldo independiente.
Al inicio, el usuario elige qué cuenta quiere usar.
Cambio de PIN:
Agregar una opción al menú para cambiar el PIN.
Validar que el nuevo PIN no sea igual al anterior y confirmarlo dos veces.
Historial de operaciones:
Guardar en un arreglo las últimas operaciones realizadas (depósitos o retiros).
Al elegir la opción “Ver historial”, mostrar el tipo de operación y el monto.
Comisión de retiro:
Aplicar un 1% o 2% de comisión a cada retiro y mostrar cuánto se descontó.
------------------------------------
Límite de operaciones:
Si el usuario realiza más de 5 operaciones, mostrar “Límite de transacciones alcanzado” y salir.
Registro de hora o fecha de operación:
Cada operación debe registrar una fecha u hora (aunque sea ingresada manualmente).
Opción de consultar movimientos totales:
Mostrar cuántos depósitos, retiros y consultas se realizaron en total.
Personalizar interfaz:
Mejorar los mensajes y presentación del menú (líneas divisoras, espacios, títulos, etc.).
 */


package cajeroautomatico;

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class cajeroautomatico {
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
        int operaciones = 0;
        int cantDepositos=0;
        int cantRetiros = 0 ;
        int cantConsultas = 0;


        //OBJETOS DE FECHAS
        LocalDateTime fechaMov = LocalDateTime.now();
        DateTimeFormatter formatingDate = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String fechaMovFormat = formatingDate.format(fechaMov);

        //HISTORIAL DE MOVIMIENTOS
        LinkedList<String> historialMov = new LinkedList<>();

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
        for (int i = 0; i < usuarios.size(); i++) {
            int numeroGuardado = (int) usuarios.get(i).get("Usuario");

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
                    if (operaciones < 5) {
                        System.out.println("Menu principal:");
                        System.out.println("1. Consultar saldo");
                        System.out.println("2. Depositar dinero");
                        System.out.println("3. Retirar dinero");
                        System.out.println("4. Modificar PIN");
                        System.out.println("5. Consultar historial de movimientos");
                        System.out.println("6. Salir");
                        System.out.println("----------------------");
                        System.out.println("Elija una opcion: ");

                        opcionMenu = scanner.nextInt();

                        switch (opcionMenu) {
                            case 1:
                                System.out.println("╔══════════════════════════════════════╗");
                                System.out.printf("Su saldo actual es: $%.2f%n", (Double) usuario.get("SALDO"));
                                System.out.println("╠══════════════════════════════════════╣");
                                System.out.println(fechaMovFormat);
                                System.out.println("╚══════════════════════════════════════╝");
                                operaciones++;
                                cantConsultas++;
                                break;
                            case 2:
                                System.out.println("╔══════════════════════════════════════╗");
                                System.out.println("Cantidad de dinero que desea depositar: ");
                                System.out.println("╚══════════════════════════════════════╝");

                                monto = scanner.nextDouble();
                                System.out.println("╔══════════════════════════════════════╗");

                                if (monto > 0) {
                                    double nuevoSaldo = (double) usuario.get("SALDO") + monto;
                                    usuario.put("SALDO", nuevoSaldo);
                                    System.out.printf("Deposito realizado. Nuevo saldo: $%.2f%n", (Double) usuario.get("SALDO"));
                                    System.out.println("╠══════════════════════════════════════╣");

                                } else {
                                    System.out.println("Monto no valido.");
                                }
                                historialMov.addFirst("Ha depositado: " + monto);
                                System.out.println( "║        " +fechaMovFormat +"           ║");
                                System.out.println("╚══════════════════════════════════════╝");

                                operaciones++;
                                cantDepositos++;
                                break;
                            case 3:
                                System.out.println("╔══════════════════════════════════════╗");
                                System.out.println("Cantidad de dinero que desea retirar: ");
                                System.out.println("╚══════════════════════════════════════╝");

                                monto = scanner.nextDouble();
                                scanner.nextLine();
                                double saldoActual = (double) usuario.get("SALDO");
                                double comision = monto * .01;

                                double montoComision = monto + comision;

                                if (monto > 0 && montoComision <= saldoActual) {
                                    //if (monto > 0 && monto <= saldoActual) {

                                    double nuevoSaldo = saldoActual - montoComision;
                                    usuario.put("SALDO", nuevoSaldo);
                                    System.out.println("╔══════════════════════════════════════╗");

                                    System.out.printf("Retiro exitoso. Nuevo saldo: $%.2f%n", nuevoSaldo);
                                    System.out.printf("Se te ha cobrado una comision de: $%.2f%n", comision);
                                } else if (monto > 0) {
                                    System.out.println("Fondos insuficientes.");
                                } else {
                                    System.out.println("Cantidad invalida.");
                                }

                                historialMov.addLast("Ha retirado: " + monto);
                                System.out.println("╠══════════════════════════════════════╣");

                                System.out.println(fechaMovFormat);
                                System.out.println("╚══════════════════════════════════════╝");
                                operaciones++;
                                cantRetiros++;
                                break;
                            case 4:
                                System.out.println("╔══════════════════════════════════════╗");
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
                                System.out.println("╚══════════════════════════════════════╝");
                                break;
                            case 5:
                                System.out.println("╔══════════════════════════════════════╗");
                                System.out.println("║      HISTORIAL DE MOVIMIENTOS        ║");
                                System.out.println("╠══════════════════════════════════════╣");
                                historialMov.forEach(System.out::println);
                                System.out.println("Consultas: "+ cantConsultas);
                                System.out.println("Depositos: "+ cantDepositos);
                                System.out.println("Retiros: "+ cantRetiros);
                                cantConsultas++;
                                System.out.println("╠══════════════════════════════════════╣");
                                System.out.println("║    Gracias por usar nuestro cajero   ║");
                                System.out.println("╚══════════════════════════════════════╝");

                                break;
                            case 6:
                                System.out.println("Saliendo del sistema...");
                                break;
                            default:
                                System.out.println("Opcion invalida.");
                                break;
                        }
                    } else {
                        System.out.println("Límite de transacciones alcanzado");
                        opcionMenu = 6;
                    }
                } while (opcionMenu != 6);

            } else {
                System.out.println("Ha excedido el numero de intentos. Tarjeta bloqueada.");
            }
        }
        System.out.println("Gracias por usar el cajero.");
        scanner.close();
    }
}
