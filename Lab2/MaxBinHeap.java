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
    private int size = 0;

    // constructors
    public MaxBinHeap(int n)
    {
        if (n < 10) 
        {
            n = 10;
        }
        heap = new int[n];
    }

    public MaxBinHeap(int[] a)
    { 
        heap = a;
        size = a.length; // the first element is used
        buildHeap();
    }

    // public methods as in spec
    public int getSize()
    {
        return size;
    }

    public void insert(int x)
    {
        // if the array is full, double its size
        if (size == heap.length)
        {
            int [] temp = new int[2 * heap.length];
            for (int i = 0; i < heap.length; i++)
            {
                temp[i] = heap[i];
            }
            heap = temp; // temp will be garbage collected
            System.out.println("Heap size doubled to " + heap.length + ". Old size was " + size);
        }
        int i = size;
        // place the new element at the end of the array
        heap[size] = x;
        size++;
        // move the new element up to its proper place
        percolateUp(i);
        
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
        return max;
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
        // sort the given array in place using heap sort, in ascending order
        MaxBinHeap h = new MaxBinHeap(a);
        // algorithm:
        // delete the max element from the heap (this is easy as the max is at the root, and the last element is moved to the root)
        // place the max element at the end of the array
        // repeat until the heap is empty
        // time complexity: O(n log n) because each deleteMax operation takes O(log n) time and in the worst case, we do n of them
        for (int i = a.length - 1; i >= 0; i--)
        {
            a[i] = h.deleteMax();
        }
    }

    // private methods
        
    private void percolateDown(int i)
    {
        // move a node at index i "down" to its proper place
        int left = leftChild(i);
        int right = rightChild(i);
        int largest = i;
        if (left < size && heap[left] > heap[largest]) // if left child exists and is greater
        {
            largest = left;
        }
        if (right < size && heap[right] > heap[largest]) // if right child exists and is greater
        {
            largest = right;
        }

        if (largest != i) // if the largest child is greater than the parent
        {
            int temp = heap[i];
            heap[i] = heap[largest];
            heap[largest] = temp;
            percolateDown(largest); // recursive call to move the new node down further
        }
        // note that if the largest child is not greater than the parent, or the parent has no children, then the heap condition is satisfied
        // and the stack collapses to return to the original call
    }

    private void buildHeap() // this method builds a heap from an array
    {
        // algorithm: start at the last internal node, percolate it down, then move to the next internal node
        int lastInternal = parent(size - 1);
        for (int i = lastInternal; i >= 0; i--)
        {
            percolateDown(i);
        }
    }

    private void percolateUp(int i)
    {
        // move the node at index i "up" to the proper place
        if (i == 0) return; // the root has no parent (i.e., it is at the top of the tree)
        int parent = parent(i);
        if (heap[i] > heap[parent]) // if the new node is greater, then heap condition is violated.
        {
            int temp = heap[i];
            heap[i] = heap[parent];
            heap[parent] = temp;
            percolateUp(parent); // recursive call to move the new node up further
        }
        
    }

    // below methods are helpers so I don't have to deal with the array representation and can treat it 
    // as a binary tree

    private int parent(int i)
    {
        return (i - 1) / 2;
    }

    private int leftChild(int i)
    {
        return 2 * i + 1;
    }

    private int rightChild(int i)
    {
        return 2 * i + 2;
    }

 }