package Intro.course;

import java.lang.Comparable;

public class Course implements Comparable<Course>
{
    private String name;
    private String code;
    private int numStudents;


    public Course() 
    {
        name = "";
        code = "";
        numStudents = 0;
    }


    public Course(String name, String code, int numStudents) {
        this.name = name;
        this.code = code;
        this.numStudents = numStudents;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getNumStudents() {
        return this.numStudents;
    }

    public void setNumStudents(int numStudents) {
        this.numStudents = numStudents;
    }

    @Override
    public String toString() {
        return "Course[" +
            " name='" + getName() + "'" +
            ", code='" + getCode() + "'" +
            ", numStudents='" + getNumStudents() + "'" +
            "]";
    }


    @Override
    public boolean equals(Object o) {
        Course course = (Course) o;
        return name.equals(course.name) && code.equals(course.code);
    }

    @Override
    public int compareTo(Course o) {
        return code.compareTo(o.code);
    }

}
