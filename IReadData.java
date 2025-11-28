import java.io.BufferedReader;
import java.util.ArrayList;

/**
 * The IReadDate interface is a blueprint for the ReadData class, which will read from a given text file
 */
public interface IReadData {
    boolean openDataFile(String fileName);
    boolean readDataFile(BufferedReader inputFile);
    ArrayList<String> getRawDataList();
}
