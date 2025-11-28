enum PokemonCompareOptions {
    greatestHP,
    leastHP,
    greatestSPEED,
    leastSPEED
}

/**
 * The PokemonCharacter class represents a PokemonCharacter with all it's data
 */
public class PokemonCharacter implements Comparable<PokemonCharacter> {
    private final String name;
    private final String japName;
    private final Integer hp;
    private final Integer speed;
    private final Integer dexNum;
    private final String rawData;
    private static PokemonCompareOptions compareOptions;

    /**
     * The PokemonCharacter constructor is used for setting the private variables of a new PokemonCharacter object
     * @param givenName A given Pokemon name in English
     * @param givenJapName A given Pokemon name in Japanese
     * @param givenHP A given Pokemon's HP base stat
     * @param givenSpeed A given Pokemon's Speed base stat
     * @param entryNum A given Pokemon's dex entry number
     * @param givenData A given string which contains a specific Pokemon's data, from abilities to weaknesses to stats
     */
    PokemonCharacter(String givenName, String givenJapName, int givenHP, int givenSpeed, int entryNum, String givenData) {
        this.name = givenName;
        this.japName = givenJapName;
        this.hp = givenHP;
        this.speed = givenSpeed;
        this.dexNum = entryNum;
        this.rawData = givenData;
    }

    public String getName() {
        return this.name;
    }

    public String getJapaneseName() {
        return this.japName;
    }

    public int getHP() {
        return this.hp;
    }

    public int getSpeed() {
        return this.speed;
    }

    public int getDexNum() {
        return this.dexNum;
    }

    public String getRawData() {
        return this.rawData;
    }

    /**
     * The getCompareWith function specifically returns the enum value related to the PokemonCharacter class
     * @return The enum value, which helps with comparisons of the PokemonCharacter class
     */
    public static PokemonCompareOptions getCompareWith() {
        return compareOptions;
    }

    /**
     * The setCompareWith function sets a specific enum value corresponding to a certain way PokemonCharacters would be compared to each other
     * @param code
     */
    public static void setCompareWith(PokemonCompareOptions code) {
        compareOptions = code;
    }

    /**
     * hashCode override gives a hashcode which depends on the name of the PokemonCharacter
     * @return int value from the hashCode function when putting in a name
     */
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    /**
     * The equals override function compares two PokemonCharacters by name, as each Pokémon has a different name
     * @param obj   the reference object with which to compare.
     * @return bool value depending on if the PokemonCharacter is the same Pokémon
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        } else if (obj.getClass() == (String.class)) {
            return this.name.equalsIgnoreCase((String)obj);
        } else if(this.getClass() == obj.getClass()) {
            return this.name.equalsIgnoreCase(((PokemonCharacter) obj).name);
        }

        return false;
    }

    /**
     * The toString override function is a way for PokemonCharacter objects to be printed out
     * @return A string with a Pokémon's name and data
     */
    @Override
    public String toString() {
        return (this.name + "," + this.japName + ": " + "(" + this.speed + " base Speed, " + this.hp + " base HP)");
    }

    /**
     * The compareTo override function helps certain type of lists, especially TreeList and TreeMap, with sorting PokemonCharacters
     * We use our enum values to compare either HP or Speed base stat between Pokémon, with leastHP and leastSpeed going in ascending order
     * while greatestHP and greatestSpeed go in descending order
     * @param other The other PokemonCharacter we are comparing a PokemonCharacter to
     * @return int value depending on if our object is greater than, less than, or equal to the other object
     */
    @Override
    public int compareTo(PokemonCharacter other) {

        return switch (compareOptions) {
            case leastHP -> this.hp.compareTo(((PokemonCharacter) other).hp);
            case greatestHP -> ((PokemonCharacter) other).hp.compareTo(this.hp);
            case leastSPEED -> this.speed.compareTo(((PokemonCharacter) other).speed);
            case greatestSPEED -> ((PokemonCharacter) other).speed.compareTo(this.speed);
        };
    }
}
