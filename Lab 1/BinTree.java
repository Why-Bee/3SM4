import java.util.ArrayList;
import java.lang.String;
import java.lang.Math;


class BinTree
{
    private TNode root;

    // Code for TNode:
    /* public class TNode {
    String data;
    TNode left;
    TNode right; // The data members of TNode, left and right are children

    TNode (String s, TNode l, TNode r) {
        data = s;
        left = l;
        right = r;
    }
    }  */

    private String toStr (int i){
        // convert int to string- cannot use Integer methods :(

        String s = "";
        while (i > 0){
            s = (char) (i % 10 + '0') + s;
            i /= 10;
        }
        return s;
    }


    public BinTree(String[] a) throws IllegalArgumentException {
        if (a.length == 0) {
            throw new IllegalArgumentException("Empty array");
        }
        // Build the prefix tree from the array

        // Go through the array and add each element to the tree
        root = new TNode(null, null, null); // the first node will always be an internal node (I)
        TNode curr = root;

        for (int i = 0; i < a.length; i++) {
            String s = a[i];
            // add this string to the tree
            for (int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);
                if (c == '0') {
                    // left path
                    // check if there is a left child
                    if (curr.left == null) {
                        // create a new node
                        curr.left = new TNode(null, null, null);
                    }
                    else {
                        // left child already exists
                        // check if it is an internal node
                        if (curr.left.data != null) {
                            // it is not an internal node
                            throw new IllegalArgumentException("Prefix condition violated!");
                        }
                    }
                    curr = curr.left;
                }
                else if (c == '1') {
                    // right path
                    // check if there is a right child
                    if (curr.right == null) {
                        // create a new node
                        curr.right = new TNode(null, null, null);
                    }
                    else {
                        // right child already exists
                        // check if it is an internal node
                        if (curr.right.data != null) {
                            // it is not an internal node
                            throw new IllegalArgumentException("Invalid character - This is not a prefix tree");
                        }
                    }
                    curr = curr.right;
                }
                else {
                    // invalid character
                    throw new IllegalArgumentException("Invalid argument!");
                }

                // if we are at the end of the string, add the string to the tree
                if (j == s.length() - 1) {
                    // check if the current node is an internal node
                    if (curr.data != null) {
                        // it is not an internal node
                        throw new IllegalArgumentException("Prefix condition violated!");
                    }
                    else {
                        // add the string to the tree
                        curr.data = "c" + toStr(i); // C0, C1, C2, etc.
                        // reset curr to the root
                        curr = root;
                    }
                }
            }
        }
    }

    public void printTree() {
        // Print the tree in prefix form
        printTree(root);
    }

    private void printTree(TNode t) {
        if (t != null) {
            printTree(t.left);
            if (t.data != null) {
                System.out.println("I ");
            }
            else {
                System.out.println(t.data + " ");
            }
            printTree(t.right);
        }
    }

    public void optimize() {
        // Optimize the tree
        // If a tree is not full, it can be optimized by removing some internal nodes while maintaining the prefix property
        
        // check if the tree is full
        if (!isFull(root)) {
            // tree is not full
            // remove internal nodes

            // We must find codewords with common prefixes, and trim them till the common prefix
            // if a node has a single child, it can be removed
            // if a node has 2 children, it cannot be removed 
        }

        optimizer(root);
    }

    private void optimizer(TNode t) {
        // base case
        if (t == null) {
            return;
        }

        // check if the node is an internal node
        if (t.data == null) {
            // internal node
            if (t.left != null && t.right == null) 
            {
                // check if the left child is a leaf node
                // left child exists, but right child does not
                // if the left child is a leaf node, no action is required as the tree could have odd number of codes
                // and if not, we will have to crawl all the one-armed nodes till we find a leaf node
                // (this will be the case if the left node is an internal node, which means it should be deleted out)
                while (t.left.data == null) {
                    // crawl the tree
                    t.data = t.left.data;
                    t.left = t.left.left;
                    t.right = t.left.right;
                }

                // by now, we have reached a leaf node
                // therefore, this fork is optimized
            }

            if (t.left == null && t.right != null) 
            {
                // right child exists, but left child does not
                // if the right child is a leaf node, push it to this node
                // and if not, we will have to traverse all the one-armed nodes till we find a leaf node
                while (t.right.data == null) {
                    // crawl the tree
                    t.data = t.right.data;
                    t.left = t.right.left;
                    t.right = t.right.right;
                }

                // by now, we have reached a leaf node
                // therefore, this fork is optimized
            }
        // traverse the tree
        optimizer(t.left);
        optimizer(t.right);
        }
    }

    private boolean isFull(TNode t) {
        // check if the tree is full
        if (t == null) {
            return true;
        }
        else if (t.left == null && t.right == null) {
            // leaf node
            return true;
        }
        else if (t.left != null && t.right != null) {
            // internal node
            return isFull(t.left) && isFull(t.right);
        }
        else {
            // not full
            return false;
        }
    }

    public ArrayList<String> getCodewords() {
        // Return all the codewords in the tree
        // codewords must be in lexographical order
        ArrayList<String> codewords = new ArrayList<String>();
        getCodeword(root, codewords, "");
        return codewords;
    }

    private void getCodeword(TNode t, ArrayList<String> codewords, String s) {
        // go through the tree and add the codewords to the arraylist
        // if the node is a leaf node, add the string to the arraylist
        if (t.data != null) {
            // leaf node
            codewords.add(s);
        }
        else {
            // internal node
            getCodeword(t.left, codewords, s + "0");
            getCodeword(t.right, codewords, s + "1");
        }

    }

    public String[] toArray()
    {
        // Return an array of strings that represents the prefix tree
        // must be in lexographical order
        // Use level order traversal (BFS)

        // Get the height of the tree
        int height = getHeight(root);
        ArrayList<String> arr = new ArrayList<String>();
        // Root is always 

        for (int i = 0; i < height; i++) {
            // get the strings at this level
            // we can ignore level 0 because it is always the root, which is a null node
            getLevel(1, root, arr, i);
        }

        // the arraylist is populated
        // convert to array, but add a null at the beginning for the root

        String[] a = new String[arr.size() + 1];
        a[0] = null;
        for (int i = 0; i < arr.size(); i++) {
            a[i + 1] = arr.get(i);
        }

        return a;
    }

    private void getLevel(int currLevel, TNode t, ArrayList<String> arr, int level) {
        // method code taken from COMP ENG 2SI3 lecture notes and adapted
        // base case
        if (t == null) {
            return;
        }

        // if the height is matching the current level, add the string to the array
        if (level == 0) {
            // this is reached when the recursion reaches the level
            // however, we do not know if every node is populated through the recursion
            while (arr.size() < currLevel) {
                arr.add(null); // this is to ensure that the array is populated
            }

            // there are 2 cases- internal node or leaf node
            if (t.left == null && t.right == null) {
                // leaf node
                arr.set(currLevel - 1, t.data); // set the string at the current level
            }
            else {
                // internal node
                arr.set(currLevel - 1, "I");
            }
        }
        else { 
            // otherwise, go to the next level
            getLevel(currLevel*2, t.left, arr, level - 1); // left child
            getLevel(currLevel*2 + 1, t.right, arr, level - 1); // right child
        }

    }

    private int getHeight(TNode t) {
        if (t == null) {
            return 0;
        }
        else {
            return 1 + Math.max(getHeight(t.left), getHeight(t.right));
        }
    }

    public String encode(ArrayList<String> a)
    {
        // Output the sequence of 0s and 1s that represents the encoded message
        // easy implementation using getCodewords()
        String s = "";
        ArrayList<String> codewords = getCodewords();

        for (int i = 0; i < a.size(); i++) {
            // check if the string is in the codewords
            if (!codewords.contains(a.get(i))) {
                // invalid string
                throw new IllegalArgumentException("Invalid string!");
            }
            else {
                // codewords has n elements where each element is a sequence of 0s and 1s
                // as the codewords are lexicoographically ordered, we can use the index of the string in the arraylist
                // to get the binary representation of the index
                int index = (s.charAt(parseInt(a.get(i)))) - '0';
                // this index can be used to get the codeword from code words
                s += codewords.get(index);
            }
        }

        return s;

    }

    public ArrayList<String> decode(String s) throws IllegalArgumentException
    {
        // use this binary tree to decode the string and return the decoded message
        // if the string is not a valid codeword, return null

        // check if the string is a valid codeword
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '0' && s.charAt(i) != '1') {
                // invalid character
                throw new IllegalArgumentException("Invalid codeword!");
            }
        }

        ArrayList<String> arr = new ArrayList<String>();

        // traverse the tree in the manner described by each string
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '0') {
                // left path
                // check if there is a left child
                if (root.left == null) {
                    // invalid codeword
                    throw new IllegalArgumentException("Invalid codeword!");
                }
                else {
                    // left child exists
                    // check if it is a leaf node
                    if (root.left.data != null) {
                        // it is a leaf node
                        // add the string to the arraylist
                        arr.add(root.left.data);
                    }
                    else {
                        // it is an internal node
                        // traverse the tree
                        root = root.left;
                    }
                }
            }
            else if (c == '1') {
                // right path
                // check if there is a right child
                if (root.right == null) {
                    // invalid codeword
                    throw new IllegalArgumentException("Invalid codeword!");
                }
                else {
                    // right child exists
                    // check if it is a leaf node
                    if (root.right.data != null) {
                        // it is a leaf node
                        // add the string to the arraylist
                        arr.add(root.right.data);
                    }
                    else {
                        // it is an internal node
                        // traverse the tree
                        root = root.right;
                    }
                }
            }
            else {
                // invalid character
                throw new IllegalArgumentException("Invalid codeword!");
            }
        }

        // if an exception was thrown, return null
        return arr;

    }

    private int parseInt(String s) {
        // convert string to int
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            num += (s.charAt(i) - '0') * Math.pow(2, s.length() - i - 1);
        }
        return num;
    }

    public String toString()
    {
        // trivial method to output all codewords as a string seperated by spaces
        // use getCodewords()

        ArrayList<String> codewords = getCodewords();
        String s = "";
        for (int i = 0; i < codewords.size(); i++) {
            s += codewords.get(i) + " ";
        }
        return s; // trailing space is necessary as per sample output
    }
}