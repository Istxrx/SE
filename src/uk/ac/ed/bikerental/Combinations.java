package uk.ac.ed.bikerental;

import java.util.ArrayList;

public class Combinations {

    // e.q. splits {1,2,3} to {{1},{2},{3}}
    public static ArrayList<ArrayList<Bike>> splitToElements(ArrayList<Bike> A) {
        ArrayList<ArrayList<Bike>> C = new ArrayList<ArrayList<Bike>>();

        for (Bike a : A) {
            ArrayList<Bike> newList = new ArrayList<Bike>();
            newList.add(a);
            C.add(newList);
        }
        return C;
    }

    public static ArrayList<ArrayList<Bike>> getCombinations(ArrayList<ArrayList<Bike>> A,
            ArrayList<ArrayList<Bike>> B) {

        ArrayList<ArrayList<Bike>> C = new ArrayList<ArrayList<Bike>>();

        for (ArrayList<Bike> a : A) {
            for (ArrayList<Bike> b : B) {
                ArrayList<Bike> newList = new ArrayList<Bike>();
                newList.addAll(a);
                newList.addAll(b);
                if (!a.containsAll(b)) {
                    if (!containsSpecial(C,newList)) {
                        C.add(newList);
                    }
                }
            }
        }
        return C;
    }

    public static ArrayList<ArrayList<Bike>> getRCombinations(ArrayList<Bike> A, int r) {

        ArrayList<ArrayList<Bike>> C = splitToElements(A);
        ArrayList<ArrayList<Bike>> AsplitToElements = splitToElements(A);

        for (int i = 0; i < r - 1; i++) {
            C = getCombinations(C, AsplitToElements);
        }
        return C;
    }

    public static boolean containsSpecial(ArrayList<ArrayList<Bike>> A,ArrayList<Bike> b) {
        for (ArrayList<Bike> a : A) {
            if (a.containsAll(b)) {
                return true;
            }
        }
        return false;
    }
    
}
