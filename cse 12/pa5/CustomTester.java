/*
 * Name: govinda sahoo
 * Email: gsahoo@ucsd.edu
 * PID: A16378764
 * Sources used: tutors
 *
 * This file is used to test The 
 * files: Course.java , Student.Java and Sanctuary.java
 * 
 * 
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

import static org.junit.Assert.*;

/**
 * The custom tester for PA5, which covers the custom test cases.
 */

public class CustomTester {


     // ----------------Student class----------------
    
     /**
     * Call equals when the argument is a non-Student object.
     */

    @Test
    public void testStudentEquals(){
        
        Student student1 = new Student("Test", "Student", "A12345678");
        Object stu2 = new Object();
    
        assertEquals(false, student1.equals(stu2));
        assertEquals(false, student1.equals(null));


    }


     /**
     * 	Call compareTo to compare two Students that have the same last name and same first name. 
     */

    @Test
    public void testStudentCompareTo(){

        Student student1 = new Student("Test", "Student", "A12345678");
        Student student2 = new Student("Test", "Student", "A12345679");
        Student student3 = new Student("Test", "Student", "A12345678");
        Student student4 = new Student("Test", "Student", "A12345677");

        int value= student1.compareTo(student2);
        int value2= student3.compareTo(student4);
        assertTrue(null, value<0);
        assertTrue(null, value2>0);


    }

     /**
     * Attempt to drop a non-existing student with a non-empty course roster.
     */

    @Test
    public void testCourseDrop(){

        Course course = new Course("CSE", "12", "Data Structure", 4);
        course.enrolled = new HashSet<>();
        Student stu1 = new Student("Whale", "Ocea", "A121");
        Student stu2 = new Student("Whales", "Ocean", "A122");
        Student stu3 = new Student("Whales", "cean", "A123");
        Student stu4 = new Student("Whales", "Ocean", "A124");
        course.enrolled.add(stu1);
        course.enrolled.add(stu2);
        course.enrolled.add(stu3);

        assertFalse(null, course.drop(stu4));
        assertEquals(course.enrolled.size(), 3);
        assertTrue(null, course.enrolled.contains(stu1));
        assertTrue(null, course.enrolled.contains(stu2));
        assertTrue(null, course.enrolled.contains(stu3));


    }

     /**
     * Attempt to enroll a valid student into a course that is already at maximum capacity.
     */

    @Test
    public void testCourseEnroll(){
        Course course = new Course("CSE", "12", "Data Structure", 4);
        course.enrolled = new HashSet<>();
        Student stu1 = new Student("Whale", "Ocea", "A121");
        Student stu2 = new Student("Whales", "Ocean", "A122");
        Student stu3 = new Student("Whales", "cean", "A123");
        Student stu4 = new Student("Whales", "Ocean", "A124");
        course.enrolled.add(stu1);
        course.enrolled.add(stu2);
        course.enrolled.add(stu3);
        course.enrolled.add(stu4);




        Student stu5 = new Student("cow", "rich", "A223");

        assertFalse(null, course.enroll(stu5));
        assertEquals(course.enrolled.size(), 4);
        assertTrue(null, !course.enrolled.contains(stu5));
    }

     /**
     * Call getRoster on a course with a large number of enrolled students.
     */

    @Test
    public void testCourseGetRoster(){
        
        
        ArrayList<Student> exp= new ArrayList<Student>();
        Course course = new Course("CSE", "12", "Data Structure", 9);
        course.enrolled = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            course.enrolled.add(new Student("cow"+i, ""+i, "A123"));
            exp.add(new Student("cow"+i, ""+i, "A123"));
          
        }

        //getroster store it in an arraylist
        // sort the arraylist of last names

        //for loop to compare roster's element's lastname with lastname in the arraylist
        
        
        assertEquals(exp, course.getRoster());



    }

     /**
     * Call the Sanctuary constructor with a negative argument for maxAnimals.
     */

    @Test (expected = IllegalArgumentException.class)
    public void testSanctConstructor(){
        Sanctuary sanct = new Sanctuary(-500, 50);



    }

     /**
     * rescue animals from an existing species, 
     * where rescuing num animals will cause the number of animals
     *  to exceed the sanctuary's max capacity. 
     */
    @Test
    public void testSanctRescuePartial(){

        Sanctuary sanct = new Sanctuary(100, 5);
        sanct.sanctuary.put("wolf", 40);
        sanct.sanctuary.put("alpaca", 40);
        sanct.sanctuary.put("Horse", 5);
        sanct.sanctuary.put("pony", 10);

        int temp=sanct.rescue("mice",15);
        assertEquals(10, temp);
        assertTrue(sanct.sanctuary.containsKey("mice"));
        assertEquals(5, (int) sanct.sanctuary.get("mice"));

        //existing element 

        Sanctuary sanct2 = new Sanctuary(100, 5);
        sanct2.sanctuary.put("wolf", 40);
        sanct2.sanctuary.put("alpaca", 40);
        sanct2.sanctuary.put("cow", 2);

        int temp2=sanct2.rescue("wolf", 5);
        assertEquals(0,temp2);
        

        sanct2.rescue("alpaca", 5);
        assertEquals(45, (int) sanct2.sanctuary.get("alpaca"));
        assertTrue(sanct2.sanctuary.containsKey("wolf"));
        assertTrue(sanct2.sanctuary.containsKey("alpaca"));

        assertEquals(45, (int) sanct2.sanctuary.get("alpaca"));
        assertEquals(45, (int) sanct2.sanctuary.get("wolf"));
        
        int total=sanct2.sanctuary.get("cow")+
        sanct2.sanctuary.get("wolf")+
        sanct2.sanctuary.get("alpaca");
        assertEquals(92, total);
        assertEquals(3, sanct2.sanctuary.size());


    }

     /**
     * rescue a new non-null species when the sanctuary is 
     * already at the max capacity for species.
     */
    @Test
    public void testSanctRescueMaxSpecies(){
      
        Sanctuary sanct = new Sanctuary(100, 5);
        sanct.sanctuary.put("wolf", 40);
        sanct.sanctuary.put("alpaca", 40);
        sanct.sanctuary.put("Horse", 5);
        sanct.sanctuary.put("pony", 10);
        sanct.sanctuary.put("puma", 5);

        assertEquals(5, sanct.rescue("cow",5));
        assertEquals(10, sanct.rescue("mice",10));



    }
 /**
     * release some (not all) of the animals of an existing species in the sanctuary.
     */
    @Test
    public void testSanctReleasePartial(){

        Sanctuary sanct = new Sanctuary(100, 5);
        sanct.sanctuary.put("wolf", 40);
        sanct.sanctuary.put("alpaca", 40);
        sanct.sanctuary.put("Horse", 5);


        sanct.release("alpaca", 10);
        assertTrue(sanct.sanctuary.containsKey("alpaca"));
        assertEquals(30, (int) sanct.sanctuary.get("alpaca"));




    }
     /**
     * Attempt to release more animals than exists for  
     * s specific animal species in the sanctuary
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSanctReleaseTooMany(){

        Sanctuary sanct = new Sanctuary(50, 5);
        sanct.sanctuary.put("Capybara", 40);
        sanct.sanctuary.put("Horse", 5);

        sanct.release("Horse", 10);

 
    }




}
