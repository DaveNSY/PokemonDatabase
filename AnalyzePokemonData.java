import java.util.ArrayList;
import java.util.HashSet;

/**
 * AnalyzePokemonData represents a way to read from an ArrayList of Pokémon data, and takes the name of the Pokémon to
 * put into a Hashset
 */
public class AnalyzePokemonData implements IAnalyzePokemonData {
    /**
     * getAllCharacterNames is a function that takes an ArrayList of Pokémon data that was made from a file, then
     * takes the names and puts it into a Hashset, slowly filling the Hashset with all the Pokémon names
     * @param originalData The ArrayList given with the Pokémon data
     * @return Hashset with Pokémon names only
     */
    @Override
    public HashSet<PokemonCharacter> getAllCharacterNames(ArrayList<String> originalData) {
        HashSet<PokemonCharacter> pokemonList = new HashSet<PokemonCharacter>();

        // Start at i = 1 to skip header
        for(int i = 1; i < originalData.size(); i++) {
            int commaNum = 0;
            String currLine = originalData.get(i);
            String currName = "N/A";
            String currJapaneseName = "N/A";
            int currHP = 0;
            int currSpeed = 0;

            for(int j = 0; j < currLine.length(); j++) {
                // Because some Pokémon have multiple abilities that are separated by commas, we must first skip pass
                // those commas and to the specific comma that comes after the list of abilities
                if(j == 0) {
                    j = currLine.indexOf(']');
                    currLine = currLine.substring(j + 1);
                }

                // Check each element separated by a comma, we look out for the values for the English name,
                // HP values, and Speed values which are located at specific comma numbers
                // Sometimes, there is nothing between two commas, indicating there is not a value for a specific category, so we skip it
                j = currLine.indexOf(',');
                currLine = currLine.substring(j + 1);
                if(currLine.charAt(0) == ',') {
                    currLine = currLine.substring(1);
                    commaNum++;
                }
                commaNum++;

                // Each comma has a certain value after it, so the commaNum takes that into account
                if(commaNum == 28) {
                    currHP = Integer.parseInt(currLine.substring(0, currLine.indexOf(',')));
                }
                else if(commaNum == 29) {
                    currJapaneseName = currLine.substring(0, currLine.indexOf(','));
                }
                else if(commaNum == 30) {
                    currName = currLine.substring(0, currLine.indexOf(','));
                }
                else if(commaNum == 35) {
                    currSpeed = Integer.parseInt(currLine.substring(0, currLine.indexOf(',')));
                    // break;
                }
            }

            pokemonList.add(new PokemonCharacter(currName, currJapaneseName, currHP, currSpeed, i, originalData.get(i)));
        }

        return pokemonList;
    }

}
