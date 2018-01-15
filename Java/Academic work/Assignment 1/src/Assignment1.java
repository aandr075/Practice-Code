/*
 * ==============================
 *  Assignment 1
 * ==============================
 * Author: Adam Andrade
 * Date: 1/16/13
 * Due: 1/17/13 11AM
 * I certify that this work was completed by the author listed above and not some other person:
 * 
 * _______________________________________________________________
 * Purpose:
 * This program takes the data from NCAA2012.data. It counts the wins and losses of each team
 * then allows the user to select a team from a list. The program then displays the selected team.
 *  
*/

//Library Imports
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


//Start of the program
public class Assignment1 
{
  //main method  
   public static void main(String[] args) throws IOException 
   {
       /*
        * ====================================
        * This Section Processes the Data 
        * ====================================
        */
       //store the url of the data file as a string
        String location = "http://www.cis.fiu.edu/~kraynek/COP3337-assignments/NCAAAssignments/ncaa2012.data";
        
       //create a URL object using the url of the data file 
        URL url = new URL(location);
        
       //create a new scanner object and open an input stream 
        Scanner ncaaResults = new Scanner(url.openStream());
        
       //assigning the desired delimiter to a string and setting the scanner's delimiter
        String ncaaDelimiter = "[:\n\r0-9]+";
        ncaaResults.useDelimiter(ncaaDelimiter);
        
       // set up the array list for the team
        ArrayList<NCAATeamStats> stats =  new ArrayList<NCAATeamStats>();
        
       //Set up variables to be used in the while loop
        int index; // used to determine the index of the arraylist object
        NCAATeamStats team; // objects to store team info
        boolean win = true; // determines whether a team won or lost
      
       //while loop as long as there are tokens
        while(ncaaResults.hasNext())
        {
           //set up a new team object for the next token 
           team = new NCAATeamStats( ncaaResults.next() );
           
           //checks to see if the arraylist contains the team already
           if (!stats.contains(team))
           {
                stats.add(team);// if not, add the team
           }
           // find the index of that team
           
            index = indexOf(team,stats);

            if (win)// if this team is a winner, increment wins
            {
                stats.get(index).incrementWins();
                win = false;
            }
            else
            {
                //otherwise increment losses
                stats.get(index).incrementLosses();
                win = true;
            }
           
        }//end while loop
        
       // An array of strings is declared, and all the team names are stored in it
        String[] teamArray = new String[stats.size()]; 
        for (int i = 0; i<stats.size(); i++)
        {
            teamArray[i] = stats.get(i).getName();                       
        }       
     
        /*
         * =================================
         * This part interacts with the User
         * =================================
         */
        
       //while loop until user selects cancel 
        while(true)
        {
           //string set to users selection
            String choice = (String) JOptionPane.showInputDialog(null,"Select a team", "NCAA team records",JOptionPane.INFORMATION_MESSAGE, null, teamArray, teamArray[0]);
            if (choice == null) return; // if users hits cancel, end loop
            team = new NCAATeamStats( choice ); //create a new team object with the selected team name
            index = indexOf(team,stats); //retrieve the index of that team from the arraylist
            JOptionPane.showMessageDialog(null,stats.get(index).toString()); //display results
        }
   }   
   /*
    * This method accepts a NCAATeamStats object and compares it to the objects
    * within the arraylist. If the team name is a match, it returns that teams index
    * number from the arraylist
    */
   public static int indexOf (NCAATeamStats team, ArrayList<NCAATeamStats> stats )
   {
       //for loop to check each object in the arraylist
       for (int i = 0; i<stats.size(); i++)
       {
           if(team.equals(stats.get(i))) return i;//if matched, return index
       }
       return -1;//no real purpose
   }
   
}
        
        
     