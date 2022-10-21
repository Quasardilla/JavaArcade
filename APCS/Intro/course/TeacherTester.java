package Intro.course;

import java.util.ArrayList;
import java.util.Collections;

//Mr. Uhl
//Program description: Code used to test the Teacher class
//                     Teacher objects also hold a list of Course objects

public class TeacherTester
{
   public static void main(String[] args)
   {
      Teacher t1 = new Teacher();
      Teacher t2 = new Teacher("Jobs", "Steve");
      Teacher t3 = new Teacher("Bill Gates");
      
      Course c1 = new Course("AP Comp Sci", "MA6704", 24, 1);
      Course c2 = new Course("English 2", "EN2400", 20, 3);
      Course c3 = new Course("Biology", "SC1400", 22, 6);
      Course c4 = new Course("Photograhy", "AR5410", 15, 4);
      
      t2.addCourseToSchedule(c4);
      
      ArrayList<Course> courses = new ArrayList<Course>();
      courses.add(c1);courses.add(c2);courses.add(c3);
      
      Teacher t4 = new Teacher("Smith", "John", 103141, courses);
      
      ArrayList<Teacher> teachers = new ArrayList<Teacher>();
      teachers.add(t1);
      teachers.add(t2);
      teachers.add(t3);
      teachers.add(t4);
      teachers.add(new Teacher("Gates", "Adam"));
      teachers.add(new Teacher("Krabappel", "Edna"));
      
      t4.removeCourseFromSchedule(c2);
      teachers.get(2).addCourseToSchedule(c2);
      
      int period = 3;
      if(t3.isAvailable(period))
         System.out.println(t3.getFirst() + " " + t3.getLast() + " is available period " + period);
      else
         System.out.println(t3.getLast() + " is NOT available period " + period);
      
      Course course = new Course("Biology", "SC1400", 22, 6);
      for(Teacher t : teachers)
         if(t.isTeachingCourse(course))
            System.out.println("The teacher of " + course.getName() + " is " + t.getFirst() + " " + t.getLast());
      
      System.out.println();
      System.out.println("***** The initial teacher list *****");
      for(Teacher t : teachers)
         System.out.println(t);
      System.out.println();
      
      Collections.sort(teachers);
      System.out.println("***** The sorted teacher list *****");
      for(Teacher t : teachers)
         System.out.println(t);
      System.out.println();
   }
}

//THIS IS THE EXPECTED OUTPUT FOR THE TESTER CODE ABOVE...

//Bill Gates is available period 3
//The teacher of Biology is John Smith
//
//***** The initial teacher list *****
//Teacher [last=LastName, first=FirstName, id=100000, schedule=[]]
//Teacher [last=Jobs, first=Steve, id=100001, schedule=[Course [name=Photograhy, code=AR5410, numStudents=15, period=4]]]
//Teacher [last=Gates, first=Bill, id=100002, schedule=[Course [name=English 2, code=EN2400, numStudents=20, period=3]]]
//Teacher [last=Smith, first=John, id=103141, schedule=[Course [name=AP Comp Sci, code=MA6704, numStudents=24, period=1], Course [name=Biology, code=SC1400, numStudents=22, period=6]]]
//Teacher [last=Gates, first=Adam, id=100003, schedule=[]]
//Teacher [last=Krabappel, first=Edna, id=100004, schedule=[]]
//
//***** The sorted teacher list *****
//Teacher [last=Gates, first=Adam, id=100003, schedule=[]]
//Teacher [last=Gates, first=Bill, id=100002, schedule=[Course [name=English 2, code=EN2400, numStudents=20, period=3]]]
//Teacher [last=Jobs, first=Steve, id=100001, schedule=[Course [name=Photograhy, code=AR5410, numStudents=15, period=4]]]
//Teacher [last=Krabappel, first=Edna, id=100004, schedule=[]]
//Teacher [last=LastName, first=FirstName, id=100000, schedule=[]]
//Teacher [last=Smith, first=John, id=103141, schedule=[Course [name=AP Comp Sci, code=MA6704, numStudents=24, period=1], Course [name=Biology, code=SC1400, numStudents=22, period=6]]]

