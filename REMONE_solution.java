
// REMONE - code chef problem solution

package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class REMONE_solution {

    private static ArrayList<Long> parseLine(Scanner myInput){
        return Arrays.stream(myInput.nextLine().split(" "))
                .map(Long::parseLong).collect(Collectors.toCollection(ArrayList::new));
    }

    private static long solve(ArrayList<Long> A, ArrayList<Long> B, int n){
        A.sort(Long::compareTo);
        B.sort(Long::compareTo);
        long res = Long.MAX_VALUE;

        long sumA = A.stream().reduce(0L, Long::sum);
        long sumB = B.stream().reduce(0L, Long::sum);

        if((sumB - sumA + A.get(0)) % (n - 1) == 0 && (sumB - sumA + A.get(0)) > 0){
            long x = (sumB - sumA + A.get(0)) / (n - 1);
            if(A.get(n - 1) + x == B.get(n - 2)){
                res = x;
            }
        }

        for(int i = 1; i < A.size(); i++){
            if((sumB - sumA + A.get(i)) % (n - 1) == 0 && (sumB - sumA + A.get(i)) > 0){
                long x = (sumB - sumA + A.get(i)) / (n - 1);
                if(A.get(i - 1) + x == B.get(i - 1)){
                    res = Long.min(x, res);
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Scanner myInput = new Scanner(System.in);

        int t = myInput.nextInt();
        myInput.nextLine();

        for(int i = 0; i < t; i++){
            int n = myInput.nextInt();
            myInput.nextLine();
            var A = parseLine(myInput);
            var B = parseLine(myInput);
            long res = solve(A, B, n);

            System.out.println(res);
        }
    }
}
