package org.example;

import java.util.Scanner;

class Table{
    public void printZoj(int n){
        for (int i = 0 ; i <= n ; i++){
            if (i % 2 == 0) {
                System.out.println(i);
            }
          try {
              Thread.sleep(n);
          }catch (Exception e){
              System.out.println(e);
              notify();
          }
        }
    }
    public void printFard(int n){
        for (int i = 0 ; i <= n ; i++){
            if (i % 2 != 0) {
                System.out.println(i);
            }
            try {
                Thread.sleep(n);
            }catch (Exception e){
                System.out.println(e);
                notify();
            }
        }
    }
}

public class Main {
    public static Scanner scanner = new Scanner(System.in);
        public static void main(String[] args) {
            final Table obj = new Table();
            System.out.println("Enter a number: ");
            int number = scanner.nextInt();
            Thread t1 = new Thread() {
                public void run() {
                    obj.printFard(number);
                }
            };
            Thread t2 = new Thread(){
            public void run(){
                obj.printZoj(number);
            }
            };

            t1.start();
            t2.start();
            }

        }