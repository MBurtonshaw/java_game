import jdk.jshell.execution.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

class Index {
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        /**
         * Initializing scanner, getting user input for name, initializing Hero
         */
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
        List<String> someList = new ArrayList<>();
        Hero hero = new Hero(name, 1, 50, 20, 0, someList, 10, 0);

        Thread.sleep(750);
        Utility.playSound("fanfare.wav");
        System.out.println(Utility.getBLUE()
                + "///////////////////////////////////////////////////////////////////////////////////////");
        System.out.println(Utility.getCYAN()
                + "/*************************************************************************************/");
        System.out.println(Utility.getBLUE() + "/******************************  " + Utility.getBLACK()
                + "WELCOME HERO," + Utility.getBLUE() + "    ************************************/");
        System.out.println(Utility.getBLUE() + "/****************************  " + Utility.getBLACK()
                + "TO ... THE GAME ... " + Utility.getBLUE() + "   ********************************/");
        System.out.println(Utility.getCYAN()
                + "/*************************************************************************************/");
        System.out.println(Utility.getBLUE()
                + "///////////////////////////////////////////////////////////////////////////////////////");

        System.out.println(Utility.getGREEN() + hero.toString());




        //

        Monster monster1 = new Monster("Barry", 20, 5, 50, Utility.getCYAN(), "");
        Monster monster2 = new Monster("Larry", 30, 10, 50, Utility.getBLACK(), "healthPotion");
        Monster monster3 = new Monster("Terry", 45, 12, 50, Utility.getMAGENTA(), "magicPotion");
        Monster monster4 = new Monster("Gary", 55, 16, 50, Utility.getYELLOW(), "");
        Monster monster5 = new Monster("Mary", 65, 22, 100, Utility.getBLUE(), "healthPotion");
        Monster monster6 = new Monster("Gorlock", 70, 26, 50, Utility.getMAGENTA(), "magicPotion");
        Monster monster7 = new Monster("Shmirgul", 90, 30, 50, Utility.getCYAN(), "healthPotion");
        Monster monster8 = new Monster("Tim", 110, 37, 100, Utility.getYELLOW(), "");
        Monster monster9 = new Monster("Hydrolscus", 120, 42, 100, Utility.getBLUE(), "");
        Monster monster10 = new Monster("Gygron", 135, 50, 0, Utility.getBLACK(), "");
        //

        List<Monster> monsterList = new ArrayList<>();
        monsterList.add(monster1);
        monsterList.add(monster2);
        monsterList.add(monster3);
        monsterList.add(monster4);
        monsterList.add(monster5);
        monsterList.add(monster6);
        monsterList.add(monster7);
        monsterList.add(monster8);
        monsterList.add(monster9);
        monsterList.add(monster10);

