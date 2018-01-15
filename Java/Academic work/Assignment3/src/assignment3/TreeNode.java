package assignment3;

/*
This project created by 
Adam Andrade
*/


/*
TreeNode class. store x/y coordinates, and has link to a left and right subtree.
must be initiated with coordinates, tree parameters are optional
*/
public class TreeNode {
    int x; //stores x coordiate
    int y; //stores y coordinate
    BinarySearchTree left; //stores a binary tree left child
    BinarySearchTree right; //stores a binary tree right child
    
    public TreeNode(int x, int y){//O(1)
              this.x = x;// constant time
              this.y = y;// constant time
              this.left = new BinarySearchTree();// constant time
              this.right = new BinarySearchTree();// constant time
    }
    
    public TreeNode(int x, int y, BinarySearchTree left, BinarySearchTree right){//O(1)
              this.x = x;// constant time
              this.y = y;// constant time
              this.left = left;// constant time
              this.right = right;// constant time
    }
    
    //overwritten equals method
    public boolean equals(TreeNode node){ //O(1)
        return (this.x == node.x && this.y ==node.y);// constant time
    }
    
    //overwritten toString method
    public String toString(){//O(1)
        return " ("+ x + "," + y +")";
    }
}