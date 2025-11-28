import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

/**
 * WriteDate represents a way of writing a new file using a Hashset as lines of content
 */
public class WriteData implements IWriteData {

    /**
     * writeDataToFile is a function that takes a Hashset and puts its content into a newly created text file
     * @param someData A given Hashset with lines of content
     * @param fileName A given name which a new file will be called
     * @return True if successful in creating a new file and putting items into it, false if error
     */
    @Override
    public boolean writeDataToFile(HashSet<PokemonCharacter> someData, String fileName) {
        try {
            File resultFile = new File(fileName);
            if(resultFile.createNewFile()) {
                PrintWriter outFile = new PrintWriter(new FileWriter(resultFile));
                for(PokemonCharacter i : someData) {
                    outFile.println(i.getName());
                }
                // Close the file from writing, so that text file can have our content
                outFile.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
