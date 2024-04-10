package ex01;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader readerA = new BufferedReader(new FileReader(args[0]));
        BufferedReader readerB = new BufferedReader(new FileReader(args[1]));
        StringBuilder res = new StringBuilder();

        String line;
        while ((line = readerA.readLine()) != null){
            res.append(line);
        }
        String[] fileA = res.toString().split(" ");

        res.append(" ");
        StringBuilder fB = new StringBuilder();
        while ((line = readerB.readLine()) != null){
            res.append(line);
            fB.append(line);
        }
        String[] fileB = fB.toString().split(" ");
        readerA.close();
        readerB.close();

       // System.out.println(res);
        Set<String> dictionary = new TreeSet<>();
        String[] tmp = res.toString().split(" ");
        for (String s : tmp){
            dictionary.add(s);
        }

        Vector<Integer> A = new Vector<>(dictionary.size());
        Vector<Integer> B = new Vector<>(dictionary.size());
        checkCount(dictionary,A, fileA);
        checkCount(dictionary,B, fileB);


        String result = String.format("%.2f",numeratorAB(A, B) / denominatorAB(A, B));
        System.out.println("Similarity = " + result);

    }
    public static void checkCount(Set<String> dic, Vector<Integer> v, String[] arr){
        for (String s : dic) {
           int count = 0;
            for (int i = 0; i < arr.length; i++) {

                if(s.equals(arr[i])){
                    count++;
                }
            }
            v.add(count);
        }
    }

    public static Integer numeratorAB(Vector<Integer> a, Vector<Integer> b){
        int sum = 0;
        for (int i = 0; i < a.size(); i++) {
           sum += a.get(i) * b.get(i);

        }
        return sum;
    }

    public static Double denominatorAB(Vector<Integer> a, Vector<Integer> b){
        Double sumA = 0.0;
        Double sumB = 0.0;
        for (int i = 0; i < a.size(); i++) {
            sumA += pow(a.get(i), 2);
            sumB += pow(b.get(i), 2);
        }
        return sqrt(sumA) * sqrt(sumB);
    }

}
