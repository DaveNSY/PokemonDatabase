import java.util.ArrayList;
import java.util.HashSet;

/**
 * The ITestData interface is a blueprint for the TestData class, which tests the data from a list of PokemonCharacter
 */
public interface ITestData {
    boolean testPrintFirstLastLines(ArrayList<String> originalList);
    boolean testWriteSet(HashSet<PokemonCharacter> someList, String fileName);
    boolean testFindCharacter(HashSet<PokemonCharacter> characterList, String desiredCharacter);
}
