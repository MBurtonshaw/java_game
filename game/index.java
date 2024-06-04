import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

class Index {
    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);
        System.out.println("What is your name?");
        String name = userInput.nextLine();
        List<String> someList = new ArrayList<>();

        Hero hero1 = new Hero(name, 1, 50, 20, 0, someList, 10, 0);
        hero1.levelUp();
        hero1.levelUp();
        System.out.println(".............................................\n.......................................\n............................................");
        System.out.println(hero1.toString());
        
        System.out.println(".............................................\n.......................................\n............................................");
        Monster monster1 = new Monster("Barry", 20, 5, 50);
        System.out.println(monster1.appear());

        getAction(hero1, monster1);

        System.out.println(".............................................\n.......................................\n............................................");
        
        System.out.println(hero1.toString());
        
        System.out.println(".............................................\n.......................................\n............................................");
        System.out.println(".............................................\n.......................................\n............................................");
        Monster monster2 = new Monster("Larry", 30, 10, 50);
        System.out.println(monster2.appear());
        getAction(hero1, monster2);
        System.out.println(".............................................\n.......................................\n............................................");
        
        System.out.println(hero1.toString());

        System.out.println(".............................................\n.......................................\n............................................");
        System.out.println(".............................................\n.......................................\n............................................");
        Monster monster3 = new Monster("Terry", 45, 12, 35);
        System.out.println(monster3.appear());
        getAction(hero1, monster3);
        System.out.println(".............................................\n.......................................\n............................................");
    
        System.out.println(".............................................\n.......................................\n............................................");
        System.out.println(".............................................\n.......................................\n............................................");
        Monster monster4 = new Monster("Gary", 55, 17, 60);
        System.out.println(monster4.appear());
        getAction(hero1, monster4);
        System.out.println(".............................................\n.......................................\n............................................");
    
        System.out.println(".............................................\n.......................................\n............................................");
        System.out.println(".............................................\n.......................................\n............................................");
        Monster monster5 = new Monster("Mary", 65, 20, 85);
        System.out.println(monster5.appear());
        getAction(hero1, monster5);
        System.out.println(".............................................\n.......................................\n............................................");
    }

    static int getAction(Hero hero, Monster monster) {
        Scanner userInput = new Scanner(System.in);

        do {
            if (hero.getSpellsList().size() > 1 || hero.getMagicPoints() >= 15) {
                System.out.println("1 - Attack  ||  2 - Run  ||  3 - Freeze Spell  ||  4 - Fire Spell");
                System.out.println(".............................................\n");
                int decision = userInput.nextInt();
                if (decision == 1) {
                    monster.receiveDamage(hero.getDamage());
                    System.out.println(monster.getName() + " receives " + hero.getDamage() + " points of damage!");
                    System.out.println(".............................................\n.......................................\n............................................");
                    if (monster.getHealthPoints() > 0) {
                        hero.receiveDamage(monster.getDamage());
                        System.out.println(monster.getName() + " attacks!");
                        System.out.println(hero.getName() + " receives " + monster.getDamage() + " points of damage!");
                        System.out.println(".............................................\n.......................................\n............................................");
                    } else {
                        System.out.println(monster.getName() + " has been defeated!");
                        hero.receiveExp(monster.getExpGiven());
                        System.out.println(hero.getName() + " has gained " + monster.getExpGiven() + " exp points!");
                        System.out.println(".............................................\n.......................................\n............................................");
                        hero.defeatEnemy();
                    }
                } else if (decision == 2) {
                    System.out.println(hero.getName() + " has run away from " + monster.getName() + "!");
                    System.out.println(".............................................\n.......................................\n............................................");
                    break;
                } else if (decision == 3) {
                    hero.castFreezeSpell();
                    monster.takeFreezeDamage();
                } else if (decision == 4) {
                    hero.castFireSpell();
                    monster.takeFireDamage();
                } else {
                    System.out.println("Please enter a valid option");
                }
            } else if (hero.getSpellsList().size() < 2 || hero.getMagicPoints() >= 15) {
                System.out.println("1 - Attack  ||  2 - Run  ||  3 - Freeze Spell");
                System.out.println(".............................................\n");
                int decision = userInput.nextInt();
                if (decision == 1) {
                    monster.receiveDamage(hero.getDamage());
                    System.out.println(monster.getName() + " receives " + hero.getDamage() + " points of damage!");
                    System.out.println(".............................................\n.......................................\n............................................");
                    if (monster.getHealthPoints() > 0) {
                        hero.receiveDamage(monster.getDamage());
                        System.out.println(monster.getName() + " attacks!");
                        System.out.println(hero.getName() + " receives " + monster.getDamage() + " points of damage!");
                        System.out.println(".............................................\n.......................................\n............................................");
                    } else {
                        System.out.println(monster.getName() + " has been defeated!");
                        hero.receiveExp(monster.getExpGiven());
                        System.out.println(hero.getName() + " has gained " + monster.getExpGiven() + " exp points!");
                        System.out.println(".............................................\n.......................................\n............................................");
                        hero.defeatEnemy();
                    }
                } else if (decision == 2) {
                    System.out.println(hero.getName() + " has run away from " + monster.getName() + "!");
                    System.out.println(".............................................\n.......................................\n............................................");
                    break;
                } else if (decision == 3) {
                    hero.castFreezeSpell();
                    System.out.println(hero.getName() + " has cast fire spell! ");
                    monster.takeFreezeDamage();
                } else {
                    System.out.println("Please enter a valid option");
                }
            } else {
                System.out.println("1 - Attack  ||  2 - Run");
                System.out.println(".............................................\n");
                int decision = userInput.nextInt();
                if (decision == 1) {
                    monster.receiveDamage(hero.getDamage());
                    System.out.println(monster.getName() + " receives " + hero.getDamage() + " points of damage!");
                    System.out.println(".............................................\n.......................................\n............................................");
                    if (monster.getHealthPoints() > 0) {
                        hero.receiveDamage(monster.getDamage());
                        System.out.println(monster.getName() + " attacks!");
                        System.out.println(hero.getName() + " receives " + monster.getDamage() + " points of damage!");
                        System.out.println(".............................................\n.......................................\n............................................");
                    } else {
                        System.out.println(monster.getName() + " has been defeated!");
                        hero.receiveExp(monster.getExpGiven());
                        System.out.println(hero.getName() + " has gained " + monster.getExpGiven() + " exp points!");
                        System.out.println(".............................................\n.......................................\n............................................");
                        hero.defeatEnemy();
                    }
                } else if (decision == 2) {
                    System.out.println(hero.getName() + " has run away from " + monster.getName() + "!");
                    System.out.println(".............................................\n.......................................\n............................................");
                    break;
                }
            }
            
        } while (hero.getHealthPoints() > 0 && monster.getHealthPoints() > 0); 

        if (hero.getHealthPoints() <= 0) {
            System.out.println(hero.getName() + " has been defeated!!");
            System.out.println("Number of enemies defeated: " + (hero.getEnemiesDefeated() + 1));
        }
        
        return 0;
    }

}