        /**
         * Main Battle Method
         */
        battle(hero, monsterList);

    }

    public static boolean battle(Hero hero, List<Monster> monsters) throws InterruptedException, FileNotFoundException {

        while (hero.getHealthPoints() > 0) {
            for (Monster monster : monsters) {
                Thread.sleep(1500);
                System.out.println(monster.getColor() + "/// " + monster.appear());
                handleInteraction(hero, monster);
                System.out.println(".............................................\n");
                if (hero.getHealthPoints() < 1) {
                    Thread.sleep(1000);
                    System.out.println(Utility.getRED() + "////// " + hero.getName() + " has been defeated!!");
                    Thread.sleep(1000);
                    System.out.println(
                            Utility.getBLUE() + "///////// Number of enemies defeated: " + (hero.getEnemiesDefeated()));
                    Utility.logToFile(" --- " + hero.getName() + ": defeated " + hero.getEnemiesDefeated() + " enemies before falling...");
                    Thread.sleep(2000);
                    displayHighScores();
                    break;
                }
            }

            if (hero.getHealthPoints() > 0 && monsters.get(9).getHealthPoints() <= 0) {
                Utility.playSound("win_music.wav");
                System.out.println(
                        Utility.getBLUE() + hero.getName() + " has defeated all " + (hero.getEnemiesDefeated())
                                + " enemies!!!");
                System.out.println(Utility.getBLUE() + "The kingdom is again at peace...");
                Utility.logToFile(" --- " + hero.getName() + " has defeated " + hero.getEnemiesDefeated() + " enemies and saved the kingdom!");
                Thread.sleep(5000);
                displayHighScores();
                break;
            }
        }

        return false;
    }

    public static void displayHighScores() throws InterruptedException, FileNotFoundException {
        File file = new File("highScores.txt");
        List<String> highScores = new ArrayList<>();

        if (file.exists() && file.isFile()) {
            try(Scanner scanner = new Scanner(file)) {
                String message = "";
                while (scanner.hasNextLine()) {
                    String lineOfData = scanner.nextLine();
                    if (lineOfData.contains("saved the kingdom")) {
                        highScores.add(lineOfData);
                        for ( int i = 0; i < highScores.size(); i++) {
                            if (highScores.size() > 0) {
                                message = "\n High Scores: " + "\n";
                                message += highScores.get(i) + "\n";
                            };
                        }
                    }
                }
                if (message != "") {
                    System.out.println(message);
                } else {
                    System.out.println("No past heroes have saved the kingdom yet");
                }
            } catch (FileNotFoundException e){
                System.out.println("Fail to load initial CSV file.");
            }catch (NumberFormatException e){
                System.out.println("There are some data type error in CSV file.");
            }catch (IllegalStateException e){
                System.out.println("There is un-excepted product type in the CSV file. ");
            }
        }

    }

    public static void attackMonster(Hero hero, Monster monster) throws InterruptedException {

        monster.receiveDamage(hero.getDamage());
        System.out.println(
                Utility.getGREEN() + "/// " + monster.getName() + " receives " + hero.getDamage()
                        + " points of damage!");

        if (monster.getHealthPoints() > 0) {
            hero.receiveDamage(monster.getDamage());
            System.out.println(monster.getColor() + "////// " + monster.getName() + " attacks!");
            System.out.println(monster.getColor() + "///////// " + hero.getName() + " receives " + monster.getDamage()
                    + " points of damage!");
            System.out.println(".............................................\n");
        } else {
            Thread.sleep(725);
            Utility.playSound("monster_grunt.wav");
            System.out.println(Utility.getGREEN() + "////// " + monster.getName() + " has been defeated!");
            System.out
                    .println(Utility.getBLUE() + "///////// " + hero.getName() + " has gained " + monster.getExpGiven()
                            + " exp points!");
            hero.receiveExp(monster.getExpGiven());
            if (monster.hasItem()) {
                hero.receiveItem(monster.getItem(), 1);
                System.out.println(monster.getColor() + monster.getName() + " dropped a " + monster.getItem() + "!");
                System.out.println(Utility.getGREEN() + hero.getName() + " picked up the " + monster.getItem()
                        + " and put it in their bag!");
            }
            hero.defeatEnemy();
        }
        //////////////
    }

    public static void castFireSpell(Hero hero, Monster monster) throws InterruptedException {
        if (hero.getMagicPoints() >= 15) {
            hero.castFireSpell();
            monster.takeFireDamage();
            System.out.println(Utility.getGREEN() + "////// " + monster.getName() + " has been defeated!");
            if (monster.hasItem()) {
                hero.receiveItem(monster.getItem(), 1);
                System.out.println(monster.getColor() + monster.getName() + " dropped a " + monster.getItem() + "!");
                System.out.println(Utility.getGREEN() + hero.getName() + " picked up the " + monster.getItem()
                        + " and put it in their bag!");
            }
            hero.receiveExp(monster.getExpGiven());
            hero.defeatEnemy();
        } else {
            System.out.println(Utility.getRED() + "/// Not enough magic points!" + Utility.getGREEN());
        }
    }

    public static void castFreezeSpell(Hero hero, Monster monster) throws InterruptedException {
        if (hero.getMagicPoints() > 9) {
            hero.castFreezeSpell();
            monster.takeFreezeDamage();
        } else {
            System.out.println(Utility.getRED() + "/// Not enough magic points!" + Utility.getGREEN());
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

    /**
     * Battle Method
     */

    static int handleInteraction(Hero hero, Monster monster) throws InterruptedException {

        Scanner userInput = new Scanner(System.in);

        do {
            String message = "";
            // CHECK FOR HERO LEVEL TO DETERMINE SPELLS AVAILABLE
            if (hero.getLvl() > 2) {
                if (hero.getInventory().size() > 0) {
                    if (hero.getInventory().containsKey("magicPotion")
                            && hero.getInventory().containsKey("healthPotion")) {
                        message = Utility.getGREEN()
                                + "1 - Attack  ||  2 - Run  ||  3 - Freeze Spell (10MP)  ||  4 - Fire Spell (15MP) || 5 - Drink healthPotion || 6 - Drink magicPotion";
                    } else if (hero.getInventory().containsKey("magicPotion")) {
                        message = Utility.getGREEN()
                                + "1 - Attack  ||  2 - Run  ||  3 - Freeze Spell (10MP)  ||  4 - Fire Spell (15MP) || 6 - Drink magicPotion";
                    } else if (hero.getInventory().containsKey("healthPotion")) {
                        message = Utility.getGREEN()
                                + "1 - Attack  ||  2 - Run  ||  3 - Freeze Spell (10MP)  ||  4 - Fire Spell (15MP) || 5 - Drink healthPotion";
                    }
                } else {
                    message = Utility.getGREEN()
                            + "1 - Attack  ||  2 - Run  ||  3 - Freeze Spell (10MP)  ||  4 - Fire Spell (15MP)";
                }
                System.out.println(message);
                int decision = userInput.nextInt();
                //////////////
                if (decision == 1) {
                    attackMonster(hero, monster);

                } else if (decision == 2) {
                    System.out.println(Utility.getMAGENTA() + "////// " + hero.getName() + " has run away from "
                            + monster.getName() + "!");
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
                    if (hero.getInventory().containsKey("magicPotion")
                            && hero.getInventory().containsKey("healthPotion")) {
                        message = Utility.getGREEN()
                                + "1 - Attack  ||  2 - Run  ||  3 - Freeze Spell (10MP)  || 5 - Drink healthPotion || 6 - Drink magicPotion";
                    } else if (hero.getInventory().containsKey("magicPotion")) {
                        message = Utility.getGREEN()
                                + "1 - Attack  ||  2 - Run  ||  3 - Freeze Spell (10MP)  || 6 - Drink magicPotion";
                    } else if (hero.getInventory().containsKey("healthPotion")) {
                        message = Utility.getGREEN()
                                + "1 - Attack  ||  2 - Run  ||  3 - Freeze Spell (10MP)  || 5 - Drink healthPotion";
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
                    System.out.println(Utility.getMAGENTA() + "////// " + hero.getName() + " has run away from "
                            + monster.getName() + "!");
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
                    if (hero.getInventory().containsKey("magicPotion")
                            && hero.getInventory().containsKey("healthPotion")) {
                        message = Utility.getGREEN()
                                + "1 - Attack  ||  2 - Run  || 5 - Drink healthPotion || 6 - Drink magicPotion";
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
                    System.out.println(Utility.getMAGENTA() + "////// " + hero.getName() + " has run away from "
                            + monster.getName() + "!");
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

        } while (hero.getHealthPoints() > 0 && monster.getHealthPoints() > 0);

        return 0;
    }

}