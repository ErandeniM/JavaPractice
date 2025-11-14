package com.erandeni.areatriangulo;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Introduce el valor de la base");
        double base=input.nextDouble();
        input.nextLine();

        System.out.println("Introduce el valor de la altura");
        double altura=input.nextDouble();
        input.nextLine();

        Calcular calculoDelArea = new Calcular(base,altura);
        
        System.out.printf("El area del triangulo es %.2f", calculoDelArea.calcularArea());



    }
}
