package sword.phantasia.Game;



public class Character {
    private String name;
    private int level;
    private int experience;
    private int coins;
    private int mana; // New mana attribute

    // Constructor
    public Character(String name, int level) {
        this.name = name;
        this.level = level;
        this.experience = 0;
        this.coins = 0;
        this.mana = 100; // Initialize mana to 100
    }

    // Getter and Setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void useMana(int amount) {
        if (mana >= amount) {
            mana -= amount;
        } else {
            System.out.println("Not enough mana!");
        }
    }

    public void addMana(int amount) {
        this.mana += amount;
        System.out.println(name + " regained " + amount + " mana!");
    }

    public void addCoins(int amount) {
        this.coins += amount;
        System.out.println(name + " earned " + amount + " coins!");
    }

    public void spendCoins(int amount) {
        if (coins >= amount) {
            coins -= amount;
        } else {
            System.out.println("Not enough coins!");
        }
    }

    public void addExperience(int exp) {
        this.experience += exp;
        System.out.println(name + " gained " + exp + " experience points.");
        checkLevelUp();
    }

    private void checkLevelUp() {
        int expThreshold = level * 100;
        while (experience >= expThreshold) {
            experience -= expThreshold;
            level++;
            mana += 20; // Increase mana when leveling up
            expThreshold = level * 100;
            System.out.println(name + " leveled up! Current level: " + level);
        }
    }

    public void displayCharacter() {
        System.out.println("Character Name: " + name);
        System.out.println("Character Level: " + level);
        System.out.println("Character Experience: " + experience);
        System.out.println("Coins: " + coins);
        System.out.println("Mana: " + mana);
    }
}