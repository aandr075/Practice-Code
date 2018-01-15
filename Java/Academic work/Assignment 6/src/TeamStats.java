
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Bill Kraynek modified by Adam Andrade Spring 2013
 */
public class TeamStats implements Comparable<TeamStats> {
    /*========================================
     *             Fields
     ========================================*/

    private String teamName;
    private int wins;
    private int losses;
    //stores games played for each year
    private Map<String, List<Game>> years;
    /*========================================
     *             Constructor
     ========================================*/

    public TeamStats(String name) {
        teamName = name;
        years = new TreeMap<>();
    }
    /*========================================
     *             Accessors
     ========================================*/

    public String getName() {
        return teamName;
    }//end getName

    public int getWins() {
        return wins;
    }//end getWins

    public int getLosses() {
        return losses;
    }//end getLosses
    /*========================================
     *             Modifiers
     ========================================*/

    public void incrementWins() {
        wins++;
    }//end incrementWins

    public void incrementLosses() {
        losses++;
    }//end incrementLosses

    public void addGame(String year, String team, boolean won) {
        //if the year isn't a key of the map, add it
        if (!years.containsKey(year)) {
            years.put(year, new LinkedList<Game>());
        }
        years.get(year).add(new Game(team, won));//add a game to this year
    }//end addGame

    /*========================================
     *             Overridden methods
     ========================================*/
    @ Override//displays various information about the team
    public String toString() {
        //team, number of wins and losses
        String out = teamName + " won " + wins + " game" + (wins != 1 ? "s" : "")
                + " and lost " + losses + " game" + (losses != 1 ? "s" : "") + "\n"
                + " They participated in the following years:\n";
        int tournamentWins = 0;//tournaments won count
        //fills in each year as a line
        for (String year : years.keySet()) {
            //year participated in
            String line = "     In " + year + " they";
            //games won that year
            for (Game game : years.get(year)) {
                if (game.won) {
                    line += (line.contains("beat") ? ", " : " beat ")
                            + game.team;
                }
            }
            //games lost that year
            for (Game game : years.get(year)) {
                if (!game.won) {
                    line += (line.contains("beat") ? " and" : "")
                            + (line.contains("lost to") ? ", " : " lost to ") + game.team;
                }
            }
            //if the tournament was won that year
            if (!line.contains("lost")) {
                line += " and won the tournament!!!";
                tournamentWins++;
            }
            else line+=".";
            out += line + "\n";//ends the line
        }
        //number of tournaments won
        out += "       They won " + tournamentWins + " Tournament" + (tournamentWins != 1 ? "s" : "");
        return out;//returns entire string
    }//end toString

    @ Override//compares team names
    public boolean equals(Object o) {
        if (o == null || !(o instanceof TeamStats)) {
            return false;
        }
        return getName().equals(((TeamStats) o).getName());
    }//end equals

    @ Override//sets object hashcode to it's name's hashcode
    public int hashCode() {
        return getName().hashCode();
    }//ends hashCode

    @Override//TeamStats objects are compared naturally through name
    public int compareTo(TeamStats t) {
        return this.getName().compareTo(t.getName());
    }//end compareTo

    /*========================================
     *             Inner Classes
     ========================================*/
    //a Game object, what team this team played and if this team won
    private class Game {

        String team;
        boolean won;

        public Game(String team, boolean won) {
            this.team = team;
            this.won = won;
        }//end constructor
    }//end inner class game
}//end Teamstats

