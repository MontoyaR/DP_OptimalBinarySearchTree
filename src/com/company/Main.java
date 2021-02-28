package com.company;

import java.io.BufferedWriter;
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

    // Array that will hold the values of the generated keys (then sorted)
    int[] keys = generateAscendingRandomKeys(amount);

    // Stores the value of the keys array as an text file
    writeInteger("src/Input/Keys.txt", keys);
    System.out.println("\nAscending Random Keys:");
    for (int i = 0; i < keys.length; i++) {
      System.out.println(keys[i]);
    }

    // Array containing random probability that sum to 1
    double[] probability = generateRandomProbability((amount * 2) + 1, 1);

    int n = probability.length;

    //Filter probability in ascending order then split
    //Arrays.sort(probability);

    // Split the array containing the probability to create a Pi(Success) and Qi(Failure) array
    double[] tempArray = {0};
    double[] Qi = Arrays.copyOfRange(probability, 0, (n + 1) / 2);
    double[] P = Arrays.copyOfRange(probability, (n + 1) / 2, n);
    double[] Pi = concat(tempArray, P);

    // Probability of Success
    writeDouble("src/Input/Pi_10keys.txt", Pi);
    System.out.println("\nPi[]:");
    for (int i = 0; i < Pi.length; i++) {
      System.out.println(Pi[i]);
    }

    //Probability of Failure
    writeDouble("src/Input/Qi_10keys.txt", Qi);
    System.out.println("\nQi[]:");
    for (int i = 0; i < Qi.length; i++) {
      System.out.println(Qi[i]);
    }

    System.out.println();

    double[][] cost = new double[amount + 1 + 1][amount + 1];

    // Start time for Time Complexity
    long startTime = System.nanoTime();

    // OBST Algorithm
    double[][] root = OptimalBST(Pi, Qi, amount, cost);

    long endTime = System.nanoTime();

    // Runtime for Optimal Binary Search Tree
    long runtime = endTime - startTime;

    printOBST(root, 1, amount, amount);

    double runtimeInSeconds = (double) runtime / 1_000_000_000;
    System.out.println("\nMerge Sort Algorithm runtime: " + runtimeInSeconds + " seconds");

    System.out.println(
        "\nA search cost of this optimal BST is " + cost[1][amount] + "\n");
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

  public static void writeInteger(String filename, int[] x) throws IOException {
    BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
    for (int i = 0; i < x.length; i++) {
      writer.write(Integer.toString(x[i]));
      writer.newLine();
    }
    writer.flush();
    writer.close();
  }

  public static void writeDouble(String filename, double[] x) throws IOException {
    BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
    for (int i = 0; i < x.length; i++) {
      writer.write(Double.toString(x[i]));
      writer.newLine();
    }
    writer.flush();
    writer.close();
  }

  public static void writeMatrix(String filename, double[][] x) throws IOException {
    BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

    int num = 1;

    for (int i = 0; i < x.length; i++) {
      for (int j = 0; j < x[i].length; j++) {
        writer.write(x[i][j] + " ");
        num++;
      }

      writer.newLine();
    }
    writer.flush();
    writer.close();
  }

  public static double[] concat  (double[]a,double[]b){
    if (a == null) return b;
    if (b == null) return a;
    double[] r = new double[a.length+b.length];
    System.arraycopy(a, 0, r, 0, a.length);
    System.arraycopy(b, 0, r, a.length, b.length);
    return r;

  }

  public static double[] generateRandomProbability(int amount, double finalSum) {
    Random random = new Random();

    double randomNum[] = new double[amount];
    double sum = 0;

    for (int i = 0; i < randomNum.length; i++) {
      randomNum[i] = random.nextDouble();
      sum += randomNum[i];
    }

    for (int i = 0; i < randomNum.length; i++) {
      randomNum[i] = (randomNum[i] / sum) * finalSum;
      // 3 Decimal places, easier to read but does not always add to 1
      //randomNum[i] = Math.round( (randomNum[i]  / sum * finalSum) * 1000.0 ) / 1000.0 ;
    }
    return randomNum;
  }

  public static double[][] OptimalBST(double[] p, double q[], int numberOfKeys, double[][] cost)
      throws IOException {
    int n = numberOfKeys;
    double[][] w = new double[n + 2][n + 1];
    double[][] root = new double[n + 1][n + 1];

    for (int i = 0; i <= n; i++) {
      cost[i + 1][i] = q[i];
    }
    for (int i = 0; i <= n; i++) {
      w[i + 1][i] = q[i];
    }

    for (int k = 1; k <= n; k++) {
      for (int i = 1; i <= n - k + 1; i++) {
        int j = i + k - 1;
        cost[i][j] = Integer.MAX_VALUE;
        w[i][j] = w[i][j - 1] + p[j] + q[j];
        for (int r = i; r <= j; r++) {
          double t = cost[i][r - 1] + cost[r + 1][j] + w[i][j];
          if (t < cost[i][j]) {
            cost[i][j] = t;
            root[i][j] = r;
          }
        }
      }
    }
    writeMatrix("src/Matrices/Root_10keys.txt", root);
    writeMatrix("src/Matrices/Cost_10keys.txt", cost);
    writeMatrix("src/Matrices/Weight_10keys.txt", w);
    return root;
  }

  public static void printOBST(double[][] root, int min, int max,
      int numberOfKeys) {
    int parent = (int) root[min][max];

    // Construct the root of optimal BST
    if (max == numberOfKeys && min == 1) {
      System.out.println("K" + parent + " is the root.");
    }

    // Construct left sub-tree
    if (min <= parent - 1) {
      System.out.println("K" + (int) root[min][parent - 1] + " is the left child of K" + parent);
      printOBST(root, min, parent - 1, numberOfKeys);
    } else {
      System.out.println("D" + (parent - 1) + " is the left child of K" + parent);
    }

    // Construct right sub-tree
    if (max >= parent + 1) {
      System.out.println("K" + (int) root[parent + 1][max] + " is the right child of K" + parent);
      printOBST(root, parent + 1, max, numberOfKeys);
    } else {
      System.out.println("D" + parent + " is the right child of K" + parent);
    }
  }






}
