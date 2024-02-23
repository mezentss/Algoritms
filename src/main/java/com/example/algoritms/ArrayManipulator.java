package com.example.algoritms;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class RandomArrayGenerator {
    private int size;
    private int[] array;

    public RandomArrayGenerator(int size) {
        this.size = size;
        array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100);
        }
    }

    public int[] getArray() {
        return array;
    }
}

class OrderedArrayCreator {
    private int[] array;

    public OrderedArrayCreator(int[] array) {
        this.array = array;
    }

    public void createOrderedArray() {
        Arrays.sort(array);
    }

    public int[] getArray() {
        return array;
    }
}

public class ArrayManipulator {
    private int[] array;
    private int size;

    public ArrayManipulator(int[] array) {
        this.array = array;
        this.size = array.length;
    }

    public void printArray() {
        long startTime = System.currentTimeMillis();
        System.out.println(Arrays.toString(array));
        long endTime = System.currentTimeMillis();
        System.out.println("Время выполнения printArray(): " + (endTime - startTime) + " мс");
    }

    public boolean linearSearch(int key) {
        long startTime = System.currentTimeMillis();
        for (int i : array) {
            if (i == key) {
                long endTime = System.currentTimeMillis();
                System.out.println("Время выполнения linearSearch(): " + (endTime - startTime) + " мс");
                return true;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Время выполнения linearSearch(): " + (endTime - startTime) + " мс");
        return false;
    }

    public boolean binarySearch(int key) {
        long startTime = System.currentTimeMillis();
        int result = Arrays.binarySearch(array, key);
        long endTime = System.currentTimeMillis();
        System.out.println("Время выполнения binarySearch(): " + (endTime - startTime) + " мс");
        return result >= 0;
    }

    public void insert(int element, int index) {
        if (index < 0 || index > size) {
            System.out.println("Некорректное значение для индекса");
            return;
        }
        int[] newArray = new int[size + 1];
        System.arraycopy(array, 0, newArray, 0, index);
        newArray[index] = element;
        System.arraycopy(array, index, newArray, index + 1, size - index);
        array = newArray;
        size++;
    }

    public void delete(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Некорректное значение для индекса");
            return;
        }
        int[] newArray = new int[size - 1];
        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(array, index + 1, newArray, index, size - index - 1);
        array = newArray;
        size--;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите размер массива: ");
        int size = scanner.nextInt();

        RandomArrayGenerator randomArrayGenerator = new RandomArrayGenerator(size);
        int[] randomArray = randomArrayGenerator.getArray();
        System.out.println("Рандомный список: " + Arrays.toString(randomArray));

        OrderedArrayCreator orderedArrayCreator = new OrderedArrayCreator(Arrays.copyOf(randomArray, randomArray.length));
        orderedArrayCreator.createOrderedArray();
        int[] orderedArray = orderedArrayCreator.getArray();

        ArrayManipulator randomArrayManipulator = new ArrayManipulator(Arrays
                .copyOf(randomArray, randomArray.length));
        ArrayManipulator orderedArrayManipulator = new ArrayManipulator(Arrays.copyOf(orderedArray, orderedArray.length));

        randomArrayManipulator.printArray();
        orderedArrayManipulator.printArray();

        System.out.print("Введите число для линейного поиска в рандомном массиве: ");
        int searchValueRandom = scanner.nextInt();
        System.out.println(randomArrayManipulator.linearSearch(searchValueRandom));

        System.out.print("Введите число для бинарного поиска в упорядоченном массиве: ");
        int searchValueOrdered = scanner.nextInt();
        System.out.println(orderedArrayManipulator.binarySearch(searchValueOrdered));

        System.out.print("Введите число для добавления в рандомный массив: ");
        int insertValueRandom = scanner.nextInt();
        System.out.print("Введите номер индекса: ");
        int insertIndexRandom = scanner.nextInt();
        randomArrayManipulator.insert(insertValueRandom, insertIndexRandom);
        randomArrayManipulator.printArray();

        System.out.print("Введите номер индекса для удаления из упорядоченного массива: ");
        int deleteIndexOrdered = scanner.nextInt();
        orderedArrayManipulator.delete(deleteIndexOrdered);
        orderedArrayManipulator.printArray();

        scanner.close();
    }
}


