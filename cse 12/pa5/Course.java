/*
 * Name: govinda sahoo
 * Email: gsahoo@ucsd.edu
 * PID: A16378764
 * Sources used: tutors
 *
 * This file is used to create The clas
 * Which is used to store information of a course
 * stores the details of the students enrolled in it
 * in a hashmap
 */



import java.util.Objects;
import java.util.Objects;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class Creates thr clas course
 * which stores student information that is 
 * enrolled in the course
 * 
 * Instance variables: 
 * capacity= the course's capacity
 * department= the departments name
 * number= number of the course 
 * description = description of the course
 */



public class Course {
    // Instance variables
    HashSet<Student> enrolled;
    private final int capacity;
    private final String department;
    private final String number;
    private final String description;

  /**
     * The constructor 
     * Initializes the course's information and stores it
     * 
     */

    public Course(String department, String number, String description, int capacity){
        // Check that the arguments are valid
        if((department==null)||(number==null)||(description==null)||(capacity<1))
        {throw new IllegalArgumentException();}
        // Set instance variables to constructor arguments
        this.capacity=capacity;
        this.department=department;
        this.number=number;
        this.description=description;
        enrolled = new HashSet<Student>();

        //Initialize the course’s information with an initial enrollment of 0 students.
    }
     /**
     * This method
     * @returns the course's department
     * 
     */

    public String getDepartment(){
        return department;
        //Return the department name.
    }
    /**
     * This method
     * @returns the course's number
     * 
     */
    public String getNumber(){
        return number;
    }
    /**
     * This method
     * @returns the course's description
     * 
     */

    public String getDescription(){
        return description;
    }
    /**
     * This method 
     * @returns the course's capacity
     * 
     */
    public int getCapacity(){
        return capacity;
    }

    /**
     * This method adds the student 
     * to the course
     * @returns true if successfully enrolled
     * @return false otherwise
     */

    public boolean enroll(Student student){
        // Check that the arguments are valid
        if(student==null){throw new IllegalArgumentException();}
        // Check if there is space in the course
        // and if the student is not already enrolled
        if(capacity>enrolled.size() && !(enrolled.contains(student))){
            enrolled.add(student);
            return true;
        }
        else{ 
            return false;
        }

    }

    /**
     * This method drops the student 
     * from the course if they are in it
     * @returns true if successfully dropped
     * @return false otherwise
     */


    public boolean drop(Student student){
        // Check that the arguments are valid
        if(student==null){throw new IllegalArgumentException();}

         // checks if the student is enrolled
        if(enrolled.contains(student)){
            enrolled.remove(student);
            return true;
        }
        else{
            return false;
        }
        
    }
    /**
     * This method drops the student from the course
     * Removes all the students from the course hastset
     */
    public void cancel(){
        enrolled.clear();
    
    }
    /**
     * This method checks
     * If the course is at its capacity
     * @returns true if at capacity
     * @return false otherwise
     */

    public boolean isFull(){
        //checks if at capacity
        if(getCapacity()>enrolled.size()){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * This method 
     * @returns the number of students enrolled in the course
     */

    public int getEnrolledCount(){
       return enrolled.size();
        //Return the current number of enrolled students.
    }
    /**
     * This method calculates
     * the number of avaible seats in the course
     * @returns avaible seats
     */

    public int getAvailableSeats(){
        //calculates the spare capacity
        int spare= getCapacity()-getEnrolledCount();
        return spare;        
        
    }

    /**
     * This method creates a shallow copy of students
     * enrolled in the course and
     * @returns the shallow copy 
     * 
     */

    public HashSet<Student> getStudents(){
        //creates shallow copy
        HashSet cloned_set = new HashSet();
        cloned_set = (HashSet)enrolled.clone();
        return cloned_set;
    }

    /**
     * This method creates an ArrayList 
     * version of the enrolled set and sort it
     * @returns the ArrayList
     * 
     */


    public ArrayList<Student> getRoster(){

        ArrayList<Student> roster= new ArrayList<>();
        Iterator list=enrolled.iterator();
        for(int i=0; i<enrolled.size(); i++){
            roster.add((Student) list.next());
        }
        Collections.sort(roster);
        return roster;
    }

    /**
     * This method creates string representation 
     * for a Course object. The format should be 
     * as follows: <department> <number> [<capacity>] <description> 
     * @returns this string representation 
     * 
     */

    @Override
    public String toString(){
         String course_des=getDepartment()+" "+getNumber()
         +" "+"["+getCapacity()+"]"+" "+getDescription();
        return course_des;
    }



    
}
