import java.util.HashSet;

/**
 * The IWriteData interface is a blueprint for the WriteData class, which will contain functions to help write a list of data into a new text file
 */
public interface IWriteData {
    boolean writeDataToFile(HashSet<PokemonCharacter> someData, String fileName);
}
