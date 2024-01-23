/*
 * Name: govinda sahoo
 * Email: gsahoo@ucsd.edu
 * PID: A16378764
 * Sources used: tutors
 *
 * This file is used to create a Hash map 
 * to store animals in a santurary and how many of them need to be saved
 * 
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
 * This class Creates the sanctury class
 * and stores the details of the animals
 * in the sancturary 
 * 
 * Instance variables: 
 * maxAnimal=maximum number of animals allowed 
 * maxSpecies=maximum number of species allowed 
 * sanctuary=stores the species and animals numbers
 * 
 */

public class Sanctuary {
    // Private instance variables
    HashMap<String, Integer> sanctuary;
    private final int maxAnimals;
    private final int maxSpecies;

    /**
     * The constructor 
     *Initialize the sanctuary 
     with the constructos data
     * 
     */
    public Sanctuary(int maxAnimals, int maxSpecies){
        // Check that the arguments are valid
        if((maxAnimals<1)||(maxSpecies<1)||(maxSpecies>maxAnimals))
        {throw new IllegalArgumentException();}
        // Set instance variables to constructor arguments
        sanctuary= new HashMap<String, Integer>();
        this.maxSpecies=maxSpecies;
        this.maxAnimals=maxAnimals;
    } 

    /**
     * This method get the number of 
     * animals of a species in the sanctuary
     * @return the number of animals of that
     * species if its in the hashmap
     * @return 0 otherwise
     */

    public int countForSpecies(String species){
        // Check that the arguments are valid
        if(species==null){throw new IllegalArgumentException();}
        //checks if sanctuary has the argument species
        if(sanctuary.containsKey(species))
        {return sanctuary.get(species);}
        else{return 0;}
    } 

    /**
     * This method get the number of 
     * animals in the sanctuary
     * @return the total number of animals
     * in the hashmap
     */
    public int getTotalAnimals(){
        int total=0;
        //loops through all the species' values 
        //adds the to the total sum
        for (String i : sanctuary.keySet()) { 
            total=total+sanctuary.get(i);
          }
        return total;
    } 

     /**
     * This method 
     * @return the total number of species
     * in the hashmap
     */
    public int getTotalSpecies(){

       return sanctuary.size();
        //Return the total number of species in the sanctuary.
    } 

     /**
     * This method 
     * @return the max animals allowed
     */

    public int getMaxAnimals(){
        return maxAnimals;
    } 
    /**
     * This method 
     * @return the max species allowed
     */
    public int getMaxSpecies(){
        return maxSpecies;
    } 

    /**
     * This method adds new species of the hashmap
     * sanctuary if there is space for new species 
     * and animals. 
     * it adds as many animals as permitted
     * @return number of animals that couldn't be saved
     */

    public int rescue(String species, int num){
        // Check that the arguments are valid
        if((num<1)||(species==null))
        {throw new IllegalArgumentException();}

        int not_rescued=num;
        int spare_cap=getMaxAnimals()-getTotalAnimals();

        //checks if there is space capacity
        if(spare_cap>0){
        // checks that Argument species is not in the list
        if((!sanctuary.containsKey(species))&&
        (getMaxSpecies()>getTotalSpecies())){
            //if all animals can be saved
            if(spare_cap>=num){
                System.out.print(num);
                sanctuary.put(species,num);
                not_rescued=0;
             }
             //if all not animals can be saved
             else{ 
                sanctuary.put(species, spare_cap);
                not_rescued= num-spare_cap;
             }
        }
        //if it already exits in the hashmap
        else{
            //gets the old values
            int old_value= (int) sanctuary.get(species);
            //updates the species with values 
            //if all animals can be saved
            if(spare_cap>=num){
                int new_value=(num+old_value);
                sanctuary.remove(species);
                sanctuary.put(species, new_value);
                not_rescued=0;
            }
            //if all animals can be saved
            else{
                sanctuary.remove(species);
                sanctuary.put(species, spare_cap);
                not_rescued= num-spare_cap;
            }
        }
        }
        //returns the animals that couldn't be rescued
        return not_rescued;
    }

        /**
     * This method Remove num 
     * animals of species from the sanctuary
     * it removes the species if it has no animals
     */

    public void release(String species, int num){
        // Check that the arguments are valid
        if((num<1)|| (num>sanctuary.get(species))
        ||(species==null)||!(sanctuary.containsKey(species)))
        {throw new IllegalArgumentException();}

        int count=countForSpecies(species);
        if(count<=num)
        { sanctuary.remove(species); }
        else{
            sanctuary.put(species, (count-num));
        }

    }

}
