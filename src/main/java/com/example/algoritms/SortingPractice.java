package com.example.algoritms;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class SortingPractice {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите длину массива: ");
        int size = scanner.nextInt();

        int[] array = createRandomArray(size);

        int[] arrayCopy;

        arrayCopy = Arrays.copyOf(array, size);
        long startTime = System.nanoTime();
        selectionSort(arrayCopy);
        long endTime = System.nanoTime();
        System.out.println("Сортировка выбором: " + Arrays.toString(arrayCopy));
        System.out.println("Время выполнения: " + (endTime - startTime) + " наносекунд");

        arrayCopy = Arrays.copyOf(array, size);
        startTime = System.nanoTime();
        insertionSort(arrayCopy);
        endTime = System.nanoTime();
        System.out.println("Сортировка вставкой: " + Arrays.toString(arrayCopy));
        System.out.println("Время выполнения: " + (endTime - startTime) + " наносекунд");

        arrayCopy = Arrays.copyOf(array, size);
        startTime = System.nanoTime();
        mergeSort(arrayCopy, 0, arrayCopy.length - 1);
        endTime = System.nanoTime();
        System.out.println("Сортировка слиянием: " + Arrays.toString(arrayCopy));
        System.out.println("Время выполнения: " + (endTime - startTime) + " наносекунд");

        arrayCopy = Arrays.copyOf(array, size);
        startTime = System.nanoTime();
        quickSort(arrayCopy, 0, arrayCopy.length - 1);
        endTime = System.nanoTime();
        System.out.println("Быстрая сортировка: " + Arrays.toString(arrayCopy));
        System.out.println("Время выполнения: " + (endTime - startTime) + " наносекунд");

        scanner.close();
    }

    public static int[] createRandomArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100);
        }
        System.out.println("Сгенерированный массив: " + Arrays.toString(array));
        return array;
    }

    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }

    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }

    public static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    public static void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(array, left, L, 0, n1);
        System.arraycopy(array, mid + 1, R, 0, n2);

        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
        }
    }

    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    public static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }
}