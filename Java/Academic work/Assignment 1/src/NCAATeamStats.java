
/**
 *
 * @author Bill Kraynek
 * modified by Adam Andrade
 * Spring 2013
 * 
 */
public class NCAATeamStats {
    private String teamName;
    private int wins;
    private int losses;
    
    /*
     * Constructor
     */
    public NCAATeamStats (String name) {
        teamName = name;
    }
    
    /*
     * Accessor for team name
     */
    public String getName() {
        return teamName;
    }
    
    /*
     * Accessor for team wins
     */
    public int getWins() {
        return wins;
    }
    
    /*
     * Accessor for team losses
     */
    public int getLosses() {
        return losses;
    }
    
    /*
     * Modifier for team wins
     */
    public void incrementWins() {
        wins++;
    }
    
    /*
     * Modifier for team losses
     */
    public void incrementLosses() {
        losses++;
    }
    
    @ Override
    public String toString() {
        return teamName + " had " + wins + " win" + (wins==1?"":"s") + " and " + losses + " loss" + (losses==1?"":"es") + 
               " in the NCAA Basketball Tournament\n";
    }
    
    
    @ Override
    public boolean equals(Object o)     
    {
        if( !( o instanceof NCAATeamStats ) ) return false;
        return getName().equals(((NCAATeamStats)o).getName());
    }
    
    @ Override
    
    public int hashCode() 
    {
		return getName().hashCode();
    }
    
}
