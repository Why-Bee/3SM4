/*
 * YoungT.java
 * Author: Yash Bhatia
 * Date: 16th Feb, 2024
 * Contains code for the YoungT class for the COMPENG 3SM4 Lab 2
 */

class YoungT {
    private int[][] Tableau;
    private int num;
    private int INF_VALUE;

    public YoungT(int k, int n, int infinity)
    {
        //construct empty kxn Young tableau
        infinity = infinity<100?100:infinity;
        k = k < 10 ? 10 : k;
        n = n < 10 ? 10 : n;
        Tableau = new int[k][n];
        num = 0;
        INF_VALUE = infinity;
        for (int i = 0; i < k; i++)
            for (int j = 0; j < n; j++)
                Tableau[i][j] = INF_VALUE;
    }

    public YoungT (int[][] a) throws IllegalArgumentException
    {
        if (a.length == 0 || a[0].length == 0)
            throw new IllegalArgumentException("Invalid Tableau");
        int k = a.length;
        int n = a[0].length;
        Tableau = new int[k][n];
        int highest = a[0][0];
        for (int i = 0; i < k; i++) { 
            for (int j = 0; j < n; j++) // go ghrough the array and assemble the tableau
            { // algorithm: find the highest element and set it to infinity
                if (a[i][j] > highest)
                    highest = a[i][j];

                Tableau[i][j] = a[i][j];
            }
        }
        num = k*n;
        INF_VALUE = highest*10;
    }

    // public methods

    public int getNumElem() {
        return num;
    }

    public int getInfinity() {
        return INF_VALUE;
    }

    public boolean isEmpty() {
        return num == 0;
    }

    public boolean isFull() {
        return Tableau[Tableau.length-1][Tableau[0].length-1] != INF_VALUE;
    }

    public int readMin() throws RuntimeException
    {
        if (isEmpty())
            throw new RuntimeException("Tableau is empty");
        return Tableau[0][0];
    }

    public int deleteMin() throws RuntimeException
    {
        if (isEmpty())
            throw new RuntimeException("Tableau is empty");
        int min = Tableau[0][0];
        Tableau[0][0] = INF_VALUE;
        num--;
        // restore the tableau property
        restoreTableau();
        return min;
    }

    public boolean find(int x)
    {
        // recall- all find operations must be O(k+n)
        int i = 0;
        int j = Tableau[0].length-1;
        while (i < Tableau.length && j >= 0)
        {
            if (Tableau[i][j] == x)
                return true;
            else if (Tableau[i][j] < x)
                i++;
            else
                j--;
        }
        return false;
    }

    public String toString() 
    {
        String s = "";
        // return tableau as a string
        // raster scan

        for (int i = 0; i < Tableau.length; i++)
        {
            for (int j = 0; j < Tableau[0].length; j++)
            {
                if (Tableau[i][j] == INF_VALUE)
                    s += "infinity, ";
                else
                    s += Tableau[i][j] + ", ";
            }
            s += "\n";
        }
        // remove the last comma, space and newline
        return s.substring(0, s.length() - 3);
    }

    // private methods
    private void restoreTableau()
    { // "staircase traversal" algorithm
        int i = 0;
        int j = 0;
        while (i < Tableau.length && j < Tableau[0].length)
        {
            int down = i+1 < Tableau.length ? Tableau[i+1][j] : INF_VALUE;
            int right = j+1 < Tableau[0].length ? Tableau[i][j+1] : INF_VALUE;
            if (down == INF_VALUE && right == INF_VALUE)
                break;
            if (down < right)
            {
                Tableau[i][j] = down;
                Tableau[i+1][j] = INF_VALUE;
                i++;
            }
            else
            {
                Tableau[i][j] = right;
                Tableau[i][j+1] = INF_VALUE;
                j++;
            }
        }
    }

}
