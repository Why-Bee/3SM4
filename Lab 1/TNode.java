public class TNode {
    String data;
    TNode left;
    TNode right; // The data members of TNode, left and right are children

    TNode (String s, TNode l, TNode r) {
        data = s;
        left = l;
        right = r;
    }
}
