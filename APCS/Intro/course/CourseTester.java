package Intro.course;

import java.util.ArrayList;
import java.util.Collections;

public class CourseTester 
{
    private static String[] names = {"AP Comp Sci", "English 2", "Biology", "Photography"};
    private static String[] codes = {"MA6704", "EN2400", "SC1400", "AR5410"};
    private static int[] numStuds = {10, 22, 19, 16};

    public static void main(String[] args)
    {
        //1.a) instantiate array
        ArrayList<Course> courses = new ArrayList<>();

        for (int i = 0; i < names.length; i++)
        {
            //1.b) populate array
            courses.add(new Course(names[i], codes[i], numStuds[i]));
            //2.) print course array
            System.out.println(courses.get(courses.size()-1));
        }

        //3.) sort the list
        Collections.sort(courses);

        //4.) print the sorted list
        for (Course c: courses)
            System.out.println(c);

        //5.) create a course c1
        Course c1 = new Course("Biology", "SC1400", 20); 

        //6.a) check if it is equal to a course in courses
        boolean c1Equal = false;
        for (Course c: courses)
            if (c.equals(c1)) c1Equal = true;
        
        //6.b) report result
        System.out.println(c1Equal);
    }
}
