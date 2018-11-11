package cse210cw;

import java.util.List;

/**
 * Helper class, provide a simple interface based on console commands for
 * testing methods in {@link DataSet#DataSet DataSet}.
 *
 * @author Zhixiang.Zhang15
 */
public class TestInterface {

    /**
     * Create a testing interface to test methods of given {@link DataSet#DataSet DataSet}.
     * <br>Reminder texts guide user to choose the method he/she want to test.
     * The confirm of choosing is achieved by inputing related console
     * commands.
     * <br>Console commands:
     * <ul> 
     * <li>1 - show the number of distinct reseachers, related to method: {@link DataSet#researchersNum() researchersNum}</li>
     * <li>2 - Show the number of distinct interests, related to method: {@link DataSet#interestsNum() interestsNum}</li>
     * <li>3 - Show detailed info of a researcher, related to method: {@link DataSet#getResearcher(java.lang.String) getResearcher}</li>
     * <li>4 - Show the number of researchers who have one specificed interest, related to method: {@link DataSet#appearTimes(java.lang.String) appearTimes}</li>
     * <li>5 - Show the number of times two interests co-occur, related to method: {@link DataSet#coOccurTimes(java.lang.String, java.lang.String) coOccurTimes}</li>
     * <li>6 - Find recommended researchers of a researcher, related to method: {@link DataSet#recommend(cse210cw.Researcher) recommend}</li>
     * <li>0 - Exit the testing interface</li>
     * </ul>
     *
     * @param dataSet Instance of {@link DataSet#DataSet DataSet} which will be
     * tested.
     */
    public static void testing(DataSet dataSet) {
        System.out.println("Functions:\n1.Show the number of distinct reseachers."
                + "\n2.Show the number of distinct interests."
                + "\n3.Show detailed info of a researcher."
                + "\n4.Show the number of researchers who have one specificed interest."
                + "\n5.Show the number of times two interests co-occur."
                + "\n6.Find recommended researchers of a researcher."
                + "\n0.exit");
        Boolean exit = false;
        while (!exit) {
            int command = DataInput.inputIntegerRange(0, 6);
            switch (command) {
                case 1:
                    System.out.println("Distinct researchers: " + dataSet.researchersNum());
                    break;

                case 2:
                    System.out.println("Distinct interests: " + dataSet.interestsNum());
                    break;

                case 3:
                    System.out.println("Enter the name of researcher: ");
                    String name = DataInput.inputStringNotEmpty();
                    List<Researcher> result = dataSet.getResearcher(name);
                    System.out.println("Number of researchers whose name is " + name + ": " + result.size());
                    for (Researcher researcher : result) {
                        System.out.println(researcher.toString());
                    }
                    break;

                case 4:
                    System.out.println("Enter the interest: ");
                    String interest = DataInput.inputStringTrimmed();
                    if (interest.trim().equals("")) {
                        System.out.println("Number of researchers who don't have interest " + ": " + dataSet.appearTimes(interest));
                    } else {
                        System.out.println("Number of researchers who has " + interest + ": " + dataSet.appearTimes(interest));
                    }
                    break;

                case 5:
                    System.out.println("Enter the first interest: ");
                    String interest_1 = DataInput.inputStringNotEmpty();
                    System.out.println("Enter the second interest: ");
                    String interest_2 = DataInput.inputStringNotEmpty();
                    if (interest_1.toLowerCase().equals(interest_2.toLowerCase())) {
                        System.out.println("Invalid input, two interests should be different.");
                    } else {
                        System.out.println("Times of " + interest_1 + " and " + interest_2 + " co-occured: " + dataSet.coOccurTimes(interest_1, interest_2));
                    }
                    break;

                case 6:
                    System.out.println("Enter the name of researcher: ");
                    String target = DataInput.inputStringNotEmpty();
                    List<Researcher> targets = dataSet.getResearcher(target);
                    System.out.println("Number of researchers whose name is " + target + ": " + targets.size());
                    for (Researcher researcher : targets) {
                        System.out.println("Matched researcher: " + researcher.toString());
                        List<Researcher> candidates = dataSet.recommend(researcher);
                        if (!candidates.isEmpty()) {
                            if (candidates.get(0).getSimilarity() <= 0.5) {
                                System.out.println("Can't find very similar researcher with this one, following are the researchers who has the highest similarity in the dataset: ");
                            } else {
                                System.out.println("Recommended researchers(if the number of similar researcher > 20, we only show top 20 of them): ");
                            }
                            for (Researcher candidate : candidates) {
                                System.out.println(candidate.recommendedString());
                            }
                        } else {
                            System.out.println("No similar researcher for this researcher. ");
                        }
                    }
                    break;

                case 0:
                    System.out.println("Bye-bye!");
                    exit = true;
            }
        }
    }
}
