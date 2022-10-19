package Intro.course;

import java.util.ArrayList;

public class Teacher implements Comparable<Teacher> {
    
    protected ArrayList<Course> schedule = new ArrayList<Course>();
    protected int id;
    protected String firstname;
    protected String lastname;
    protected static int nextID;

    Teacher()
    {
        firstname = "John";
        lastname = "Doe";
        id = nextID++;
    }

    Teacher(String lastname, String firstname)
    {
        this.lastname = lastname;
        this.firstname = firstname;
        id = nextID++;
    }

    /*
     * Takes in "Lastname Firstname"
     */
    Teacher(String name)
    {
        int space = name.charAt(' ');
        lastname = name.substring(0, space);
        firstname = name.substring(space + 1);
        id = nextID++;
    }

    Teacher(String lastname, String firstname, int id, ArrayList<Course> schedule)
    {
        this.lastname = lastname;
        this.firstname = firstname;
        this.id = id;
        this.schedule = schedule;
    }

    public boolean equals(String lastname, String firstname, int id)
    {
        if (this.lastname.equals(lastname) && this.firstname.equals(firstname) && this.id == id)
            return true;
        return false;
    }

    @Override
    public int compareTo(Teacher o) {
        if(this.lastname.equals(o.lastname))
            return this.lastname.compareTo(o.lastname);
        else if(this.firstname.equals(o.firstname))
            return this.firstname.compareTo(o.firstname);
        else return o.id - this.id;
        
    }


}
