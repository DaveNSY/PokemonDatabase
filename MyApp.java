import java.util.HashSet;
import java.util.Scanner;

/**
 * MyApp will be the application which the program reads from a CSV file containing dex entries of Pokémon monsters
 */
public class MyApp {
    /**
     * The MenuOptions contains enum values corresponding to all menu and submenu options, with each specific submenu corresponding to a certain range
     * Example: Finding_Attribute submenu have enum with values 21 and 22 corresponding to option 1-2
     * Example: Finding_HP submenu have enum with values 31-35 corresponding to option 1-5
     * Example: Unit_testing submenu have enum with values 11-13 corresponding to option 1-3
     */
    enum MenuOptions {
        EXIT(1),
        OPEN_READ_DATA(2),

        FIND_WITH_ATTRIBUTE(4),
        ATTRIBUTE_HP(21),
        HP_ONE_VALUE(31),
        HP_RANGE_VALUE(32),
        HP_LEAST(33),
        HP_GREATEST(34),
        RETURN_FROM_HP(35),
        ATTRIBUTE_SPEED(22),
        SPEED_FASTEST(41),
        SPEED_SLOWEST(42),
        SPEED_TOP_THREE_FASTEST(43),
        SPEED_TOP_THREE_SLOWEST(44),
        SPEED_RANGE_VALUE(45),
        SPEED_TOP_GROUPS(46),
        SPEED_LARGEST_GROUP(47),
        RETURN_FROM_SPEED(48),

        UNIT_TEST(10),
        TEST_PRINT(11),
        TEST_WRITE_CHARACTER(12),
        TEST_SEARCH_CHARACTER(13);

        private int optionNum;

        /**
         * MenuOptions is a constructor for the enum, all initialized with a given value to help operate the menus
         * @param number A specific value corresponding to the enum label
         */
        MenuOptions(int number) {
            this.optionNum = number;
        }

        /**
         * getOptionValue is a function which gets the value of an enum label, and helps with submenus
         * @return An int value that tells the program which option the user has picked
         */
        public int getOptionValue() {
            return this.optionNum;
        }
    }

    /**
     * showMenu is the text-interface which greets the user and display the available options this program has
     * @return An integer value that represents a specific option
     */
    public static int showMenu() {
        // Scanner to scan user input
        Scanner scnr = new Scanner(System.in);

        System.out.print("\n\n------------------------\n1. Exit\n2. Open data file\n...\n4. Find character by attribute\n...\n10. Unit Testing\nChoose an option: ");
        return scnr.nextInt();
    }

    /**
     * showFindAttributeSubMenu is a text-interface which displays the available options if the user decides they want to find Pokemon with certain attribute.
     * Currently shows HP and Speed
     * @return An int value related to the user's choice of options
     */
    public static int showFindAttributeSubMenu() {
        Scanner scnr = new Scanner(System.in);

        System.out.print("Finding with:\n1. HP\n2. Speed\n...\nChoose an option: ");
        return scnr.nextInt();
    }

    /**
     * showHPAttributeSubMenu is a text-interface which displays options related to finding Pokemon by using their HP base stat
     * @return An int value related to the user's choice of options
     */
    public static int showHPAttributeSubMenu() {
        Scanner scnr = new Scanner(System.in);

        System.out.print("1. Find a character with a specific hit point value\n" +
                "2. Find characters within a specific range of hit values\n" +
                "3. Find the character with the lowest hit point value\n" +
                "4. Find the character with the highest hit point value\n" +
                "5. Go back to the previous menu\n" +
                "Choose an option: ");
        return scnr.nextInt();
    }

    /**
     * showSPEEDAttributeSubMenu is a text-interface which displays options related to finding Pokemon by using their Speed base stat
     * @return An int value related to the user's choice of options
     */
    public static int showSPEEDAttributeSubMenu() {
        Scanner scnr = new Scanner(System.in);

        System.out.print("1. Which character has the fastest speed\n" +
                "2. Which character has the slowest speed\n" +
                "3. Which characters are part of the top 3 fastest speeds\n" +
                "4. Which characters are part of the 3 slowest speeds\n" +
                "5. Which characters are part of a specific range of speeds\n" +
                "6. What are the top 3 speed groups, and what is the list of characters which are part of each speed group\n" +
                "7. Which group of characters represent the largest speed group\n" +
                "8. Go back to the previous menu\n" +
                "Choose an option: ");
        return scnr.nextInt();
    }

    /**
     * showTestSubMenu is the text-interface which opens when the user selected option 10
     * @return An integer value that represents a specific option
     */
    public static int showTestSubMenu() {
        Scanner scnr = new Scanner(System.in);

        System.out.print("Submenu:\n1. Test Print Method\n2. Write Pokemon Character Name to File\n3. Search for character\nChoose an option: ");
        return scnr.nextInt();
    }

