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
 * This class is an enumeration of the different types of users: Panelist, Employee, and Administrator, Invalid.
**/
public enum UserType
{
    PANELIST (0),
    EMPLOYEE (1),
    ADMINISTRATOR (2),
    INVALID (3);

    UserType(int type)
    {
        this.type = type;
    }

    static UserType getType(int i)
    {
        switch(i)
        {
                case(0): return PANELIST;
                case(1): return EMPLOYEE;
                case(2): return ADMINISTRATOR;
                default: return INVALID;
        }
    }

    private int type;
}
