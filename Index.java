import jdk.jshell.execution.Util;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

class Index {
    public static void main(String[] args) throws InterruptedException {
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
        System.out.println(Utility.getBLUE() + "///////////////////////////////////////////////////////////////////////////////////////");
        System.out.println(Utility.getCYAN() + "/*************************************************************************************/");
        System.out.println(Utility.getBLUE() + "/******************************  "	 + Utility.getBLACK() + "WELCOME HERO," + Utility.getBLUE() + "    ************************************/");
        System.out.println(Utility.getBLUE() + "/****************************  " + Utility.getBLACK() + "TO ... THE GAME ... " + Utility.getBLUE() + "   ********************************/");
        System.out.println(Utility.getCYAN() + "/*************************************************************************************/");
        System.out.println(Utility.getBLUE() + "///////////////////////////////////////////////////////////////////////////////////////");


        List<String> someList = new ArrayList<>();
        Hero hero1 = new Hero(name, 1, 50, 20, 0, someList, 10, 0);
        System.out.println(Utility.getGREEN() + hero1.toString());

        //

        Monster monster1 = new Monster("Barry", 20, 5, 50, Utility.getCYAN(), "");
        Monster monster2 = new Monster("Larry", 30, 10, 50, Utility.getBLACK(), "healthPotion");
        Monster monster3 = new Monster("Terry", 45, 12, 50, Utility.getMAGENTA(), "magicPotion");
        Monster monster4 = new Monster("Gary", 55, 16, 50, Utility.getYELLOW(), "");
        Monster monster5 = new Monster("Mary", 65, 22, 100, Utility.getBLUE(), "healthPotion");
        Monster monster6 = new Monster("Gorlock", 70, 26,  50, Utility.getMAGENTA(), "magicPotion");
        Monster monster7 = new Monster("Shmirgul", 90, 30, 50, Utility.getCYAN(), "healthPotion");
        Monster monster8 = new Monster("Tim", 110, 37, 100, Utility.getYELLOW(), "");
        Monster monster9 = new Monster("Hydrolscus", 120, 42, 100, Utility.getBLUE(), "");
        Monster monster10 = new Monster("Gygron", 135, 50, 0, Utility.getBLACK(), "");
        //

        /* First battle */
        battle(hero1, monster1);

        /* Second battle */
        battle(hero1, monster2);

        /* Third battle */
        battle(hero1, monster3);

        /* Fourth battle */
        if (hero1.getHealthPoints() > 0) {
            battle(hero1, monster4);
        }

        /* Fifth battle */
        if (hero1.getHealthPoints() > 0) {
            battle(hero1, monster5);
        }
        /* Sixth battle */
        if (hero1.getHealthPoints() > 0) {
            battle(hero1, monster6);
        }
        /* Seventh battle */
        if (hero1.getHealthPoints() > 0) {
            battle(hero1, monster7);
        }
        /* Eighth battle */
        if (hero1.getHealthPoints() > 0) {
            battle(hero1, monster8);
        }
        /* Ninth battle */
        if (hero1.getHealthPoints() > 0) {
            battle(hero1, monster9);
        }
        /* Tenth battle */
        if (hero1.getHealthPoints() > 0) {
            battle(hero1, monster10);

            /* ////////// */
            /* Win condition */
            /* ////////// */
            Thread.sleep(1000);
            if (hero1.getHealthPoints() > 0 && monster10.getHealthPoints() <= 0) {
                Utility.playSound("win_music.wav");
                System.out.println(
                        Utility.getBLUE() + hero1.getName() + " has defeated all " + (hero1.getEnemiesDefeated()) + " enemies!!!");
                System.out.println(Utility.getBLUE() + "The kingdom is again at peace...");
                Thread.sleep(5000);
            }
        }

    }

    public static boolean battle(Hero hero, Monster monster) throws InterruptedException {
        Thread.sleep(1500);
        System.out.println(monster.getColor() + "/// " + monster.appear());
        handleInteraction(hero, monster);
        System.out.println(".............................................\n");
        return true;
    }

