package assignment3;

/*
This project created by 
Adam Andrade
*/
import java.util.ArrayList;

/*
Binary search tree class, stores one treenode. TreeNodes contain links to 
subtrees rather than nodes.

Binary search tree can be initialized with x and y coordinates  or nothing,
which would give an empty tree

All methods verify that the Tree is not empty

*/
public class BinarySearchTree {
    
    private TreeNode root;
    
    public BinarySearchTree(){
        root = null;
    }
    public BinarySearchTree(TreeNode node ){
        root = node;
    }
    
    
    /*
        a search function that will find any node and return it from the tree
        method is unused, but wording of instructions suggests it is necessary
        "The binary search property is defined differently for even levels (root is at level 0) and odd levels."
        search alternates to follow level rules.
    */
    
    public TreeNode search(TreeNode node){//O(log n) assuming height != n
        
        return searchEven(node);//starts with even level
    }
    
    public TreeNode searchEven(TreeNode node){//O(log n) assuming height != n
        if (root == null) return null;//constant
        if (root.equals(node)) return root;//constant
        if (root.x >= node.x)return root.left.searchOdd(node);//log n, only checks left subtree
        return root.right.searchOdd(node);//log n, only checks right subtree        
    }
    
    public TreeNode searchOdd(TreeNode node){//O(log n) assuming height != n
        if (root == null) return null;//constant
        if (root.equals(node)) return root;//constant
        if (root.y >= node.y)return root.left.searchOdd(node);//log n, only checks left subtree
        return root.right.searchOdd(node);//log n, only checks right subtree
    }
    
    /*
        searches binary tree based on coordinates given. looks for coordinates that fall within
        min and max values for both x and y values.
    
        to save time, all nodes typically won't be traversed. if current nodes value (based on current level)
        is less than the max, then the right subtree must be checked, otherwise no values within the range will
        be found in the right subtree. with the left, the value must be greater than or equal to the min to
        require the left subtree to be checked.
    
        example, if the root coordinate's x value is lower than the min, there will be no values in the left subtree
    */
    
    public ArrayList searchRectangle(TreeNode node1, TreeNode node2){//O(log n) assuming height != n
        int[] rangeX = {Math.min(node1.x, node2.x),Math.max(node1.x, node2.x)};//constant
        int[] rangeY = {Math.min(node1.y, node2.y),Math.max(node1.y, node2.y)};//constant
        ArrayList list = new ArrayList();//constant
        searchRectangleEven(list, rangeX, rangeY);// starts with even level
        return list; //returns list
    }
    
    public void searchRectangleEven(ArrayList list, int[] x, int[] y){//O(log n) assuming height != n
        if (root!=null){//constant
            if (x[0] <= root.x){//constant
                if( root.x <= x[1] && y[0] <= root.y && root.y <=y[1]) list.add(root);//constant
                root.left.searchRectangleOdd(list,x,y); //log n, only checks left subtree
            }
            if (root.x < x[1] ) root.right.searchRectangleOdd(list,x,y); //log n, only checks right subtree 
        }
    }
    
    public void searchRectangleOdd(ArrayList list, int[] x, int[] y){//O(log n) assuming height != n
        if (root!=null){//constant
            if (y[0] <= root.y){//constant
                if (root.y <= y[1] && x[0] <= root.x && root.x <=x[1])list.add(root);//constant
                root.left.searchRectangleEven(list,x,y);//log n, only checks left subtree     
                }
            if (root.y < y[1]) root.right.searchRectangleEven(list,x,y);//log n, only checks right subtree
        }
            
    }
    
    /*
    traversal methods return an array of all nodes in order.
    
    */
    
    public ArrayList inOrder(){// n+2 = O(n)
       ArrayList<TreeNode> traverse = new ArrayList();// constant time
       inOrderArray(traverse); //n
       return traverse; // constant
    }
    
    private void inOrderArray(ArrayList<TreeNode> traverse){//O(n) will traverse every node once, leaves are constant and have no bearing
        if (root!=null){//constant
            root.left.inOrderArray(traverse);// gets all left subtrees
            traverse.add(root);// constant time
            root.right.inOrderArray(traverse); // gets all right subtrees
        }
    }
    
    /*
    
    add method adds a value to the binary search tree, following the rules of 
    tree. add takes a node with the values, then will call the private 
    method addEven for first level. calls alternate between addEven and 
    addOdd depending on which level comparison occurs on. even levels compare
    x coordinates, and y coordinates for odd
    
    a  somewhat recursive method
    
    */
    public boolean add(TreeNode node){//O(log n) assuming height != n
        return addEven(node);//starts on even level
    }
    
    private boolean addEven(TreeNode node){//O(log n) assuming height != n
        if (root == null) {// constant
            root = node;// constant
            return true;// constant
        }
        
        if (root.x >= node.x){//constant
            if (root.equals(node)){// constant
                return false;// constant
            }
            return root.left.addOdd(node);//log n, only left subtrees
        }
            return root.right.addOdd(node);//log n, only right subtrees
    }
    
    private boolean addOdd(TreeNode node){//O(log n) assuming height != n
        if (root == null) {// constant
            root = node;// constant
            return true;// constant
        }
        
        if (root.y >= node.y){//constant
            if (root.equals(node)){// constant
                return false;// constant
            }
            return root.left.addEven(node);//log n, only left subtrees
        }
            return root.right.addEven(node);//log n, only right subtrees
    }
    
}
