package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the amount of randomly generated keys you want?");
        int amount = scanner.nextInt();

        System.out.println("\nEnter 0 for randomly generated input keys or 1 for randomly sorted "
            + "input keys. Otherwise, the default is option 0.");
        int choice = scanner.nextInt();

        // Array that will hold the values of the generated keys
        int[] keys;

        switch(choice) {

            case 1 :
                keys = generateAscendingRandomKeys(amount);
                System.out.println("\nAscending Random Keys:");
                break;

            case 2 :
                keys = generateDescendingRandomKeys(amount);
                System.out.println("\nDescending Random Keys:");
                break;

            default :
                keys = generateRandomKeys(amount);
                System.out.println("\nRandom Keys:");
                break;
        }

        // Display the keys
        for (int i = 0; i < keys.length; i++) {
            System.out.println(keys[i]);
        }

        // Stores the value of the keys array as an text file
        write("src/Input/TestRun.txt", keys);

        // Reads the keys value from the text file
        Scanner s = new Scanner(new File("src/Input/TestRun.txt"));

        // Stores the values read from the input file
        int[] input = new int[amount];
        int temp = 0;
        while (s.hasNextInt()) {
            input[temp++] = s.nextInt();
        }

        // Start time for Time Complexity
        long startTime = System.nanoTime();

        // OBST Algorithm





    }

    public static int[] generateRandomKeys(int amount) {
        int[] Numbers = new int[amount];
        Random random = new Random();

        for (int i = 0; i < Numbers.length; i++) {
            Numbers[i] = random.nextInt();
        }
        return Numbers;
    }

    public static int[] generateAscendingRandomKeys(int amount) {
        int[] Numbers = new int[amount];
        Random random = new Random();

        for (int i = 0; i < Numbers.length; i++) {
            Numbers[i] = random.nextInt();
        }

        Arrays.sort(Numbers);
        return Numbers;
    }

    public static int[] generateDescendingRandomKeys(int amount) {
        int[] Numbers = new int[amount];
        Random random = new Random();

        for (int i = 0; i < Numbers.length; i++) {
            Numbers[i] = random.nextInt();
        }

        Arrays.sort(Numbers);

        int temp;

        for (int i = 0; i < Numbers.length; i++) {
            for (int j = i; j < Numbers.length; j++) {
                temp = Numbers[i];
                Numbers[i] = Numbers[j];
                Numbers[j] = temp;
            }
        }
        return Numbers;
    }

    public static void write(String filename, int[] x) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (int i = 0; i < x.length; i++) {
            writer.write(Integer.toString(x[i]));
            writer.newLine();
        }
        writer.flush();
        writer.close();
    }











}
