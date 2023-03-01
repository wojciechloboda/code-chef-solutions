package com.company;

import java.util.ArrayList;
import java.util.Scanner;

interface ILine{
    double line(double x);
}

public class K1 {
    private static class Vector2d{
        public Vector2d(double x, double y){
            this.x = x;
            this.y = y;
        }
        public double x;
        public double y;
    }

    public static double dist(ArrayList<Vector2d> A, Vector2d point){
        double res = 0.0;

        for(var p : A){
            res += Math.sqrt((p.x - point.x) * (p.x - point.x) + (p.y - point.y) * (p.y - point.y));
        }
        return res;
    }

    private static double solve(ArrayList<Vector2d> A, int n, int a, int b, int c){
        double low = -5001;
        double high = 5001;

        double mid = (high + low) / 2.0;
        double step = 0.001;
        ILine l = (double x) -> ((- a * x - c) / b);
        Vector2d midPoint = new Vector2d(mid, l.line(mid));
        Vector2d left = new Vector2d(mid + step, l.line(mid + step));
        Vector2d right = new Vector2d(mid - step, l.line(mid - step));

        while(!(dist(A, left) > dist(A, midPoint) && dist(A, right) > dist(A, midPoint))){
            if(dist(A, left) < dist(A, right)){
                low = mid;
            }
            else{
                high = mid;
            }

            mid = (low + high) / 2;
            left = new Vector2d(mid + step, l.line(mid + step));
            right = new Vector2d(mid - step, l.line(mid - step));
            midPoint = new Vector2d(mid, l.line(mid));
        }
        return dist(A, midPoint);
    }

    public static void main(String[] args) {
        Scanner myInput = new Scanner(System.in);
        int x = 0;
        int y = 0;

        int t = myInput.nextInt();
        myInput.nextLine();

        for(int i = 0; i < t; i++){
            int n = myInput.nextInt();
            myInput.nextLine();
            ArrayList<Vector2d> A = new ArrayList<>();
            int a = myInput.nextInt();
            int b = myInput.nextInt();
            int c = myInput.nextInt();

            for(int j = 0; j < n; j++){
                x = myInput.nextInt();
                y = myInput.nextInt();
                A.add(new Vector2d(x, y));
            }
            double res = solve(A, n, a, b, c);
            System.out.printf("%.6f%n", res);
            System.out.println();
        }
    }
}
