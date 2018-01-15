/*
 * Arelys De La Guardia
 * Deisy Hernandez
 * Michael Smythers
 * Daniel Galano
 * Jairo Pava
 *
 * International Science Consoritum Control System
 *
 * December 1, 2009
 */

package Storage.Repository;

/**
 * This class is an entity that stores all the information related to a panelist (id, userid, institution, address, city, state, zip code, telephone, email, gender, ethnicity, and expertises). All attributes of the class are public for easy access.
**/
public class PanelistProfile extends UserProfile
{
	public String ISCID;
	public String Institution;
	public String Address;
	public String City;
	public String State;
	public String ZipCode;
	public String Telephone;
	public String Email;
	public String Gender;
	public String Ethnicity;
	public String Expertise;

    public String get(String getMe)
    {
        if(getMe.equals("ISCID"))
            return ISCID;
        else if(getMe.equals("Institution"))
            return Institution;
        else if(getMe.equals("Address"))
            return Address;
        else if(getMe.equals("City"))
            return City;
        else if(getMe.equals("State"))
            return State;
        else if(getMe.equals("ZipCode"))
            return ZipCode;
        else if(getMe.equals("Telephone"))
            return Telephone;
        else if(getMe.equals("Email"))
            return Email;
        else if(getMe.equals("Gender"))
            return Gender;
        else if(getMe.equals("Ethnicity"))
            return Ethnicity;
        else if(getMe.equals("Expertise"))
            return Expertise;
        else if(getMe.equals("FirstName"))
            return FirstName;
        else if(getMe.equals("LastName"))
            return LastName;
        else
            return null;
    }

}
