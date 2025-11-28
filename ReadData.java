import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * ReadData represents a way of reading from a given file and containing each line of content into a private ArrayList
 */
public class ReadData implements IReadData {
    private ArrayList<String> fileContent = new ArrayList<String>();

    /**
     * openDataFile is a function which tests whether a given file exists
     * @param fileName The given filename which the function will search for
     * @return True if the filename is found, false if not found
     */
    @Override
    public boolean openDataFile(String fileName) {
        // openState tell us whether the file is in an open state, if a file has not been opened, that means the user
        // has not entered a valid file name and has to try again. If openState is true, a file is opened and the function
        // will exit out of the prompt loop
        boolean openState = false;

        // While a file is not open, prompt the user to enter a file name that can be open
        // Loop back two times if given file name doesn't exist in the user's computer
        try {
            BufferedReader inFile = new BufferedReader(new FileReader(fileName));
            readDataFile(inFile);
            inFile.close();
            openState = true;
        } catch (IOException e) {
            System.out.println("Trouble opening the given filename: " + e.getMessage());
        }

        return openState;
    }

    /**
     * readDataFile is a function which opens and reads a file, extracting content from each line and putting it into
     * the object's private ArrayList
     * @param inputFile The given file
     * @return True if successful in extracting information, false if error
     */
    @Override
    public boolean readDataFile(BufferedReader inputFile) {
        try {
            String currLine;
            while((currLine = inputFile.readLine()) != null) {
                fileContent.add(currLine);
            }
        } catch (IOException e) {
            System.out.println("Trouble reading from the file: " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * getRawDataList gives back the reference to the private ArrayList, giving access to its content
     * @return The ArrayList of the object
     */
    @Override
    public ArrayList<String> getRawDataList() {
        return fileContent;
    }

}
