import jdk.jshell.execution.Util;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.awt.Color;

class Index {
    public static void main(String[] args) throws InterruptedException {

        String BLACK = "\u001B[30m";
        String RED = "\u001B[31m";
        String GREEN = "\u001B[32m";
        String YELLOW = "\u001B[33m";
        String BLUE = "\u001B[34m";
        String MAGENTA = "\u001B[35m";
        String CYAN = "\u001B[36m";
        String WHITE = "\u001B[37m";


        /* ////////// */
        /* Initializing scanner, getting user input for name, initializing Hero */
        /* ////////// */
        Scanner userInput = new Scanner(System.in);
        System.out.println("Welcome, hero!");
        System.out.println(" ...");
        System.out.println(" ...");
        System.out.println(" ...\n");
        Thread.sleep(700);
        System.out.println("Could you, uhhhh......\n");
        Utility.playSound("ahem.wav");
        Thread.sleep(1100);
        System.out.println("Could you remind me of your name?");

        String name = userInput.nextLine();

        Thread.sleep(750);
        Utility.playSound("fanfare.wav");
        System.out.println(BLUE + "///////////////////////////////////////////////////////////////////////////////////////");
        System.out.println(CYAN + "/*************************************************************************************/");
        System.out.println(BLUE + "/******************************  "	 + BLACK + "WELCOME HERO," + BLUE + "    ************************************/");
        System.out.println(BLUE + "/****************************  " + BLACK + "TO ... THE GAME ... " + BLUE + "   ********************************/");
        System.out.println(CYAN + "/*************************************************************************************/");
        System.out.println(BLUE + "///////////////////////////////////////////////////////////////////////////////////////");


        List<String> someList = new ArrayList<>();
        Hero hero1 = new Hero(name, 1, 50, 20, 0, someList, 10, 0);
        System.out.println(GREEN + hero1.toString());

        //


        /* ////////// */
        /* First battle */
        /* ////////// */
        Thread.sleep(3000);
        Monster monster1 = new Monster("Barry", 20, 5, 50, CYAN, "magicPotion");
        System.out.println(monster1.getColor() + "/// " + monster1.appear());
        getAction(hero1, monster1);
        System.out.println(".............................................\n");

        /* ////////// */
        /* Second battle */
        /* ////////// */
        Thread.sleep(1000);
        Monster monster2 = new Monster("Larry", 30, 10, 50, BLACK, "healthPotion");
        System.out.println(monster2.getColor() + monster2.appear());
        getAction(hero1, monster2);
        System.out.println(".............................................\n");

        /* ////////// */
        /* Third battle */
        /* ////////// */
        Thread.sleep(1000);
        Monster monster3 = new Monster("Terry", 45, 12, 35, MAGENTA, "");
        System.out.println(monster3.getColor() + monster3.appear());
        getAction(hero1, monster3);
        System.out.println(".............................................\n");

        /* ////////// */
        /* Fourth battle */
        /* ////////// */
        Thread.sleep(1000);
        if (hero1.getHealthPoints() > 0) {
            Monster monster4 = new Monster("Gary", 55, 17, 75, YELLOW, "");
            System.out.println(monster4.getColor() + monster4.appear());
            getAction(hero1, monster4);
            System.out.println(".............................................\n");
        }

        /* ////////// */
        /* Fifth battle */
        /* ////////// */
        Thread.sleep(1000);
        if (hero1.getHealthPoints() > 0) {
            Monster monster5 = new Monster("Mary", 65, 20, 100, BLUE, "");
            System.out.println(monster5.getColor() + monster5.appear());
            getAction(hero1, monster5);
            System.out.println(".............................................\n");

            /* ////////// */
            /* Win condition */
            /* ////////// */
            Thread.sleep(1000);
            if (hero1.getHealthPoints() > 0 && monster5.getHealthPoints() <= 0) {
                Utility.playSound("win_music.wav");
                System.out.println(
                        BLUE + hero1.getName() + " has defeated all " + (hero1.getEnemiesDefeated()) + " enemies!!!");
                System.out.println(BLUE + "The kingdom is again at peace...");
                Thread.sleep(5000);
            }
        }
    }

    /* ////////// */
    /* Battle Method */
    /* ////////// */

