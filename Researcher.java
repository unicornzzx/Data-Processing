package cse210cw;

import java.util.HashSet;

/**
 * Stores data of a researcher.
 * <br><br>Instances of this class store:
 * <ul>
 * <li>the name of a researcher, as a string;</li>
 * <li>university name of the university where the researcher comes from, as a
 * String;</li>
 * <li>department name of the department where the researcher comes from, as a
 * String;</li>
 * <li>a set of string element which contains the interests of this researcher,
 * as a HashSet;</li>
 * <li>a double value to store the cos similarity of interests with another
 * researcher;</li>
 * </ul>
 *
 * @author Zhixiang Zhang 1509020
 */
public class Researcher implements Comparable<Researcher> {

    //the name of a researcher
    private final String name;

    //university name of the university where the researcher comes from
    private final String university;

    //department name of the department where the researcher comes from
    private final String department;

    // a set of string element which contains the interests of this researcher
    private final HashSet<String> interests;

    /*
     * A double value to store the cos similarity of interests with
     * another researcher, it is not final, its value (init: 0) can be changed.
     */
    private double similarity = 0;

    /**
     * @Description: The constructor of {@link #Researcher Researcher} class.
     * @param name The String value which will be assigned to
     * {@link #name name}.
     * @param university The String value which will be assigned to
     * {@link #university university}.
     * @param department The String value which will be assigned to
     * {@link #department department}.
     * @param interests The String value which will be assigned to
     * {@link #interests interests}.
     */
    Researcher(String name, String university, String department, HashSet<String> interests) {
        this.name = name;
        this.university = university;
        this.department = department;
        this.interests = interests;
    }

    /**
     * Get the value of this researcher's {@link #name name} .
     *
     * @return The value of {@link #name name}, as a String.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the value of this researcher's {@link #interests interests}.
     *
     * @return The value of {@link #interests interests}, as a HashSert of
     * String elements.
     */
    public HashSet<String> getInterests() {
        return interests;
    }

    /**
     * Get the value of this researcher's {@link #similarity similarity} .
     *
     * @return The value of {@link #similarity similarity}, as a double.
     */
    public double getSimilarity() {
        return similarity;
    }

    /**
     * Set the value of this researcher's {@link #similarity similarity}.
     *
     * @param similarity a double value which will be assigned to
     * {@link #similarity similarity}
     */
    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }

    /**
     * Get the a string which contains the detailed information of this
     * researcher.
     *
     * @return Detailed information of this researcher, in order: name;
     * university; department; interests.
     */
    @Override
    public String toString() {
        String interestsInfo = "";
        for (String interest : interests) {
            if (interestsInfo.equals("")) {
                interestsInfo += interest;
            } else {
                interestsInfo += " , " + interest;
            }
        }
        String result = "Name:" + name + "; University: " + university
                + "; Department: " + department + "; Interests: " + interestsInfo;
        return result;
    }

    /**
     * Based on method {@link #toString() toString}, add the similar degree
     * information in the front of this String. It's used in print the
     * information of recommended researcher.
     *
     * @return Detailed information of this researcher, in order:similarity;
     * name; university; department; interests.
     */
    public String recommendedString() {
        return "Similar degree: " + similarity + " ;" + toString();
    }

    /**
     * Check if a interest is contained in the interests set of this researcher.
     *
     * @param interest The interest will be checked, as a String.
     * @return <code>true</code> if the given interest is contained in the
     * interests set of this researcher.
     */
    public boolean hasInterest(String interest) {
        return interests.contains(interest);
    }

    /**
     * Defined the comparison of two researchers, implement the Compareable of
     * class {@link #Researcher Researcher}.
     * <br><br>The researcher who has greater similarity will be fronter than
     * another when call Collection.sort().
     *
     * @param researcher The researcher will be compared with this researcher.
     * @return <ul>
     * <li>interger 0, if two researchers similarity are the same</li>
     * <li>interger 1, if this researcher's similarity is greater</li>
     * <li>interger -1, if researcher in parameter has greater similarity</li>
     * </ul>
     */
    @Override
    public int compareTo(Researcher researcher) {
        int result = 0;
        double a = researcher.getSimilarity();
        double b = this.getSimilarity();
        if (a - b < 0) {
            result = -1;
        } else if (a - b > 0) {
            result = 1;
        }
        return result;
    }
}
