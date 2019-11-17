package uk.ac.ed.bikerental;

import java.util.ArrayList;

public class Comb {
    /* arr[]  ---> Input Array 
    data[] ---> Temporary array to store current combination 
    start & end ---> Staring and Ending indexes in arr[] 
    index  ---> Current index in data[] 
    r ---> Size of a combination to be printed */
    public static void combinationUtil(ArrayList<Bike> arr, int n, int r, int index, 
                                Bike[] data, int i,ArrayList<ArrayList<Bike>> allTypeCombinations) 
    { 
        // Current combination is ready to be printed, print it 
        if (index == r) {
            ArrayList<Bike> bikes = new ArrayList<>();
            for (int j=0; j<r; j++) { 
                bikes.add(data[j]);
            }
            allTypeCombinations.add(bikes);
        return; 
        } 
  
        // When no more elements are there to put in data[]
        if (i >= n) 
        return; 
  
        // current is included, put next at next location 
        data[index] = arr.get(i); 
        combinationUtil(arr, n, r, index+1, data, i+1,allTypeCombinations); 
  
        // current is excluded, replace it with next (Note that 
        // i+1 is passed, but index is not changed) 
        combinationUtil(arr, n, r, index, data, i+1,allTypeCombinations); 
    } 
  
    // The main function that prints all combinations of size r 
    // in arr[] of size n. This function mainly uses combinationUtil() 
    public static void makeCombination(ArrayList<Bike> arr, int n, int r, ArrayList<ArrayList<Bike>> allTypeCombinations) 
    { 
        // A temporary array to store all combination one by one 
        Bike[] data=new Bike[r]; 
  
        // Print all combination using temprary array 'data[]' 
        combinationUtil(arr, n, r, 0, data, 0,allTypeCombinations); 
    } 
  
    /*Driver function to check for above function*/
   
}
