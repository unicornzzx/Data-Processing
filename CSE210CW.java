package cse210cw;

import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 * Main class, the entrance of this program.
 *
 * @author Zhixiang Zhang
 */
public class CSE210CW {

    /**
     * Decide the path of excel file which will be read, use the abstracted data
     * to create a new instance of {@link DataSet#DataSet DataSet}, and call
     * {@link TestInterface#testing(cse210cw.DataSet) testing} method to test
     * it.Exceptions throwed by calling methods with be catched here, and the
     * track information of them will be printed.
     *
     * @param args
     */
    public static void main(String[] args) {
        String fileName = ""; //please enter the path of the dataset in your local system
        try {
            DataSet dataSet = new DataSet(ReadFile.readFile(fileName));
            TestInterface.testing(dataSet);
        } catch (IOException | InvalidFormatException | IllegalStateException e) {
            System.out.println("Error" + e);
        }

    }

}
