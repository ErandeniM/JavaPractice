/*Crear un programa en Java que permita leer 10 nombres de películas,
y la duración de la película que guarde los nombres en un ArrayList y la duración en otro,
 imprima las listas. Luego pida al usuario que introduzca un nombre de película, y luego que muestre su duración.
*/

import java.util.ArrayList;
import java.util.Scanner;

public class movieNamesArrayList {
    public static void main(String[] args) {

        //declaracion de objeto
        ArrayList<String> peliNombre = new ArrayList<>();
        ArrayList<Integer> peliDuracion = new ArrayList<>();
        boolean continuar = true;

        Scanner input = new Scanner(System.in);
//ingresar nombres y duracion
        for (int i = 0; i < 10; i++) {
            System.out.println("Ingrese el nombre de la pelicula numero " + (i + 1) + ": ");
            peliNombre.add(input.nextLine());
            System.out.println("Ingrese la duracion de la pelicula en formato de minutos: " + peliNombre.get(i));
            peliDuracion.add(input.nextInt());
            input.nextLine();
        }

// imprimir la pelicula y la duracion
        for (int i = 0; i < peliNombre.size(); i++) {
            System.out.println("La pelicula: " + peliNombre.get(i) + " tiene una duracion de: " + peliDuracion.get(i) + " minutos.");
        }

        do {
            System.out.println("Ingresa la pelicula para conocer su duracion: ");
            String consultarNombre = input.nextLine();

int posicionConsultarNombre = peliNombre.indexOf(consultarNombre);

//verificar si la peli esta en el arreglo
            if (posicionConsultarNombre != -1) {
                int duracion = peliDuracion.get(posicionConsultarNombre);
                System.out.println("La pelicula: " + consultarNombre + " tiene una duracion de: " + duracion + " minutos.");
            } else {
                System.out.println("La pelicula '" + consultarNombre + "' no se encuentra en la lista.");
            }

            //se repite el loop o no
            System.out.println("Desea buscar la duracion de otra pelicula? Escriba S para continuar");
            String respuesta = input.nextLine();
            if (respuesta.equalsIgnoreCase("s")) {
                continuar = true;
            } else {
                continuar = false;
            }

        } while (continuar == true);
        System.out.println("Saliendo del sistema...");

    }
}

