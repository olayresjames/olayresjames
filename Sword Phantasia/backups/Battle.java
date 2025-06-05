package sword.phantasia.Game;



import java.util.Random;
import java.util.Scanner;

public class Battle {
    private Character player;
    private String monsterName;
    private int monsterHP;
    private int monsterAttack;

    public Battle(Character player) {
        this.player = player;
    }

    public void start() {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        String[] monsterNames = {"Slime", "Goblin", "Skeleton"};
        monsterName = monsterNames[random.nextInt(monsterNames.length)];
        monsterHP = random.nextInt(50) + 30;
        monsterAttack = random.nextInt(10) + 5;

        int playerHP = 100;
        int playerAttack = 15 + player.getLevel();
        System.out.println("\nA wild " + monsterName + " appears! (HP: " + monsterHP + ")");

        while (playerHP > 0 && monsterHP > 0) {
            System.out.println("\nYour HP: " + playerHP);
            System.out.println("Your Mana: " + player.getMana());
            System.out.println(monsterName + " HP: " + monsterHP);
            System.out.println("What will you do?");
            System.out.println("1. Attack");
            System.out.println("2. Defend");
            System.out.println("3. Use Skill (Cost: 20 Mana)");
            System.out.println("4. Run");
            System.out.print("Your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1: // Attack
                    int damageDealt = playerAttack + random.nextInt(10);
                    monsterHP -= damageDealt;
                    System.out.println("You attacked the " + monsterName + " for " + damageDealt + " damage!");
                    if (monsterHP <= 0) {
                        System.out.println("You defeated the " + monsterName + "!");
                        rewardPlayer(random);
                        return;
                    }
                    break;

                case 2: // Defend
                    System.out.println("You brace yourself for an attack.");
                    int reducedDamage = Math.max(0, monsterAttack - random.nextInt(5) - 5);
                    playerHP -= reducedDamage;
                    System.out.println("The " + monsterName + " attacked, but you only took " + reducedDamage + " damage!");
                    continue;

                case 3: // Use Skill
                    if (player.getMana() >= 20) {
                        int skillDamage = 40 + random.nextInt(20); // Skill does higher damage
                        player.useMana(20);
                        monsterHP -= skillDamage;
                        System.out.println("You used a powerful skill and dealt " + skillDamage + " damage!");
                        if (monsterHP <= 0) {
                            System.out.println("You defeated the " + monsterName + "!");
                            rewardPlayer(random);
                            return;
                        }
                    } else {
                        System.out.println("Not enough mana to use skill!");
                    }
                    break;

                case 4: // Run
                    if (random.nextInt(100) < 50) {
                        System.out.println("You successfully ran away from the " + monsterName + "!");
                        return;
                    } else {
                        System.out.println("You failed to escape!");
                    }
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    continue;
            }

            int damageTaken = monsterAttack + random.nextInt(5);
            playerHP -= damageTaken;
            System.out.println("The " + monsterName + " attacked you for " + damageTaken + " damage!");

            if (playerHP <= 0) {
                System.out.println("You were defeated by the " + monsterName + ". Game over!");
                return;
            }
        }
    }

    private void rewardPlayer(Random random) {
        int expGained = random.nextInt(30) + 20;
        int coinsGained = random.nextInt(20) + 10;

        player.addExperience(expGained);
        player.addCoins(coinsGained);
        System.out.println(player.getName() + " gained " + expGained + " experience and " + coinsGained + " coins!");
    }
}