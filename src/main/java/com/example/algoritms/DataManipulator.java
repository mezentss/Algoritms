package com.example.algoritms;

import java.util.*;

class RandomDataGenerator {
    private int size;
    private List<Integer> list;
    private int[] array;

    public RandomDataGenerator(int size) {
        this.size = size;
        Random random = new Random();
        list = new ArrayList<>();
        array = new int[size];
        for (int i = 0; i < size; i++) {
            int element = random.nextInt(100);
            list.add(element);
            array[i] = element;
        }
    }

    public List<Integer> getList() {
        return list;
    }

    public int[] getArray() {
        return array;
    }
}

class ElementSwapper {
    public void swapElements(List<Integer> list, int element1, int element2) {
        int index1 = list.indexOf(element1);
        int index2 = list.indexOf(element2);
        if (index1 != -1 && index2 != -1) {
            Collections.swap(list, index1, index2);
        }
    }

    public void swapElements(int[] array, int element1, int element2) {
        int index1 = -1;
        int index2 = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == element1) {
                index1 = i;
            } else if (array[i] == element2) {
                index2 = i;
            }
            if (index1 != -1 && index2 != -1) {
                break;
            }
        }
        if (index1 != -1 && index2 != -1) {
            int temp = array[index1];
            array[index1] = array[index2];
            array[index2] = temp;
        }
    }
}

class ListManipulator {
    private List<Integer> singlyLinkedList;
    private LinkedList<Integer> doublyLinkedList;
    private LinkedList<Integer> doubleEndedLinkedList;

    public ListManipulator(List<Integer> list) {
        this.singlyLinkedList = new ArrayList<>(list);
        this.doublyLinkedList = new LinkedList<>(list);
        this.doubleEndedLinkedList = new LinkedList<>(list);
    }

    public void testListSpeed() {
        testSpeed(singlyLinkedList);
        testSpeed(doublyLinkedList);
        testSpeed(doubleEndedLinkedList);
    }

    private void testSpeed(List<Integer> list) {
        long startTime = System.nanoTime();
        list.get(0);
        list.get(list.size() - 1);
        list.get(list.size() / 2);
        long endTime = System.nanoTime();

        System.out.println("Время выполнения: " + (endTime - startTime) + " наносек");
    }

    public boolean searchElement(List<Integer> list, int element) {
        return list.contains(element);
    }

    public boolean searchElementByIndex(List<Integer> list, int index) {
        if (index >= 0 && index < list.size()) {
            return true;
        }
        return false;
    }

    public void insertElement(List<Integer> list, int element, int index) {
        if (index >= 0 && index <= list.size()) {
            list.add(index, element);
        }
    }

    public void deleteElement(List<Integer> list, int index) {
        if (index >= 0 && index < list.size()) {
            list.remove(index);
        }
    }

    public void performInsertionSort(List<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            int key = list.get(i);
            int j = i - 1;

            while (j >= 0 && list.get(j) > key) {
                list.set(j + 1, list.get(j));
                j = j - 1;
            }
            list.set(j + 1, key);
        }
    }
}

public class DataManipulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите длину массива: ");
        int size = scanner.nextInt();

        RandomDataGenerator dataGenerator = new RandomDataGenerator(size);
        List<Integer> dataList = dataGenerator.getList();
        int[] dataArray = dataGenerator.getArray();

        ElementSwapper elementSwapper = new ElementSwapper();

        System.out.println("Данные:");
        System.out.println("Список: " + dataList);
        System.out.println("Массив: " + Arrays.toString(dataArray));

        System.out.print("\nВведите первый элемент для перемещения: ");
        int element1 = scanner.nextInt();
        System.out.print("Введите второй элемент: ");
        int element2 = scanner.nextInt();

        elementSwapper.swapElements(dataList, element1, element2);
        elementSwapper.swapElements(dataArray, element1, element2);

        System.out.println("\nРезультат:");
        System.out.println("Список: " + dataList);
        System.out.println("Массив: " + Arrays.toString(dataArray));

        ListManipulator listManipulator = new ListManipulator(dataList);

        System.out.println("\nВремя:");
        listManipulator.testListSpeed();

        System.out.print("\nВведите элемент для поиска: ");
        int searchElement = scanner.nextInt();
        System.out.println( listManipulator.searchElement(dataList, searchElement));

        System.out.print("\nВведите индекс для поиска: ");
        int index = scanner.nextInt();
        System.out.println(listManipulator.searchElementByIndex(dataList, index));

        System.out.print("\nВведите элемент для поиска: ");
        int insertElement = scanner.nextInt();
        System.out.print("Введите индекс для поиска: ");
        int insertIndex = scanner.nextInt();
        listManipulator.insertElement(dataList, insertElement, insertIndex);
        System.out.println("Список: " + dataList);

        System.out.print("\nВведите индекс для удаления: ");
        int deleteIndex = scanner.nextInt();
        listManipulator.deleteElement(dataList, deleteIndex);
        System.out.println("Список: " + dataList);

        listManipulator.performInsertionSort(dataList);

        System.out.println("\nРезультат:");
        System.out.println("Список: " + dataList);

        scanner.close();
    }
}
