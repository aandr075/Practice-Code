/*
This project created by group members:
Alain Suarez
Adam Andrade
Carlos Acevedo
Jamelle Welch
*/

import java.util.ArrayList;

/*
traversal class used for traversing trees in various orders, all are returned
as ArrayLists to be used in the GUI

initial call of method to set up empty arraylist, and following methods do 
actual traversal and adds each node to the list.
*/

public class Traversal {
    ArrayList<TreeNode> traverse;

    
    public ArrayList inOrder(BinarySearchTree tree ){// n+2 = O(n)
       traverse = new ArrayList();// constant time
       inOrderArray(tree); //n
       return traverse; // constant
    }
    private void inOrderArray(BinarySearchTree tree){//O(n) will traverse every node once, leaves are constant and have no bearing
        if (tree.getRoot()!=null){//constant
            inOrderArray(tree.getRoot().left);// gets all left subtrees
            traverse.add(tree.getRoot());// constant time
            inOrderArray(tree.getRoot().right); // gets all right subtrees
        }
    }
    // all other traversals are identical to the first one, except the order is
    //rearranged, they will all be O(n) as well
    public ArrayList postOrder(BinarySearchTree tree){
       traverse = new ArrayList();
       postOrderArray(tree);
       return traverse;
    }
    
    private void postOrderArray(BinarySearchTree tree){
        if (tree.getRoot()!=null){
            postOrderArray(tree.getRoot().left);
            postOrderArray(tree.getRoot().right);
            traverse.add(tree.getRoot());
        }
    }
    
    public ArrayList preOrder(BinarySearchTree tree){
        traverse = new ArrayList();
        preOrderArray(tree);
        return traverse;
    }
    
    private void preOrderArray(BinarySearchTree tree){
        if (tree.getRoot()!=null){
            traverse.add(tree.getRoot());
            preOrderArray(tree.getRoot().left);
            preOrderArray(tree.getRoot().right);
            
        }
    }
}
