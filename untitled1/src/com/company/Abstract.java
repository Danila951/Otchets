package com.company;

abstract class Sortinger {
    public abstract void sort(int[] arr);

    public long sotWithTime(int[] arr) {
        long mills = System.currentTimeMillis();
        sort(arr);
        long mills2 = System.currentTimeMillis();
        return mills2-mills;
    }
}


 class Test1 extends Sortinger {

    public void sort(int[] arr) {
        boolean sorted = false;
        int temp;
        while(!sorted) {
            sorted = true;
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] > arr[i+1]) {
                    temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                    sorted = false;
                }
            }
        }
    }
}

class Test2 extends Sortinger {

    public void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int current = arr[i];
            int j = i - 1;
            while(j >= 0 && current < arr[j]) {
                arr[j+1] = arr[j];
                j--;
            }
            // в этой точке мы вышли, так что j так же -1
            // или в первом элементе, где текущий >= a[j]
            arr[j+1] = current;
        }
    }
}

class Test3 extends Sortinger {

    public void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            int minId = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    minId = j;
                }
            }
            // замена
            int temp = arr[i];
            arr[i] = min;
            arr[minId] = temp;
        }
    }
}
