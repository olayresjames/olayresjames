package sword.phantasia.Game;


import java.util.Scanner;

public class MainMenu {

    // Entry point to display the main menu
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Welcome to Sword Phantasia ===");
            System.out.println("1. Play Game");
            System.out.println("2. Help");
            System.out.println("3. Quit");
            System.out.print("Your choice: ");

            int choice = getInput(scanner);

            switch (choice) {
                case 1:
                    playGame(scanner);
                    break;
                case 2:
                    displayHelp();
                    break;
                case 3:
                    System.out.println("Thank you for playing Sword Phantasia!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Displays help information
    private void displayHelp() {
        System.out.println("\n=== Help ===");
        System.out.println("- Choose 'Play Game' to start your adventure.");
        System.out.println("- Traverse the world, fight monsters, and level up.");
        System.out.println("- Reach level 10 to challenge Demon King Koji.");
        System.out.println("=================\n");
    }

    // Handles the game start process
    private void playGame(Scanner scanner) {
        System.out.println("\n=== Choose Your Weapon ===");
        System.out.println("1. Sword");
        System.out.println("2. Bow");
        System.out.println("3. Axe");
        System.out.println("4. Exit to Main Menu");
        System.out.print("Your choice: ");

        int weaponChoice = getInput(scanner);
        String weaponName = getWeaponName(weaponChoice);

        if (weaponName == null) {
            System.out.println("Exiting to main menu...");
            return;
        }

        System.out.print("\nEnter your character's name: ");
        scanner.nextLine(); // Consume leftover newline
        String playerName = scanner.nextLine();

        startAdventure(playerName, weaponName);
    }

    // Maps weapon choice to a name
    private String getWeaponName(int weaponChoice) {
        String weaponName = null;
        switch (weaponChoice) {
            case 1:
                weaponName = "Sword";
                break;
            case 2:
                weaponName = "Bow";
                break;
            case 3:
                weaponName = "Axe";
                break;
            case 4:
                // Exit to menu
                break;
            default:
                System.out.println("Invalid choice. Returning to main menu...");
        }
        return weaponName;
    }

    // Initializes the adventure
    private void startAdventure(String playerName, String weaponName) {
        Character player = new Character(playerName, 1);
        Item weapon = new Item(weaponName, "Base weapon", 10.0);

        System.out.println("\nWelcome, " + player.getName() + "!");
        System.out.println("You have chosen the " + weapon.getItemName() + ".");
        System.out.println("Get ready for your adventure!");

        WorldTraversion world = new WorldTraversion(player);
        world.startAdventure();
    }

    // Validates user input as an integer
    private int getInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}