    static int getAction(Hero hero, Monster monster) throws InterruptedException {
        String BLACK = "\u001B[30m";
        String RED = "\u001B[31m";
        String GREEN = "\u001B[32m";
        String YELLOW = "\u001B[33m";
        String BLUE = "\u001B[34m";
        String MAGENTA = "\u001B[35m";
        String CYAN = "\u001B[36m";
        String WHITE = "\u001B[37m";


        Scanner userInput = new Scanner(System.in);

        do {
            String message = "";
            // CHECK FOR HERO LEVEL TO DETERMINE SPELLS AVAILABLE
            if (hero.getLvl() > 2) {
                if (hero.getItemMap().size() > 0) {
                    message = GREEN + "1 - Attack  ||  2 - Run  ||  3 - Freeze Spell (10MP)  ||  4 - Fire Spell (15MP) || 5 - Drink healthPotion || 6 - Drink magicPotion";
                    System.out.println(message);
                } else {
                    message = GREEN + "1 - Attack  ||  2 - Run  ||  3 - Freeze Spell (10MP)  ||  4 - Fire Spell (15MP)";
                    System.out.println(message);

                }
                int decision = userInput.nextInt();
                //////////////
                if (decision == 1) {
                    monster.receiveDamage(hero.getDamage());
                    System.out.println(
                            GREEN + "/// " + monster.getName() + " receives " + hero.getDamage() + " points of damage!");
                    if (monster.getHealthPoints() > 0) {
                        hero.receiveDamage(monster.getDamage());
                        System.out.println(monster.getColor() + "////// " + monster.getName() + " attacks!");
                        System.out.println(monster.getColor() + "///////// " + hero.getName() + " receives " + monster.getDamage()
                                + " points of damage!");
                        System.out.println(".............................................\n");
                    } else {
                        Thread.sleep(725);
                        Utility.playSound("monster_grunt.wav");
                        System.out.println(GREEN + "////// " + monster.getName() + " has been defeated!");
                        System.out.println(GREEN + "///////// " + hero.getName() + " has gained " + monster.getExpGiven()
                                + " exp points!");
                        if (monster.hasItem()) {
                            hero.receiveItem(monster.getItem(), 1);
                            System.out.println(monster.getColor() + monster.getName() + " dropped a " + monster.getItem() + "!");
                            System.out.println(GREEN + hero.getName() + " picked up the " + monster.getItem() + " and put it in their bag!");
                        }
                        hero.defeatEnemy();
                        hero.receiveExp(monster.getExpGiven());
                    }
                    //////////////
                } else if (decision == 2) {
                    System.out.println(MAGENTA + "////// " + hero.getName() + " has run away from " + monster.getName() + "!");
                    break;
                    //////////////
                } else if (decision == 3) {
                    hero.castFreezeSpell();
                    monster.takeFreezeDamage();
                    //////////////
                } else if (decision == 4) {
                    if (hero.getMagicPoints() >= 15) {
                        hero.castFireSpell();
                        monster.takeFireDamage();
                        System.out.println(GREEN + "////// " + monster.getName() + " has been defeated!");
                        if (monster.hasItem()) {
                            hero.receiveItem(monster.getItem(), 1);
                            System.out.println(monster.getColor() + monster.getName() + " dropped a " + monster.getItem() + "!");
                            System.out.println(GREEN + hero.getName() + " picked up the " + monster.getItem() + " and put it in their bag!");
                        }
                        hero.defeatEnemy();
                        hero.receiveExp(monster.getExpGiven());
                    } else {
                        System.out.println(RED + "/// Not enough magic points!");
                    }
                } else if (decision == 5) {
                    hero.drinkHealthPotion();
                    hero.receiveDamage(monster.getDamage());
                    System.out.println(monster.getColor() + "////// " + monster.getName() + " attacks!");
                    System.out.println(monster.getColor() + "///////// " + hero.getName() + " receives " + monster.getDamage()
                            + " points of damage!");
                    System.out.println(".............................................\n");
                } else if (decision == 6) {
                    hero.drinkMagicPotion();
                    hero.receiveDamage(monster.getDamage());
                    System.out.println(monster.getColor() + "////// " + monster.getName() + " attacks!");
                    System.out.println(monster.getColor() + "///////// " + hero.getName() + " receives " + monster.getDamage()
                            + " points of damage!");
                    System.out.println(".............................................\n");
                } else {

                    System.out.println(RED + "/// Please enter a valid option");
                }

                // CHECK FOR HERO LEVEL TO DETERMINE SPELLS AVAILABLE
            } else if (hero.getLvl() > 1) {
                if (hero.getItemMap().size() > 0) {
                    message = GREEN + "1 - Attack  ||  2 - Run  ||  3 - Freeze Spell (10MP)  || 5 - Drink healthPotion || 6 - Drink magicPotion";
                    System.out.println(message);
                } else {
                    message = GREEN + "1 - Attack  ||  2 - Run  ||  3 - Freeze Spell (10MP)";
                    System.out.println(message);

                }
                int decision = userInput.nextInt();
                //////////////
                if (decision == 1) {
                    monster.receiveDamage(hero.getDamage());
                    System.out.println(
                            GREEN + "/// " + monster.getName() + " receives " + hero.getDamage() + " points of damage!");
                    if (monster.getHealthPoints() > 0) {
                        hero.receiveDamage(monster.getDamage());
                        System.out.println(monster.getColor() + "////// " + monster.getName() + " attacks!");
                        System.out.println(monster.getColor() + "///////// " + hero.getName() + " receives " + monster.getDamage()
                                + " points of damage!");
                        System.out.println(".............................................\n");
                    } else {
                        Thread.sleep(725);
                        Utility.playSound("monster_grunt.wav");
                        System.out.println(GREEN + "////// " + monster.getName() + " has been defeated!");
                        System.out.println(BLUE + "///////// " + hero.getName() + " has gained " + monster.getExpGiven()
                                + " exp points!");
                        if (monster.hasItem()) {
                            hero.receiveItem(monster.getItem(), 1);
                            System.out.println(monster.getColor() + monster.getName() + " dropped a " + monster.getItem() + "!");
                            System.out.println(GREEN + hero.getName() + " picked up the " + monster.getItem() + " and put it in their bag!");
                        }
                        hero.defeatEnemy();
                        hero.receiveExp(monster.getExpGiven());
                    }
                    //////////////
                } else if (decision == 2) {
                    System.out.println(MAGENTA + "////// " + hero.getName() + " has run away from " + monster.getName() + "!");
                    System.out.println(".............................................\n");
                    break;
                    //////////////
                } else if (decision == 3) {
                    if (hero.getMagicPoints() > 9) {
                        hero.castFreezeSpell();
                        monster.takeFreezeDamage();
                    } else {
                        System.out.println(RED + "/// Not enough magic points!");
                    }
                } else if (decision == 5) {
                    hero.drinkHealthPotion();
                    hero.receiveDamage(monster.getDamage());
                    System.out.println(monster.getColor() + "////// " + monster.getName() + " attacks!");
                    System.out.println(monster.getColor() + "///////// " + hero.getName() + " receives " + monster.getDamage()
                            + " points of damage!");
                    System.out.println(".............................................\n");
                } else if (decision == 6) {
                    hero.drinkMagicPotion();
                    hero.receiveDamage(monster.getDamage());
                    System.out.println(monster.getColor() + "////// " + monster.getName() + " attacks!");
                    System.out.println(monster.getColor() + "///////// " + hero.getName() + " receives " + monster.getDamage()
                            + " points of damage!");
                    System.out.println(".............................................\n");
                } else {
                    System.out.println(RED + "/// Please enter a valid option");
                }

                // CHECK FOR HERO LEVEL TO DETERMINE SPELLS AVAILABLE
            } else {
                if (hero.getItemMap().size() > 0) {
                    message = GREEN + "1 - Attack  ||  2 - Run  || 5 - Drink healthPotion || 6 - Drink magicPotion";
                    System.out.println(message);
                } else {
                    message = GREEN + "1 - Attack  ||  2 - Run";
                    System.out.println(message);

                }
                int decision = userInput.nextInt();
                //////////////
                if (decision == 1) {
                    monster.receiveDamage(hero.getDamage());
                    System.out.println(
                            GREEN + "/// " + monster.getName() + " receives " + hero.getDamage() + " points of damage!");

                    if (monster.getHealthPoints() > 0) {
                        hero.receiveDamage(monster.getDamage());
                        System.out.println(monster.getColor() + "////// " + monster.getName() + " attacks!");
                        System.out.println(monster.getColor() + "///////// " + hero.getName() + " receives " + monster.getDamage()
                                + " points of damage!");
                        System.out.println(".............................................\n");
                    } else {
                        Thread.sleep(725);
                        Utility.playSound("monster_grunt.wav");
                        System.out.println(GREEN + "////// " + monster.getName() + " has been defeated!");
                        System.out.println(BLUE + "///////// " + hero.getName() + " has gained " + monster.getExpGiven()
                                + " exp points!");
                        if (monster.hasItem()) {
                            hero.receiveItem(monster.getItem(), 1);
                            System.out.println(monster.getColor() + monster.getName() + " dropped a " + monster.getItem() + "!");
                            System.out.println(GREEN + hero.getName() + " picked up the " + monster.getItem() + " and put it in their bag!");
                        }
                        hero.defeatEnemy();
                        hero.receiveExp(monster.getExpGiven());
                    }
                    //////////////
                } else if (decision == 2) {
                    System.out.println(MAGENTA + "////// " + hero.getName() + " has run away from " + monster.getName() + "!");
                    System.out.println(".............................................\n");
                    break;
                } else if (decision == 5) {
                    hero.drinkHealthPotion();
                    hero.receiveDamage(monster.getDamage());
                    System.out.println(monster.getColor() + "////// " + monster.getName() + " attacks!");
                    System.out.println(monster.getColor() + "///////// " + hero.getName() + " receives " + monster.getDamage()
                            + " points of damage!");
                    System.out.println(".............................................\n");
                } else if (decision == 6) {
                    hero.drinkMagicPotion();
                    hero.receiveDamage(monster.getDamage());
                    System.out.println(monster.getColor() + "////// " + monster.getName() + " attacks!");
                    System.out.println(monster.getColor() + "///////// " + hero.getName() + " receives " + monster.getDamage()
                            + " points of damage!");
                    System.out.println(".............................................\n");
                } else {
                    System.out.println("Please enter a valid option");
                }
            }
            if (hero.getHealthPoints() < 1) {
                Thread.sleep(1000);
                System.out.println(RED + "////// " + hero.getName() + " has been defeated!!");
                Thread.sleep(1000);
                System.out.println(BLUE + "///////// Number of enemies defeated: " + (hero.getEnemiesDefeated()));
            }
        } while (hero.getHealthPoints() > 0 && monster.getHealthPoints() > 0);

        return 0;
    }

}