package com.practice.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuickSort {

    private int PIVOT_INDEX = 0;

    public List<Integer> sort(List<Integer> elements) {
        Integer pivot = elements.get(PIVOT_INDEX);

        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();

        for(int i=0; i<elements.size(); i++) {
            Integer element = elements.get(i);
            if (element <= pivot && i != PIVOT_INDEX) {
                leftList.add(element);
            } else if (element > pivot){
                rightList.add(element);
            }
        }

        if (!leftList.isEmpty() && leftList.size() > 1) {
            leftList = sort(leftList);
        }

        if (!rightList.isEmpty() && rightList.size() > 1) {
            rightList = sort(rightList);
        }

        elements = compactArray(leftList, rightList, pivot);
        return elements;
    }

    private List<Integer> compactArray(List<Integer> leftList, List<Integer> rightList, Integer pivot) {
        List<Integer> l = new ArrayList<>();
        if (!leftList.isEmpty()) {
            l.addAll(leftList);
            l.add(pivot);

            if (!rightList.isEmpty()) {
                l.addAll(rightList);
            }
        } else {
            l.add(pivot);
            l.addAll(rightList);
        }
        return l;
    }

    public static void main(String[] args) {
        QuickSort qs = new QuickSort();

        Random random = new Random();
        List<Integer> elements = new ArrayList<>();
        for(int i=0; i<100; i++) {
            elements.add(random.nextInt(1000));
        }

        elements = qs.sort(elements);

        for(Integer element: elements) {
            System.out.println("Element: " + element);
        }
    }
}
