package sword.phantasia.Game;



import java.util.Random;
import java.util.Scanner;

public class WorldTraversion {
    private Character player;
    private Item weapon;
    private int playerHP = 100;

    public WorldTraversion(Character player) {
        this.player = player;
        this.weapon = new Item("Sword", "Sharp Blade", 10.0); // Default weapon
    }

    public void startAdventure() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        while (true) {
            // Display character stats on the main screen
            System.out.println("\n===============================");
            System.out.println(" Name: " + player.getName());
            System.out.println(" HP: " + playerHP + " | Mana: " + player.getMana() + " | Level: " + player.getLevel() + " | Coins: " + player.getCoins());
            System.out.println("===============================\n");

            // Check if the player reached level 10 and allow them to fight the final boss
            if (player.getLevel() >= 10) {
                System.out.println("You have reached level 10!");
                System.out.println("A powerful foe awaits you...");
                System.out.println("Would you like to challenge the Demon King Koji?");
                System.out.println("1. Yes, fight the Demon King Koji.");
                System.out.println("2. No, continue adventuring.");
                System.out.print("\nEnter your choice: ");
                int choice = scanner.nextInt();
                System.out.println();

                if (choice == 1) {
                    fightFinalBoss();
                    return; // Ends the game after the boss fight
                } else {
                    System.out.println("You chose to continue adventuring. Keep gaining EXP!");
                    continue;
                }
            }

            System.out.println("What would you like to do?");
            System.out.println(" 1. Walk Forward");
            System.out.println(" 2. Walk Back");
            System.out.println(" 3. Walk Left");
            System.out.println(" 4. Walk Right");
            System.out.println(" 5. Explore (Look for monsters)");
            System.out.println(" 6. Rest (Restore 10 HP and 10 Mana)");
            System.out.println(" 7. Go to Blacksmith");
            System.out.println(" 8. Quit");

            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();
            System.out.println(); // Add a blank line for better spacing

            switch (choice) {
                case 1:
                case 2:
                case 3:
                case 4:
                    System.out.println("You moved.");
                    if (random.nextInt(100) < 20) { // 20% chance to encounter a monster
                        encounterMonster();
                    }
                    break;
                case 5:
                    System.out.println("You search the area...");
                    if (random.nextInt(100) < 50) { // 50% chance to encounter a monster
                        encounterMonster();
                    } else {
                        System.out.println("No monsters found.");
                    }
                    break;
                case 6:
                    rest();
                    break;
                case 7:
                    visitBlacksmith();
                    break;
                case 8:
                    System.out.println("Exiting the world. Thank you for playing!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void encounterMonster() {
        Random random = new Random();
        System.out.println("A wild encounter!");

        Battle battle = new Battle(player);
        battle.start();

        // Adjust player's HP after the battle
        playerHP -= random.nextInt(15); // Simulate taking damage during battle
        if (playerHP <= 0) {
            System.out.println("\nYou have been defeated! Game over!");
            System.exit(0);
        }
    }

    private void rest() {
        if (playerHP >= 100 && player.getMana() >= 100) {
            System.out.println("\nYou are already at full HP and Mana!");
        } else {
            playerHP = Math.min(playerHP + 10, 100); // Heal up to a max of 100 HP
            player.setMana(Math.min(player.getMana() + 10, 100)); // Regenerate up to a max of 100 Mana
            System.out.println("\nYou rested and regained 10 HP and 10 Mana. Current HP: " + playerHP + ", Current Mana: " + player.getMana());
        }
    }

    private void visitBlacksmith() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nWelcome to the Blacksmith!");
        System.out.println("Your current weapon: " + weapon.getItemName());
        System.out.println("Current additional damage: " + weapon.getAdditionalDamage());
        System.out.println("Your coins: " + player.getCoins());
        System.out.println("Weapon upgrade cost: 50 coins");
        System.out.println("\nWould you like to upgrade your weapon? (1: Yes, 2: No)");

        System.out.print("\nEnter your choice: ");
        int choice = scanner.nextInt();
        System.out.println(); // Add a blank line for better spacing

        if (choice == 1) {
            if (player.getCoins() >= 50) {
                System.out.println("Applying a 20% upgrade to your weapon...");
                weapon.applyUpgrades(20); // Increase damage by 20%
                player.spendCoins(50); // Deduct coins
                System.out.println("\nUpgrade complete! New additional damage: " + weapon.getAdditionalDamage());
                System.out.println("Remaining coins: " + player.getCoins());
            } else {
                System.out.println("\nYou don't have enough coins. Earn more by defeating monsters!");
            }
        } else {
            System.out.println("\nNo upgrades applied. Come back anytime!");
        }
    }

    private void fightFinalBoss() {
        System.out.println("\nThe Demon King Koji appears!");

        Battle battle = new Battle(player); // Reuse the Battle class
        battle.start();
    }
}