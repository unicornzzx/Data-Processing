package cse210cw;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/**
 * Helper class, calculatethe cos similarity between two HashSets with String
 * elements.
 *
 * @author Zhixiang.Zhang
 */
public class cosSimilarity {

    /**
     * Calculate the cos similarity value of two given HashSet with String
     * elements.
     * <br> <br>Algorithm:
     * <ol>
     * <li>create the vector space: contains all disctinct interests in two
     * given HashSets</li>
     * <li>for each distinct String in vector space: calculate the appear times
     * t1 in the first given HashSet and t2 in the second one</li>
     * <li>calculate the modulo of the first vector: = the accumulation of all
     * distinct Strings' t1*t1</li>
     * <li>calculate the modulo of the second vector: = the accumulation of all
     * distinct Strings' t2*t2</li>
     * <li>calculate the vector product : = the accumulation of all distinct
     * Strings' t1*t2</li>
     * <li>calculate the cos similarity of two given HashSet: = vector product /
     * (square of first vector's modulo * square of second vector's modulo)</li>
     * </ol>
     * Reference: https://blog.csdn.net/sinat_18497785/article/details/53467196
     * @param interests_1 the first HashSet will be compared, its elements are
     * String
     * @param interests_2 the second HashSet will be compared, its elements are
     * String
     * @return The cos similarity value(as double type) of two given HashSet.
     */
    public static double getSimilarity(HashSet<String> interests_1, HashSet<String> interests_2) {
        /*
         * create the vector space
         * implemented by Map, key is distinct String, value is a array with size 
         * two, which represent the appear times of related String in two HashSet
         */
        Map<String, int[]> vectorSpace = new HashMap<>();

        // three possible situations of a distinct String's appear times in two HashSet, 
        int[] counter_1 = {1, 0}; //only appears in the first HashSet
        int[] counter_2 = {1, 1}; //only appears in the second HashSet
        int[] counter_3 = {0, 1}; //appears in both of two HashSet
        double vector1Modulo = 0.00; //modulo of first vector
        double vector2Modulo = 0.00; //modulo of second vector
        double vectorProduct = 0.00; //product of two vectors  

        // calculate the value of related array for each String
        for (String interest : interests_1) {
            vectorSpace.put(interest, counter_1);
        }
        for (String interest : interests_2) {
            if (vectorSpace.containsKey(interest)) {
                vectorSpace.put(interest, counter_2);
            } else {
                vectorSpace.put(interest, counter_3);
            }
        }

        // calculate the cos similarity
        Iterator iter = vectorSpace.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            int[] temp = (int[]) entry.getValue();

            vector1Modulo += temp[0] * temp[0];
            vector2Modulo += temp[1] * temp[1];

            vectorProduct += temp[0] * temp[1];
        }
        vector1Modulo = Math.sqrt(vector1Modulo);
        vector2Modulo = Math.sqrt(vector2Modulo);

        return (vectorProduct / (vector1Modulo * vector2Modulo));
    }

}