    public static void main(String[] args) {
        // userOption is the value that will represent the user's choice
        // contentList is an object which represents an array list of lines from a file, and has reading functions
        int userOption = 0;
        ReadData contentList = new ReadData();
        HashSet<PokemonCharacter> pokemonList = null;

        // While loop keeps the cycle of user interface going until the user wants to exit
        while(userOption != MenuOptions.EXIT.getOptionValue()) {
            userOption = showMenu();

            if(userOption == MenuOptions.EXIT.getOptionValue()) {
                System.out.println("Thanks for using this application.");
            }
            else if(userOption == MenuOptions.OPEN_READ_DATA.getOptionValue()) {
                int counter = 0;
                boolean fileOpened = false;
                Scanner scnr = new Scanner(System.in);

                while(!fileOpened) {
                    System.out.print("Type in the file name: ");
                    String userFile = scnr.nextLine();

                    fileOpened = contentList.openDataFile(userFile);
                    if(!fileOpened && (counter < 2)) {
                        System.out.println("Please enter the correct file name.\n");
                        counter++;
                    }
                    else if(!fileOpened && (counter == 2)) {
                        System.out.println("Cannot find file, exiting.\n");
                        break;
                    }
                }
                AnalyzePokemonData pokemonNameListMaker = new AnalyzePokemonData();
                pokemonList = pokemonNameListMaker.getAllCharacterNames(contentList.getRawDataList());
            }
            else if(userOption == MenuOptions.FIND_WITH_ATTRIBUTE.getOptionValue() && (pokemonList != null)) {
                int subOptions = 0;

                // This do white loop ensures that the user can back out of the HP or Speed submenus and choose another attribute to compare Pokemons with
                do {
                    subOptions = showFindAttributeSubMenu();

                    if(subOptions == MenuOptions.ATTRIBUTE_HP.getOptionValue() - 20) {
                        int subHPOptions = showHPAttributeSubMenu();

                        if(subHPOptions == MenuOptions.HP_ONE_VALUE.getOptionValue() - 30) {
                            Scanner scnr = new Scanner(System.in);
                            TestData findSpecificHP = new TestData();
                            PokemonCharacter.setCompareWith(PokemonCompareOptions.leastHP);

                            System.out.print("Enter HP value you would like to search: ");
                            int userHP = scnr.nextInt();
                            findSpecificHP.findCharacterWithCertain(pokemonList, userHP, userHP);

                        } else if(subHPOptions == MenuOptions.HP_RANGE_VALUE.getOptionValue() - 30) {
                            Scanner scnr = new Scanner(System.in);
                            TestData findSpecificHP = new TestData();
                            PokemonCharacter.setCompareWith(PokemonCompareOptions.leastHP);

                            System.out.print("Enter two numbers to represent the HP range you would like to search: ");
                            int userHP1 = scnr.nextInt();
                            int userHP2 = scnr.nextInt();
                            if (userHP2 >= userHP1) {
                                findSpecificHP.findCharacterWithCertain(pokemonList, userHP1, userHP2);
                            } else {
                                findSpecificHP.findCharacterWithCertain(pokemonList, userHP2, userHP1);
                            }

                        } else if(subHPOptions == MenuOptions.HP_LEAST.getOptionValue() - 30) {
                            PokemonCharacter.setCompareWith(PokemonCompareOptions.leastHP);
                            TestData findGreatHP = new TestData();
                            findGreatHP.findCharacterWith(pokemonList);

                        } else if(subHPOptions == MenuOptions.HP_GREATEST.getOptionValue() - 30) {
                            PokemonCharacter.setCompareWith(PokemonCompareOptions.greatestHP);
                            TestData findGreatHP = new TestData();
                            findGreatHP.findCharacterWith(pokemonList);

                        }
                        else if(subHPOptions == MenuOptions.RETURN_FROM_HP.getOptionValue() - 30) {
                            System.out.println("== Returning back ==");
                            subOptions = MenuOptions.RETURN_FROM_HP.getOptionValue();
                        }
                        else {
                            System.out.println("Invalid input, pick from 1-5.");
                        }
                    } else if(subOptions == MenuOptions.ATTRIBUTE_SPEED.getOptionValue() - 20) {
                        int subSpeedOptions = showSPEEDAttributeSubMenu();

                        // Need work
                        if(subSpeedOptions == MenuOptions.SPEED_FASTEST.getOptionValue() - 40) {
                            PokemonCharacter.setCompareWith(PokemonCompareOptions.greatestSPEED);
                            TestData findGreatSPEED = new TestData();
                            findGreatSPEED.findCharacterWith(pokemonList);

                        }
                        else if(subSpeedOptions == MenuOptions.SPEED_SLOWEST.getOptionValue() - 40) {
                            PokemonCharacter.setCompareWith(PokemonCompareOptions.leastSPEED);
                            TestData findLeastSPEED = new TestData();
                            findLeastSPEED.findCharacterWith(pokemonList);

                        }
                        else if(subSpeedOptions == MenuOptions.SPEED_TOP_THREE_FASTEST.getOptionValue() - 40) {
                            PokemonCharacter.setCompareWith(PokemonCompareOptions.greatestSPEED);
                            TestData findTopThreeGreatSPEED = new TestData();
                            findTopThreeGreatSPEED.findTopThree(pokemonList);

                        }
                        else if(subSpeedOptions == MenuOptions.SPEED_TOP_THREE_SLOWEST.getOptionValue() - 40) {
                            PokemonCharacter.setCompareWith(PokemonCompareOptions.leastSPEED);
                            TestData findTopThreeLeastSPEED = new TestData();
                            findTopThreeLeastSPEED.findTopThree(pokemonList);

                        }
                        else if(subSpeedOptions == MenuOptions.SPEED_RANGE_VALUE.getOptionValue() - 40) {
                            PokemonCharacter.setCompareWith(PokemonCompareOptions.leastSPEED);
                            Scanner scnr = new Scanner(System.in);
                            TestData findSpecificSPEED = new TestData();

                            System.out.print("Enter two numbers to represent the SPEED range you would like to search: ");
                            int userSPEED1 = scnr.nextInt();
                            int userSPEED2 = scnr.nextInt();
                            if (userSPEED2 >= userSPEED1) {
                                findSpecificSPEED.findCharacterWithCertain(pokemonList, userSPEED1, userSPEED2);
                            } else {
                                findSpecificSPEED.findCharacterWithCertain(pokemonList, userSPEED2, userSPEED1);
                            }

                        }
                        else if(subSpeedOptions == MenuOptions.SPEED_TOP_GROUPS.getOptionValue() - 40) {
                            PokemonCharacter.setCompareWith(PokemonCompareOptions.leastSPEED);
                            TestData findTopThreeGroups = new TestData();

                            findTopThreeGroups.findTopThreeGroups(pokemonList);
                        }
                        else if(subSpeedOptions == MenuOptions.SPEED_LARGEST_GROUP.getOptionValue() - 40) {
                            PokemonCharacter.setCompareWith(PokemonCompareOptions.leastSPEED);
                            TestData findTopGroup = new TestData();

                            findTopGroup.findTopGroup(pokemonList);
                        }
                        else if(subSpeedOptions == MenuOptions.RETURN_FROM_SPEED.getOptionValue() - 40) {
                            System.out.println("== Returning back ==");
                            subOptions = MenuOptions.RETURN_FROM_SPEED.getOptionValue();
                        }
                        else {
                            System.out.println("Invalid input, pick from 1-8.");
                        }
                    }
                } while(subOptions == MenuOptions.RETURN_FROM_HP.getOptionValue() || subOptions == MenuOptions.RETURN_FROM_SPEED.getOptionValue());
            }
            else if(userOption == MenuOptions.UNIT_TEST.getOptionValue() && (pokemonList != null)) {
                int subOptions = showTestSubMenu();

                if(subOptions == (MenuOptions.TEST_PRINT.getOptionValue() - 10)) {
                    TestData firstTest = new TestData();
                    if(firstTest.testPrintFirstLastLines(contentList.getRawDataList())) {
                        System.out.println("First and last seven items printed successfully.");
                    }
                }
                else if(subOptions == (MenuOptions.TEST_WRITE_CHARACTER.getOptionValue() - 10)) {
                    Scanner scnr = new Scanner(System.in);
                    TestData secondTest = new TestData();

                    // Test: /Users/sean/Downloads/pokemon.csv
                    // Test: /Users/sean/IdeaProjects/MyAppPart2/src/Results.txt
                    System.out.print("Give a filename: ");
                    String userFile = scnr.nextLine();

                    secondTest.testWriteSet(pokemonList, userFile);
                }
                else if(subOptions == (MenuOptions.TEST_SEARCH_CHARACTER.getOptionValue() - 10)) {
                    Scanner scnr = new Scanner(System.in);
                    TestData thirdTest = new TestData();

                    System.out.print("Enter a Pokemon: ");
                    String userCharacter = scnr.nextLine();

                    thirdTest.testFindCharacter(pokemonList, userCharacter);
                }
                else {
                    System.out.println("Invalid input.");
                }
            }
            else {
                System.out.println("Invalid input, must have created a Pokemon list using Option 2 AND select a valid option number.");
            }
        }

        System.out.println("Exiting...");
    }
}
