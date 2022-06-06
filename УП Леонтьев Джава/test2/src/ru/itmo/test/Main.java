package ru.itmo.test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main calculation = new Main();
        System.out.println(Arrays.toString(calculation.Calculate(1, -2, -24)));
    }

    public double[] Calculate(double a, double b, double c) {
        if (a < -100 || a > 100 || b < -100 || b > 100 ||c < -100 || c > 100) {
            return null;
        }

        double D = (b*b)-(4*a*c);


        try {
            if(D<0) {
                return null;
            } else if (D == 0) {
                double[] result = new double[1];
                if (a != 0 ) {
                    result[0] = (-b) / (2 * a);
                    return result;
                } else {
                    if (c != 0 && b!=0) {
                        result[0] = (-c) / b;
                        return result;
                    } else {
                        return null;
                    }
                }
            } else {
                double[] result = new double[2];
                result[1] = ((-b) + Math.sqrt(D)) / (2*a);
                result[0] = ((-b) - Math.sqrt(D)) / (2*a);
                return result;
            }
        } catch (Exception e) {
            return null;
        }

    }
}
