package cse210cw;

import java.util.Collections;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.List;

/**
 * Helper class, store a collection of {@link Researcher#Researcher Researcher}
 * objects, provide operations about analyse data of researchers.
 * {@link Researcher#Researcher Researcher}s are stored in a LinkedList
 * <br><br>Operations provided:
 * <ul>
 * <li>calculate the number of distinct researchers in the dataset;</li>
 * <li>calculate the number of distinct interests in the dataset;</li>
 * <li>given a researcher’s name, show detailed information about him/her;</li>
 * <li>given an interest, calculate the number of researchers who have that
 * interest;</li>
 * <li>given two interests, show the number of times they co-occur;</li>
 * <li>given a researcher, find similar researchers based on their
 * interests;</li>
 * </ul>
 *
 * @author Zhixiang.Zhang
 */
public class DataSet {

    private final LinkedList<Researcher> researchers;

    /**
     * The constructor of {@link #DataSet DataSet} class.
     *
     * @param researchers The LinkedList of
     * {@link Researcher#Researcher Researcher} which will be hold by this
     * dataset.
     */
    DataSet(LinkedList<Researcher> researchers) {
        this.researchers = researchers;
    }

    /**
     * Calculate the number of distinct researchers in the dataset.
     *
     * @return The number of elements contained in the LinkedList, as an
     * integer.
     */
    public int researchersNum() {
        return researchers.size();
    }

    /**
     * Calculate the number of distinct interests in the dataset. Traverse the
     * whole dataset, check the interests of each
     * {@link Researcher#Researcher Researcher}, calculate the number of
     * distinct non-empty interests.
     *
     * @return The number of distinct non-empty interests in this dataset, as an
     * integer.
     */
    public int interestsNum() {
        HashSet<String> allInterests = new HashSet();
        for (Researcher researcher : researchers) {
            HashSet<String> temp = researcher.getInterests();
            for (String interest : temp) {
                if (!interest.equals("")) { //only add non-empty interests
                    allInterests.add(interest);
                }
            }
        }
        return allInterests.size();
    }

    /**
     * Get the {@link Researcher#Researcher Researcher} elements who has the
     * same name as the given String.
     *
     * @param name The name will be checked, as a String.
     * @return A LinkedList of {@link Researcher#Researcher Researcher} elements
     * who has the same name as the given String.
     */
    public LinkedList<Researcher> getResearcher(String name) {
        LinkedList<Researcher> result = new LinkedList();
        for (Researcher researcher : researchers) {
            if (researcher.getName().equalsIgnoreCase(name)) {
                result.add(researcher);
            }
        }
        return result;
    }

    /**
     * Calculate the number of researchers who have that given interest.
     *
     * @param interest The name of given interest, as a String.
     * @return The number of researchers who have that given interest, as an
     * integer.
     */
    public int appearTimes(String interest) {
        //return the number of researchers who have that interest
        int times = 0;
        interest = interest.toLowerCase();
        for (Researcher researcher : researchers) {
            if (researcher.hasInterest(interest)) {
                times++;
            }
        }
        return times;
    }

    /**
     * Calculate the number of researchers who have two given interest at the
     * same time.
     *
     * @param interest_1 The name of first given interest , as a String.
     * @param interest_2 The name of second given interest, as a String.
     * @return The number of researchers who have both given interests, as an
     * integer.
     */
    public int coOccurTimes(String interest_1, String interest_2) {
        int times = 0;
        interest_1 = interest_1.toLowerCase();
        interest_2 = interest_2.toLowerCase();
        for (Researcher researcher : researchers) {
            if (researcher.hasInterest(interest_1) && researcher.hasInterest(interest_2)) {
                times++;
            }
        }
        return times;
    }

    /**
     * Find the similar {@link Researcher#Researcher Researcher}s of the given
     * one by the cos similarities of their interests.
     *
     * @param researcher {@link Researcher#Researcher Researcher} instance which
     * will be found similar {@link Researcher#Researcher Researcher}.
     * @return <ul>
     * <li>A list of {@link Researcher#Researcher Researcher}s sorted by
     * interests cos similarity with the given one in descending order - only
     * list top 20 when number of researcher who has cos similarity greater than
     * 0.5 is more than 20.</li>
     * <li>A list of {@link Researcher#Researcher Researcher}s which have the
     * highests interests cos similarity with the given one - when the highest
     * similarity value is not greater than 0.5.</li>
     * <li>A empty list - when the interest of the given
     * {@link Researcher#Researcher Researcher} is empty.(In this situation, the
     * process of traversing dataset and finding similar ones will not be
     * excuted.)</li>
     * </ul>
     */
    public List<Researcher> recommend(Researcher researcher) {
        List<Researcher> recList = new LinkedList();
        HashSet<String> interest_1 = researcher.getInterests();
        if (!researcher.hasInterest("")) {
            double maxSimilarity = 0; //track the max similarity when the highest one is not greater than 0.5
            List<Researcher> secondCandidate = new LinkedList();
            //store the researchers who have max similarity when the highest one is not greater than 0.5
            for (Researcher r : researchers) {
                if (!r.hasInterest("")) {
                    double similarity = cosSimilarity.getSimilarity(interest_1, r.getInterests());
                    if (0.5 < similarity && similarity < 1) {
                        if (similarity < 0.99 || !r.getName().equals(researcher.getName())) {
                            //remove the same researcher as the given researcher
                            r.setSimilarity(similarity);
                            recList.add(r);
                        }
                    } else if (recList.isEmpty() && similarity > maxSimilarity && similarity < 1) {
                        // if there is any researcher who has similarity > 0.5, ignore the second candidates
                        maxSimilarity = similarity;
                        r.setSimilarity(similarity);
                        secondCandidate.clear();
                        secondCandidate.add(r);
                    } else if (recList.isEmpty() && similarity == maxSimilarity && similarity < 1) {
                        r.setSimilarity(similarity);
                        secondCandidate.add(r);
                    }
                }
            }
            Collections.sort(recList);
            if (recList.isEmpty()) {
                return secondCandidate;
            } else if (recList.size() > 20) {
                //restrict the size of return list
                recList = recList.subList(0, 20);
            }
        }
        return recList;
    }
}
