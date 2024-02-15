/*
 * MaxBinHeap.java
 * Author: Yash Bhatia
 * Date: 16th Feb, 2024
 * Contains code for the MaxBinHeap class for the COMPENG 3SM4 Lab 2
 */

 class MaxBinHeap
 {
    private int[] heap;
    private int size;

    public MaxBinHeap(int n)
    {
        if (n < 10)
        {
            n = 10;
        }
        heap = new int[n];
        size = n;
    }

    public MaxBinHeap(int[] a)
    { // TODO
        heap = a;
        size = a.length;
        buildHeap();
    }

    public int getSize()
    {
        return size;
    }

    public void insert(int x)
    {

    }

    public int readMax() throws RuntimeException
    {
        if (size == 0)
        {
            throw new RuntimeException("Heap is empty");
        }
        return heap[1];
    }

    public int deleteMax() throws RuntimeException
    {
        if (size == 0)
        {
            throw new RuntimeException("Heap is empty");
        }
    }

    public String toString()
    {
        String s = "";
        for (int i = 1; i <= size; i++)
        {
            s += heap[i] + ", ";
        }
        // remove the last comma and space
        return s.substring(0, s.length() - 2);
    }

    public static void sortArray(int[]a)
    {
        // sort in place using Heap Sort
    }
 }