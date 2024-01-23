/*
 * Name: govinda sahoo
 * Email: gsahoo@ucsd.edu
 * PID: A16378764
 * Sources used: tutors
 *
 * This file is used to create the class Student
 * Which is a object that is used by Course
 * It contains the essential information of a student
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
 * This class Creates the class Student 
 * and stores the details of the students 
 * 
 * Instance variables: 
 * firstName=first name of the student
 * lastName=last name of the student
 * PID=PID of the student
 * 
 */
public class Student implements Comparable<Student> {
    // Private instance variables
    private final String firstName;
    private final String lastName;
    private final String PID;
      /**
     * The constructor 
     * Initializes the student’s information. 
     * 
     */
    public Student(String firstName, String lastName, String PID){
        // Throw an IllegalArgumentException
        if((firstName==null)
        ||(lastName==null)||
        (PID==null)){throw new IllegalArgumentException();}

        // Initialize the student’s information. 
        this.firstName=firstName;
        this.lastName=lastName;
        this.PID=PID;
    }
    /**
     * The constructor 
     * @returns the student’s firstname
     * 
     */
    public String getFirstName(){
        // Return the student’s first name
        return firstName;
    }
     /**
     * The constructor 
     * @returns the student’s lastname
     * 
     */
    public String getLastName(){
        // Return the student’s last name
        return lastName;
        
    }
     /**
     * The constructor 
     * @returns the student’s PID
     * 
     */

    public String getPID(){
        // Return the student’s last name
        return PID;
        
    }
     /**
     * The constructor Compare the individual instance 
     * variables of the two Student objects
     * checks wether o is a non-null object
     * and is student object or not. And it @Overrides the built in function
     * @returns True if o is student and conditions are satisfied
     * false otherwise
     * 
     */
    @Override
    public boolean equals(Object o){
     
        // checks its not null and is a student
        if(!(o==null) && 
     (o instanceof Student) )
    	{ //check wether the values align up   
        Student temp= (Student) o;
        if ( (firstName.compareTo( temp.firstName)==0)
        && (temp.lastName.compareTo(lastName)==0) 
        && (temp.PID.compareTo(PID)==0)){  
            return true;
        }  
         }
         //returns false if above condition is not met
            return false;
    
    }
     /**
     * The constructor hashes the student Object
     * using builtin in hash function
     * @returns hashed value
     * 
     */
    @Override
    public int hashCode(){
        //hashes the code
       int Hcode= Objects.hash(firstName,lastName,PID);
        return Hcode;
    }
    /**
     * The constructor compares two Student's objects
     * It goes in the order from lastname,firstname,PID
     * if both objects aren't the same it
     * @returns the compared value
     * 
     */
    public int compareTo(Student o){
        // Throw a NullPointerException if o is null
        if(o==null){throw new NullPointerException();}

        int value;
        // Compare the last names of the two Student objects
        value=lastName.compareTo(o.lastName);
        if(value!=0){
            return value;
        }
        // If last names are the same, compare the first names
        value=firstName.compareTo(o.firstName);
        if(value!=0){
            return value;
        }
        else{// If first names are the same, compare the PIDs
            return  PID.compareTo(o.PID);
        }
    }



    

}