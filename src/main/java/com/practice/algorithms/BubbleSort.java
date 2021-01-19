package com.practice.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BubbleSort {

    public Integer[] sort(Integer[] elements) {
        boolean done = false;
        while (!done) {
            boolean isSwapped = false;
            for (int i = 0; i < elements.length - 1; i++) {
                if (elements[i] > elements[i + 1]) {
                    isSwapped = true;
                    Integer temporary = elements[i];
                    elements[i] = elements[i + 1];
                    elements[i + 1] = temporary;
                }
            }

            if (!isSwapped) {
                done = true;
            }
        }
        return elements;
    }

    public static void main(String[] args) {
        BubbleSort bs = new BubbleSort();

        Random random = new Random();
        List<Integer> elements = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            elements.add(random.nextInt(10000));
        }

        Integer[] sorted = bs.sort(elements.toArray(new Integer[elements.size()]));

        for (Integer element : sorted) {
            System.out.println("Element: " + element);
        }
    }
}
