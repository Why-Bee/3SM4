/*
 * YoungT.java
 * Author: Yash Bhatia
 * Date: 16th Feb, 2024
 * Contains code for the YoungT class for the COMPENG 3SM4 Lab 2
 */

class YoungT {
    private int[][] Tableau;
    private int num;
    static final int INFINITY;

    public YoungT(int k, int n, int infinity)
    {
        //construct empty kxn Young tableau
        infinity = infinity<100?100:infinity;
        k = k < 10 ? 10 : k;
        n = n < 10 ? 10 : n;
        Tableau = new int[k][n];
        num = 0;
        INFINITY = infinity;
    }
}
