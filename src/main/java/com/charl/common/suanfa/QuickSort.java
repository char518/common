package com.charl.common.suanfa;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-08-08 14:24
 **/
public class QuickSort {

    private int[] arr;

    public QuickSort(int[] arr) {
        this.arr = arr;
    }

    public void qSort(int left, int right) {
        if(left > right) {
            return;
        }

        int baseV = arr[left];
        int li = left;
        int ri = right;

        int temp = 0;
        while (li != ri) {
            while (arr[ri] >= baseV && ri > li) {
                ri--;
            }
            while (arr[li] <= baseV && ri > li) {
                li++;
            }

            if (ri > li) {
                temp = arr[ri];
                arr[ri] = arr[li];
                arr[li] = temp;
            }
        }

        arr[left] = arr[li];
        arr[li] = baseV;

        qSort(left, li - 1);
        qSort(ri + 1, right);
    }

    public static void main(String[] args) {
        int[] arr = {3,20,4,5,1,2,10,12};
        QuickSort quickSort = new QuickSort(arr);

        for (int i : arr) {
            System.out.print(i + ",");
        }
        System.out.println();

        quickSort.qSort(0,arr.length - 1);

        for (int i : arr) {
            System.out.print(i + ",");
        }
        System.out.println();


    }
}
