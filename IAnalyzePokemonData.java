import java.util.ArrayList;
import java.util.HashSet;

/**
 * The IAnalyzePokemonData interface is the blueprint for the AnalyzePokemonData class, which takes an array of Pokémon data
 * and picks certain values to make a PokemonCharacter object
 */
public interface IAnalyzePokemonData {
    HashSet<PokemonCharacter> getAllCharacterNames(ArrayList<String> originalData);
}