    public static void attackMonster(Hero hero, Monster monster) throws InterruptedException {
        String GREEN = "\u001B[32m";
        String BLUE = "\u001B[34m";

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
    }

    public static void castFireSpell(Hero hero, Monster monster) throws InterruptedException {
        String GREEN = "\u001B[32m";
        String RED = "\u001B[31m";
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
            System.out.println(RED + "/// Not enough magic points!" + GREEN);
        }
    }

    public static void castFreezeSpell(Hero hero, Monster monster) throws InterruptedException {
        String RED = "\u001B[31m";
        String GREEN = "\u001B[32m";
        if (hero.getMagicPoints() > 9) {
            hero.castFreezeSpell();
            monster.takeFreezeDamage();
        } else {
            System.out.println(RED + "/// Not enough magic points!" + GREEN);
        }
    }

    public static void drinkHealthPotion(Hero hero, Monster monster) throws InterruptedException {
        hero.drinkHealthPotion();
        hero.receiveDamage(monster.getDamage());
        System.out.println(monster.getColor() + "////// " + monster.getName() + " attacks!");
        System.out.println(monster.getColor() + "///////// " + hero.getName() + " receives " + monster.getDamage()
                + " points of damage!");
        System.out.println(".............................................\n");
    }

    public static void drinkMagicPotion(Hero hero, Monster monster) throws InterruptedException {
        hero.drinkMagicPotion();
        hero.receiveDamage(monster.getDamage());
        System.out.println(monster.getColor() + "////// " + monster.getName() + " attacks!");
        System.out.println(monster.getColor() + "///////// " + hero.getName() + " receives " + monster.getDamage()
                + " points of damage!");
        System.out.println(".............................................\n");
    }

    /* ////////// */
    /* Battle Method */
    /* ////////// */

    static int handleInteraction(Hero hero, Monster monster) throws InterruptedException {

        Scanner userInput = new Scanner(System.in);

        do {
            String message = "";
            // CHECK FOR HERO LEVEL TO DETERMINE SPELLS AVAILABLE
            if (hero.getLvl() > 2) {
                if (hero.getInventory().size() > 0) {
                    if (hero.getInventory().containsKey("magicPotion") && hero.getInventory().containsKey("healthPotion")) {
                        message = Utility.getGREEN() + "1 - Attack  ||  2 - Run  ||  3 - Freeze Spell (10MP)  ||  4 - Fire Spell (15MP) || 5 - Drink healthPotion || 6 - Drink magicPotion";
                    } else if (hero.getInventory().containsKey("magicPotion")) {
                        message = Utility.getGREEN() + "1 - Attack  ||  2 - Run  ||  3 - Freeze Spell (10MP)  ||  4 - Fire Spell (15MP) || 6 - Drink magicPotion";
                    } else if (hero.getInventory().containsKey("healthPotion")) {
                        message = Utility.getGREEN() + "1 - Attack  ||  2 - Run  ||  3 - Freeze Spell (10MP)  ||  4 - Fire Spell (15MP) || 5 - Drink healthPotion";
                    }
                } else {
                    message = Utility.getGREEN() + "1 - Attack  ||  2 - Run  ||  3 - Freeze Spell (10MP)  ||  4 - Fire Spell (15MP)";
                }
                System.out.println(message);
                int decision = userInput.nextInt();
                //////////////
                if (decision == 1) {
                    attackMonster(hero, monster);

                } else if (decision == 2) {
                    System.out.println(Utility.getMAGENTA() + "////// " + hero.getName() + " has run away from " + monster.getName() + "!");
                    break;
                    //////////////
                } else if (decision == 3) {
                    castFreezeSpell(hero, monster);
                    //////////////
                } else if (decision == 4) {
                        castFireSpell(hero, monster);
                } else if (decision == 5) {
                    drinkHealthPotion(hero, monster);
                } else if (decision == 6) {
                    drinkMagicPotion(hero, monster);
                } else {

                    System.out.println(Utility.getRED() + "/// Please enter a valid option");
                }

                // CHECK FOR HERO LEVEL TO DETERMINE SPELLS AVAILABLE
            } else if (hero.getLvl() > 1) {
                if (hero.getInventory().size() > 0) {
                    if (hero.getInventory().containsKey("magicPotion") && hero.getInventory().containsKey("healthPotion")) {
                        message = Utility.getGREEN() + "1 - Attack  ||  2 - Run  ||  3 - Freeze Spell (10MP)  || 5 - Drink healthPotion || 6 - Drink magicPotion";
                    } else if (hero.getInventory().containsKey("magicPotion")) {
                        message = Utility.getGREEN() + "1 - Attack  ||  2 - Run  ||  3 - Freeze Spell (10MP)  || 6 - Drink magicPotion";
                    } else if (hero.getInventory().containsKey("healthPotion")) {
                        message = Utility.getGREEN() + "1 - Attack  ||  2 - Run  ||  3 - Freeze Spell (10MP)  || 5 - Drink healthPotion";
                    }
                } else {
                    message = Utility.getGREEN() + "1 - Attack  ||  2 - Run  ||  3 - Freeze Spell (10MP)";
                }
                System.out.println(message);
                int decision = userInput.nextInt();
                //////////////
                if (decision == 1) {
                    attackMonster(hero, monster);
                } else if (decision == 2) {
                    System.out.println(Utility.getMAGENTA() + "////// " + hero.getName() + " has run away from " + monster.getName() + "!");
                    System.out.println(".............................................\n");
                    break;
                    //////////////
                } else if (decision == 3) {
                    castFreezeSpell(hero, monster);
                } else if (decision == 5) {
                    drinkHealthPotion(hero, monster);
                } else if (decision == 6) {
                    drinkMagicPotion(hero, monster);
                } else {
                    System.out.println(Utility.getRED() + "/// Please enter a valid option");
                }

                // CHECK FOR HERO LEVEL TO DETERMINE SPELLS AVAILABLE
            } else {
                if (hero.getInventory().size() > 0) {
                    if (hero.getInventory().containsKey("magicPotion") && hero.getInventory().containsKey("healthPotion")) {
                        message = Utility.getGREEN() + "1 - Attack  ||  2 - Run  || 5 - Drink healthPotion || 6 - Drink magicPotion";
                    } else if (hero.getInventory().containsKey("magicPotion")) {
                        message = Utility.getGREEN() + "1 - Attack  ||  2 - Run  || 6 - Drink magicPotion";
                    } else if (hero.getInventory().containsKey("healthPotion")) {
                        message = Utility.getGREEN() + "1 - Attack  ||  2 - Run  || 5 - Drink healthPotion";
                    }
                } else {
                    message = Utility.getGREEN() + "1 - Attack  ||  2 - Run";
                }
                System.out.println(message);
                int decision = userInput.nextInt();
                //////////////
                if (decision == 1) {
                   attackMonster(hero, monster);
                } else if (decision == 2) {
                    System.out.println(Utility.getMAGENTA() + "////// " + hero.getName() + " has run away from " + monster.getName() + "!");
                    System.out.println(".............................................\n");
                    break;
                } else if (decision == 5) {
                    drinkHealthPotion(hero, monster);
                } else if (decision == 6) {
                    drinkMagicPotion(hero, monster);
                } else {
                    System.out.println("Please enter a valid option");
                }
            }
            if (hero.getHealthPoints() < 1) {
                Thread.sleep(1000);
                System.out.println(Utility.getRED() + "////// " + hero.getName() + " has been defeated!!");
                Thread.sleep(1000);
                System.out.println(Utility.getBLUE() + "///////// Number of enemies defeated: " + (hero.getEnemiesDefeated()));
            }
        } while (hero.getHealthPoints() > 0 && monster.getHealthPoints() > 0);

        return 0;
    }




}