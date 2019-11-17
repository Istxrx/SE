package uk.ac.ed.bikerental;

import java.util.ArrayList;

public class Combinations {

    // e.q. splits {1,2,3} to {{1},{2},{3}}
    public static ArrayList<ArrayList<Object>> splitToElements(ArrayList<Object> A) {
        ArrayList<ArrayList<Object>> C = new ArrayList<ArrayList<Object>>();

        for (Object a : A) {
            ArrayList<Object> newList = new ArrayList<Object>();
            newList.add(a);
            C.add(newList);
        }
        return C;
    }

    public static ArrayList<ArrayList<Object>> getCombinations(ArrayList<ArrayList<Object>> A,
            ArrayList<ArrayList<Object>> B) {

        ArrayList<ArrayList<Object>> C = new ArrayList<ArrayList<Object>>();

        for (ArrayList<Object> a : A) {
            for (ArrayList<Object> b : B) {
                ArrayList<Object> newList = new ArrayList<Object>();
                newList.addAll(a);
                newList.addAll(b);
                if (!a.contains(b)) {
                    C.add(newList);
                }
            }
        }
        return C;
    }

    public static ArrayList<ArrayList<Object>> getRCombinations(ArrayList<Object> A, int r) {

        ArrayList<ArrayList<Object>> C = new ArrayList<ArrayList<Object>>();
        ArrayList<ArrayList<Object>> AsplitToElements = splitToElements(A);

        for (int i = 0; i < r - 1; i++) {
            C = getCombinations(C, AsplitToElements);
        }
        return C;
    }

    
}
