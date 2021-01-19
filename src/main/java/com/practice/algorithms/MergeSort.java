package com.practice.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MergeSort {

    public static void main(String[] args) {
        MergeSort ms = new MergeSort();

        Random random = new Random();
        List<Integer> elements = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            elements.add(random.nextInt(100));
        }

        Integer[] sorted = ms.sort(elements.toArray(new Integer[elements.size()]));

        for (Integer element : sorted) {
            System.out.println("Element: " + element);
        }
    }

    public Integer[] sort(Integer[] elements) {

        if (elements.length == 1) {
            return elements;
        }

        int midPoint = elements.length / 2;

        List<Integer> left = new ArrayList<>();
        for (int i = 0; i < midPoint; i++) {
            left.add(elements[i]);
        }

        List<Integer> right = new ArrayList<>();
        for (int i = midPoint; i < elements.length; i++) {
            right.add(elements[i]);
        }

        Integer[] leftSorted = null;
        if (left.size() >= 1) {
            leftSorted = sort(left.toArray(new Integer[left.size()]));
        }

        Integer[] rightSorted = null;
        if (right.size() >= 1) {
            rightSorted = sort(right.toArray(new Integer[right.size()]));
        }

        List<Integer> merged = merge(leftSorted, rightSorted);
        return merged.toArray(new Integer[merged.size()]);
    }

    private List<Integer> merge(Integer[] left, Integer[] right) {
        List<Integer> merged = new ArrayList<>();

        int index = 0;
        boolean done = false;
        while (!done) {
            if (index < left.length) {
                Integer leftValue = left[index];

                Integer rightValue = null;
                if (index < right.length) {
                    rightValue = right[index];
                }

                if (rightValue != null) {
                    if (leftValue < rightValue) {
                        merged.add(leftValue);
                        merged.add(rightValue);
                    } else {
                        merged.add(rightValue);
                        merged.add(leftValue);
                    }
                }
            }
            index++;

            if (index == Math.max(left.length, right.length)) {
                done = true;
            }
        }
        return merged;
    }
}
