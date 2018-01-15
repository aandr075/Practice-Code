/*
 * ==============================
 *  Assignment 6
 * ==============================
 * Author: Adam Andrade
 * Date: 4/10/13
 * Due: 4/11/13 11AM
 * I certify that this work was completed by the author listed above and not some other person:
 * 
 * _______________________________________________________________
 * Purpose:
 * This program takes the data from NCAA2013.data. It counts the wins and losses of each 
 * teamthen allows the user to select a team from a list. The program then displays the 
 * selected team and various statistics about that team
 */
//Library Imports
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Assignment6 {

    public static void main(String[] args) throws IOException {
        /*========================================
         *             Data Retrieval
         ========================================*/
        //scanner scans ncaa2013.data file
        String location = "http://www.cis.fiu.edu/~kraynek/COP3337-assignments/NCAAAssignments/ncaa2013.data";
        URL url = new URL(location);
        Scanner results = new Scanner(url.openStream());
        //assigns appropriate delimiter
        String ncaaDelimiter = "[:\n\r]+";
        results.useDelimiter(ncaaDelimiter);
        //map that stores team names and teamStats object
        Map<String, TeamStats> stats = new TreeMap<>();
        /*========================================
         *             Data Sorting
         ========================================*/
        while (results.hasNext()) {
            //stores the year
            String year = results.next();
            //sets up winning team
            TeamStats team = new TeamStats(results.next());
            String teamNameWin = team.getName();//for use in making Game objects
            if (!stats.containsKey(teamNameWin)) {
                stats.put(teamNameWin, team);
            }
            stats.get(teamNameWin).incrementWins();
            results.next();//skip score
            //sets up losing team 
            team = new TeamStats(results.next());
            String teamNameLose = team.getName();
            if (!stats.containsKey(teamNameLose)) {
                stats.put(teamNameLose, team);
            }
            stats.get(teamNameLose).incrementLosses();
            results.next();//skip score  
            //creates Game objects for each team
            stats.get(teamNameWin).addGame(year, teamNameLose, true);
            stats.get(teamNameLose).addGame(year, teamNameWin, false);
        }//end while loop
        /*========================================
         *             Sorting of teams
         ========================================*/
        //sorts teams by the CompareTeamStats object's rules
        Set<TeamStats> sortTeams = new TreeSet<>(new CompareTeamStats());
        sortTeams.addAll(stats.values());
        String[] teamArray = new String[sortTeams.size()];
        //for loop to make the string array of sorted team names
        int i = 0;
        for (TeamStats team : sortTeams) {
            teamArray[i] = team.getName();
            i++;
        }
        /*========================================
         *             User interface
         ========================================*/
        while (true) {
            //string set to users selection
            String choice = (String) JOptionPane.showInputDialog(null, "Select a team", "NCAA team records", JOptionPane.INFORMATION_MESSAGE, null, teamArray, teamArray[0]);
            if (choice == null) {
                return; // if users hits cancel, end loop
            }            //display user's choice
            JTextArea display = new JTextArea(stats.get(choice).toString(), 32, 55);
            JOptionPane.showMessageDialog(null, new JScrollPane(display), "Sample Game", -1);
        }//end while
    } //end main

    //Comparator Class
    static class CompareTeamStats implements Comparator<TeamStats> {
        //used to sort TeamStats objects first by number of wins, losses then by name

        public int compare(TeamStats team1, TeamStats team2) {
            int diff = team2.getWins() - team1.getWins();
            if (diff != 0) {
                return diff;
            }
            diff = team2.getLosses() - team1.getLosses();
            if (diff != 0) {
                return diff;
            }
            return team1.getName().compareToIgnoreCase(team2.getName());
        }//end compare method
    }//end CompareTeamStats class
}//end Assignment 6

