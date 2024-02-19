/*
 * MaxBinHeap.java
 * Author: Yash Bhatia
 * Date: 16th Feb, 2024
 * Contains code for the MaxBinHeap class for the COMPENG 3SM4 Lab 2
 */

 class MaxBinHeap
 {
    // data menbers (private)
    private int[] heap;
    private int size;

    // constructors
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
        size = a.length; // the first element is the root
        buildHeap();
    }

    // public methods as in spec
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
        return heap[0]; // in a max heap, the max is at the root
    }

    public int deleteMax() throws RuntimeException
    {
        if (size == 0)
        {
            throw new RuntimeException("Heap is empty");
        }
        int max = heap[0];
        heap[0] = heap[size - 1]; // the last element in level order traversal must be put to the root
        size--;
        percolateDown(0); // restore the heap property
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

    // private methods
    private void percolateDown(int i)
    {
        // use the array representation to move a node at index i down to its proper place

        
    }

 }