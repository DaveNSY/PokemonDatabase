import java.util.*;

/**
 * TestData represents a way of testing the functions of the program using lists from a text file, ensuring that
 * reading and writing is correct
 */
public class TestData implements ITestData {
    /**
     * testPrintFirstLastLines prints the first and last seven lines of a given ArrayList
     * @param originalList A list of Strings which are contents of a text file
     * @return True if the test successfully printed, false if error
     */
    @Override
    public boolean testPrintFirstLastLines(ArrayList<String> originalList) {
        try {
            for(int i = 0; i < originalList.size(); i++) {
                if(i < 7 || i > (originalList.size() - 8)) {
                    System.out.println("Line " + (i + 1) + ": " + originalList.get(i));
                }
            }
        } catch (Exception e) {
            System.out.println("Trouble printing: " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * testWriteSet writes a Hashset of Strings to a given file
     * @param someList A given Hashset with String values which are Pokémon names
     * @param fileName A given name for a new file created as an output for listing the different Pokémon names
     * @return True if file was created successfully, false if error
     */
    @Override
    public boolean testWriteSet(HashSet<PokemonCharacter> someList, String fileName) {
        WriteData pokemonNames = new WriteData();
        return pokemonNames.writeDataToFile(someList, fileName);
    }

    /**
     * testFindCharacter searches for a specified character in a given Hashset
     * @param characterList A given Hashset that contains a list of characters
     * @param desiredCharacter A given character name that the user wants to search for
     * @return True if found, false if not
     */
    @Override
    public boolean testFindCharacter(HashSet<PokemonCharacter> characterList, String desiredCharacter) {
        for(PokemonCharacter i : characterList) {
            if(i.equals(desiredCharacter)) {
                System.out.println("(Dex Num. " + i.getDexNum() + ") " + i.getName() + "'s data: " + i.getRawData());
                return true;
            }
        }

        System.out.println("Character not found.");
        return false;
    }

    /**
     * findCharacterWithCertain is a function that will create a new ArrayList containing the sorted version of a given Hashset
     * Then, depending on a given range, print characters which has the values in between the end points of the given range
     * The searched attribute will be selected when a user selects an option
     * @param characterList A list of Pokémon characters
     * @param desired_lowPoint The lower bound of a given range
     * @param desired_highPoint The higher bound of a given range
     */
    public void findCharacterWithCertain(HashSet<PokemonCharacter> characterList, int desired_lowPoint, int desired_highPoint) {
        ArrayList<PokemonCharacter> tempList = new ArrayList<>(characterList);
        Collections.sort(tempList);

        if(PokemonCharacter.getCompareWith() == PokemonCompareOptions.leastHP) {
            System.out.println("Pokemon with " + desired_lowPoint + "-" + desired_highPoint + " base HP: ");
            for(PokemonCharacter i : tempList) {
                if(i.getHP() >= desired_lowPoint && i.getHP() <= desired_highPoint) {
                    System.out.println(i);
                }
            }
        }
        else if(PokemonCharacter.getCompareWith() == PokemonCompareOptions.leastSPEED) {
            System.out.println("Pokemon with " + desired_lowPoint + "-" + desired_highPoint + " base Speed: ");
            for(PokemonCharacter i : tempList) {
                if(i.getSpeed() >= desired_lowPoint && i.getSpeed() <= desired_highPoint) {
                    System.out.println(i);
                }
            }
        }
    }

    /**
     * findCharacterWith is a function which searches for one character with the greatest/lowest attribute value specified from the user
     * @param characterList A given Pokémon list
     */
    public void findCharacterWith(HashSet<PokemonCharacter> characterList) {
        TreeSet<PokemonCharacter> tempList = new TreeSet<PokemonCharacter>(characterList);

        // Print different messages base on the set compare option
        switch (PokemonCharacter.getCompareWith()) {
            case leastHP -> System.out.print("The Pokemon with the least HP is " + tempList.getFirst());
            case greatestHP -> System.out.print("The Pokemon with the greatest HP is " + tempList.getFirst());
            case leastSPEED -> System.out.print("The Pokemon with the least SPEED is " + tempList.getFirst());
            case greatestSPEED -> System.out.print("The Pokemon with the greatest SPEED is " + tempList.getFirst());
        }
    }

    /**
     * findTopThree is a function which sorts the given list in an ArrayList, then prints the first three Pokémon who are the top three of a given attribute
     * @param characterList A given Pokémon list
     */
    public void findTopThree(HashSet<PokemonCharacter> characterList) {
        ArrayList<PokemonCharacter> tempList = new ArrayList<>(characterList);

        // Sorted by the set compare option
        Collections.sort(tempList);
        for(int i = 0; i < 3; i++) {
            System.out.println(tempList.get(i));
        }
    }

    /**
     * findTopThree is a function which groups all Pokémon into a specific attribute value, then prints the top three groups with the largest size
     * @param characterList A given Pokémon list
     */
    public void findTopThreeGroups(HashSet<PokemonCharacter> characterList) {

        // A new hashset is created as a copy of our given characterList so the original list is not modified in any way
        // A treemap is created with keys as Integer, representing the ArrayList sizes, along with the values being an ArrayList and its content
        HashSet<PokemonCharacter> tempPokemonList = new HashSet<>(characterList);
        TreeMap<Integer, ArrayList<PokemonCharacter>> groupList = new TreeMap<>();

        // For each character in the Pokémon list, get its speed into currSpeed which will serve to insert into a specific key value
        // Each key value relates to an ArrayList of Pokémon with the same Speed as the key
        for(PokemonCharacter i : tempPokemonList) {
            int currSpeed = i.getSpeed();

            // Create a new ArrayList, and give it the ArrayList from our Treemap using the current Pokémon's speed
            // This should give the group of Pokémon with the same speed as the current Pokémon i
            // If groupList.get(currSpeed) returns null, it means we are creating a new ArrayList for a speed group that hasn't been put into the TreeMap
            // Otherwise, when we get the ArrayList with the group of Pokémon with the same speed, pokemonGroup adds the current Pokémon and overrides the key value back in the TreeMap
            ArrayList<PokemonCharacter> pokemonGroup = groupList.get(currSpeed);
            if(pokemonGroup == null) {
                ArrayList<PokemonCharacter> tempList = new ArrayList<PokemonCharacter>();
                tempList.add(i);
                groupList.put(currSpeed, tempList);
            }
            else {
                pokemonGroup.add(i);
                groupList.put(currSpeed, pokemonGroup);
            }
        }

        // Test to see if groups are sorted correctly
        // System.out.println(groupList);

        // Map.Entry is the entries of the TreeMap, first/second/third will represent the top three groups base on the largest size
        Map.Entry<Integer, ArrayList<PokemonCharacter>> first = null;
        Map.Entry<Integer, ArrayList<PokemonCharacter>> second = null;
        Map.Entry<Integer, ArrayList<PokemonCharacter>> third = null;
        for(var speedGroup : groupList.entrySet()) {
            if(first == null) {
                first = speedGroup;
            }
            else if(first.getValue().size() < speedGroup.getValue().size()) {
                third = second;
                second = first;
                first = speedGroup;
            }
            else if(second == null) {
                second = speedGroup;
            }
            else if(second.getValue().size() < speedGroup.getValue().size()) {
                third = second;
                second = speedGroup;
            }
            else if(third == null) {
                third = speedGroup;
            }
            else if(third.getValue().size() < speedGroup.getValue().size()) {
                third = speedGroup;
            }
        }

        // Print the groups
        System.out.println("The Top Three Largest Group: ");
        System.out.println("1st group with " + first.getValue().size() + " Pokémon (Base Speed of " + first.getKey() + "): " + first.getValue());
        System.out.println("2nd group with " + second.getValue().size() + " Pokémon (Base Speed of " + second.getKey() + "): " + second.getValue());
        System.out.println("3rd group with " + third.getValue().size() + " Pokémon (Base Speed of " + third.getKey() + "): " + third.getValue());
    }

    /**
     * findTopGroup does the same thing as findTopThreeGroups, but only keeps track of the top group, see comments on the findTopThreeGroups to see how function works, as both are practically the same
     * @param characterList A given Pokémon List
     */
    public void findTopGroup(HashSet<PokemonCharacter> characterList) {
        HashSet<PokemonCharacter> tempPokemonList = new HashSet<>(characterList);
        TreeMap<Integer, ArrayList<PokemonCharacter>> groupList = new TreeMap<>();

        for(PokemonCharacter i : tempPokemonList) {
            int currSpeed = i.getSpeed();

            ArrayList<PokemonCharacter> pokemonGroup = groupList.get(currSpeed);
            if(pokemonGroup == null) {
                ArrayList<PokemonCharacter> tempList = new ArrayList<PokemonCharacter>();
                tempList.add(i);
                groupList.put(currSpeed, tempList);
            }
            else {
                pokemonGroup.add(i);
                groupList.put(currSpeed, pokemonGroup);
            }
        }

        // Test if group is sorted correctly
        // System.out.println(groupList);

        Map.Entry<Integer, ArrayList<PokemonCharacter>> first = null;
        for(var speedGroup : groupList.entrySet()) {
            if(first == null) {
                first = speedGroup;
            }
            else if(first.getValue().size() < speedGroup.getValue().size()) {
                first = speedGroup;
            }
        }

        System.out.println("The Top Largest Group: ");
        System.out.println("1st group with " + first.getValue().size() + " (Base Speed of " + first.getKey() + "): " + first.getValue());

    }
}

