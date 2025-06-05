package sword.phantasia.Game;

public class Item {
    private String itemName;
    private String attributes;
    private double additionalDamage;

    // Constructor
    public Item(String itemName, String attributes, double additionalDamage) {
        this.itemName = itemName;
        this.attributes = attributes;
        this.additionalDamage = additionalDamage;
    }

    // Getter and Setter methods
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public double getAdditionalDamage() {
        return additionalDamage;
    }

    public void setAdditionalDamage(double additionalDamage) {
        this.additionalDamage = additionalDamage;
    }

    // Method to apply upgrades
    public void applyUpgrades(double percentage) {
        double additionalBonus = (percentage / 100) * additionalDamage;
        additionalDamage += additionalBonus;
        System.out.println("Upgraded additional damage: " + additionalDamage);
    }
}