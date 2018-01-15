

package assignment3;

/*
This project created by 
Adam Andrade
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.util.Scanner;
import javax.swing.JOptionPane;


public class Assignment3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        
        BinarySearchTree tree = new BinarySearchTree();
        File file; //file to be scanned
        Scanner read; // scanner that reads the file
        PrintWriter write;
        String action; //used to determine what action to perform
        int x;// x coordinate
        int y;//y coordinate
        
        //used for rectangle search
        TreeNode corner1; // one corner
        TreeNode corner2; // second corner
        
        //takes user input for file to be read
        file = new File(JOptionPane.showInputDialog("File to be read: \n"
                + "Example: \"input.txt\"","input.txt"));
        
        // reads file selected if it exists
        try{
            read = new Scanner(file);
        }
        catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null, "File Not Found");
            return;
        }
        
        //sets delimiter to anything that isn't a number or command letter stated in instructions
        read.useDelimiter("[\\s[^0-9IOS]]+");
        
        //creates output file
        write = new PrintWriter("output.txt");
       
        //while tokens exist in input
        while(read.hasNext()){
            action = read.next();//take in a command, if it is not I,O or S, repeat loop
            
            //insert action, checks to see if numbers follow
            if (action.equals("I") && read.hasNextInt()){
                if (tree.add(new TreeNode(x = parseInt(read.next()),y = parseInt(read.next())))){
                        write.println("");
                        write.println("Coordinates ( "+x+", "+y+" ) Inserted Successfully!!!");
                }
                else{
                        write.println("");
                        write.println("Insert Coordinates ( "+x+", "+y+" ) failed!!!");
                        write.println("**Duplicate coordinates are not allowed**");
                    
                }
            }
            
            //rectangle search // checks to see if numbers follow
            if (action.equals("S") && read.hasNextInt()){
                write.println("");
                write.println("Rectangle search printout: ");
                write.println(tree.searchRectangle(corner1 = new TreeNode(parseInt(read.next()),parseInt(read.next())),corner2 = new TreeNode(parseInt(read.next()),parseInt(read.next()))));
                write.println("Between "+corner1+ " and "+corner2);
            }
            
            //in order traversal
            if (action.equals("O")){
                write.println("");
                write.println("Binary Search Tree in order printout: ");
                write.println(tree.inOrder());
            }
        }
        write.close();//writes output file
    }
    
}
