package org.example;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        NumberGenerator numberGenerator = new NumberGenerator();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();


        numberGenerator.setNumber(number);

        OddNumber oddNumber = new OddNumber(numberGenerator);
        EvenNumber evenNumber = new EvenNumber(numberGenerator);
//        AllNumber allNumber = new AllNumber(numberGenerator);

        oddNumber.start();
        evenNumber.start();
//        allNumber.start();
//
        try {
            oddNumber.join();
            evenNumber.join();
//            for (int i = 0 ; i <= number ; i++){
//                System.out.print(i + ",");
//            }
//            allNumber.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class NumberGenerator {
    private int number;
    private int currentNumber = 1;

    public synchronized void setNumber(int number) {
        this.number = number;
    }

    public synchronized boolean hasNextNumber() {
        return currentNumber <= number;
    }

    public synchronized int getNextNumber() {
        int nextNumber = currentNumber;
        currentNumber++;
        return nextNumber;
    }
}

class OddNumber extends Thread {
    private final NumberGenerator numberGenerator;

    public OddNumber(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    @Override
    public void run() {
        while (numberGenerator.hasNextNumber()) {
            synchronized (numberGenerator) {
                int nextNumber = numberGenerator.getNextNumber();
                if (nextNumber % 2 != 0) {
                    System.out.println(nextNumber + " odd number");
                }
                numberGenerator.notify();
                try {
                    if (numberGenerator.hasNextNumber()) {
                        numberGenerator.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class EvenNumber extends Thread {
    private final NumberGenerator numberGenerator;

    public EvenNumber(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    @Override
    public void run() {
        while (numberGenerator.hasNextNumber()) {
            synchronized (numberGenerator) {
                int nextNumber = numberGenerator.getNextNumber();
                if (nextNumber % 2 == 0) {
                    System.out.println(nextNumber + " even number");
                }
                numberGenerator.notify();
                try {
                    if (numberGenerator.hasNextNumber()) {
                        numberGenerator.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

//class AllNumber extends Thread {
//    private final NumberGenerator numberGenerator;
//
//    public AllNumber(NumberGenerator numberGenerator) {
//        this.numberGenerator = numberGenerator;
//    }
//
//    @Override
//    public void run() {
//        List<Integer> allNumbers = new ArrayList<>();
//        while (numberGenerator.hasNextNumber()) {
//            synchronized (numberGenerator) {
//                numberGenerator.notifyAll();
//                try {
//                    if (numberGenerator.hasNextNumber()) {
//                        numberGenerator.wait();
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        System.out.println("All Numbers: " + allNumbers);
//    }
//}





