package Intro.course;

import java.util.ArrayList;
import java.util.Objects;

public class Teacher implements Comparable<Teacher> {
    
    protected ArrayList<Course> schedule = new ArrayList<Course>();
    protected int id;
    protected String firstname;
    protected String lastname;
    protected static int nextID;

    public Teacher()
    {
        firstname = "John";
        lastname = "Doe";
        id = nextID++;
    }

    public Teacher(String lastname, String firstname)
    {
        this.lastname = lastname;
        this.firstname = firstname;
        id = nextID++;
    }

    /*
     * Takes in "Lastname Firstname" and seperates 
     * the two
     */
    public Teacher(String name)
    {
        int space = name.charAt(' ');
        firstname = name.substring(0, space);
        lastname = name.substring(space + 1);
        id = nextID++;
    }

    public Teacher(String lastname, String firstname, int id, ArrayList<Course> schedule)
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


    @Override
    public int hashCode() {
        return Objects.hash(schedule, id, firstname, lastname);
    }


    public ArrayList<Course> getSchedule() {
        return this.schedule;
    }

    public void setSchedule(ArrayList<Course> schedule) {
        this.schedule = schedule;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst() {
        return this.firstname;
    }

    public void setFirst(String firstname) {
        this.firstname = firstname;
    }

    public String getLast() {
        return this.lastname;
    }

    public void setLast(String lastname) {
        this.lastname = lastname;
    }


    @Override
    public String toString() {
        return "{" +
            " schedule='" + getSchedule() + "'" +
            ", id='" + getId() + "'" +
            ", firstname='" + getFirst() + "'" +
            ", lastname='" + getLast() + "'" +
            "}";
    }

    public void addCourseToSchedule(Course course)
    {
        schedule.add(course);
    }

    public void removeCourseFromSchedule(Course course)
    {
        schedule.remove(course);
    }

    public boolean isAvailable(int period)
    {
        for (Course c: schedule)
            if (c.getPeriod() == period)
               return false;
        return true;
    }

    public Course getPeriodXCourse(int period)
    {
        for (Course c: schedule)
        {
            if (c.getPeriod() == period) return c;
        }
        return null;
    }

    public boolean isTeachingCourse(Course course)
    {
        return schedule.contains(course);
    }
}
