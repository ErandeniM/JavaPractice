package com.erandeni.areatriangulo;

public class Calcular {
double base;
double altura;

public  Calcular(double base,double altura){
    this.base=base;
    this.altura=altura;
}

public double calcularArea(){
    double area=(base*altura) /2;
    return area;
}


}
