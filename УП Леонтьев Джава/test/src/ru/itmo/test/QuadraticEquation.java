package ru.itmo.test;

public class QuadraticEquation {

    public static void main(String[] args) {
        QuadraticEquation calculation = new QuadraticEquation();
        System.out.println(calculation.Calculate(1,-10,25));
    }

    public double[] Calculate(double a, double b, double c) {
        if (a < -100 || a > 100 || b < -100 || b > 100 ||c < -100 || c > 100) {
            return null;
        }

        double D = (b*b)-(4*a*c);

        double[] result = new double[0];

        if(D<0) {
            return null;
        } else if (D == 0) {
            result[0] = (-b) / (2*a);
        } else {
            result[0] = ((-b) + Math.sqrt(D)) / (2*a);
            result[1] = ((-b) - Math.sqrt(D)) / (2*a);
        }

        return result;





    }
}
