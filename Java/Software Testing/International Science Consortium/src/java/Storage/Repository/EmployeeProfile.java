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
 * This class is an entity that stores all the information related to an employee (id, userid, type). All attributes of the class are public for easy access.
**/
public class EmployeeProfile extends UserProfile{
	public Integer EmployeeID;
    public Integer TypeOfEmployee;

    public EmployeeProfile(UserProfile up)
    {
        this.FirstName = up.FirstName;
        this.LastName = up.LastName;
        this.Locked = up.Locked;
        this.Type = up.Type;
        this.UserID = up.UserID;
        this.UserName = up.UserName;
    }

}
