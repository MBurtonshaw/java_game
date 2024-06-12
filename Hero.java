import java.util.List;

import java.util.ArrayList;

public class Hero {


    private String name;
    private int lvl;
    private int healthPoints;
    private int magicPoints;
    private int expPoints = 0;
    private List<String> spellsList = new ArrayList<>();
    private List<String> itemList = new ArrayList<>();
    private int damage = 10;
    private int enemiesDefeated;

    String BLACK = "\u001B[30m";
    String RED = "\u001B[31m";
    String GREEN = "\u001B[32m";
    String YELLOW = "\u001B[33m";
    String BLUE = "\u001B[34m";
    String MAGENTA = "\u001B[35m";
    String CYAN = "\u001B[36m";
    String WHITE = "\u001B[37m";

    public Hero(String name, int lvl, int healthPoints, int magicPoints, int expPoints, List<String> spellsList,
            int damage, int enemiesDefeated) {
        this.name = name;
        this.lvl = lvl;
        this.healthPoints = healthPoints;
        this.magicPoints = magicPoints;
        this.expPoints = expPoints;
        this.spellsList = spellsList;
        this.damage = damage;
        this.enemiesDefeated = enemiesDefeated;
    }

    public String getName() {
        return name;
    }

    public int getLvl() {
        return lvl;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getMagicPoints() {
        return magicPoints;
    }

    public int getExpPoints() {
        return expPoints;
    }

    public int getDamage() {
        return damage;
    }

    public int getEnemiesDefeated() {
        return enemiesDefeated;
    }

    public void levelUp(int leftovers) {
        this.lvl = lvl + 1;
        this.healthPoints = (int) (healthPoints * 5) / 2;
        this.magicPoints = magicPoints + 10;
        this.damage = (int) ((damage * 3) / 1.8);
        // if level two, add freeze spell to list
        if (this.getLvl() == 2) {
            spellsList.add("Freeze");
        }
        // if level three, add fire spell to list
        if (this.getLvl() == 3) {
            spellsList.add("Fire");
        }
        this.resetExp();
        this.expPoints = leftovers;
        System.out.println(".............................................\n");
        System.out.println(this.toString());
    }

    public List<String> getSpellsList() {
        return spellsList;
    }

    public List<String> getItemList() {
        return itemList;
    }

    public void castFreezeSpell() throws InterruptedException {
        if (this.magicPoints > 9) {
            System.out.println(CYAN + this.getName() + " has cast freeze spell!");
            Utility.playSound("eerie_wind.wav");
            Thread.sleep(1000);
            this.magicPoints -= 10;
            System.out.println(CYAN + "Enemy is hit with frost! Damage is halved!");
            Thread.sleep(1000);
            System.out.println("...");
            Thread.sleep(1000);
        } else {
            System.out.println(RED + "Not enough magic points!");
        }
    }

    public void castFireSpell() throws InterruptedException {
        if (this.magicPoints > 14) {
            System.out.println(YELLOW + this.getName() + " has cast fire spell!");
            Utility.playSound("crackling_fire.wav");
            Thread.sleep(1200);
            this.magicPoints -= 15;
            System.out.println(YELLOW + "Enemy has been burnt!");
            Thread.sleep(1200);
            System.out.println("...");
            Thread.sleep(1000);
        } else {
            System.out.println(RED + "Not enough magic points!");
        }
    }

    public String toString() {
        String returner = "";

        if (this.getSpellsList().size() > 0) {
            returner = GREEN + getName() + " is lvl " + getLvl() + "!\n Their health points are: " + getHealthPoints()
                    + ", their magic points are: " + getMagicPoints() + ", their damage is: " + getDamage()
                    + ",\n and their experience points are currently: " + getExpPoints() + ".\n" + "Current items: " + getItemList() + "\n" + " They have the "
                    + getSpellsList() + " spell(s).";
        } else {
            returner = GREEN + getName() + " is lvl " + getLvl() + "!\n Their health points are: " + getHealthPoints()
                    + ", their magic points are: " + getMagicPoints() + ", and their damage is: " + getDamage()
                    + ".\n Their experience points are currently: " + getExpPoints() + ".\n";
        }
        return returner;
    }

    public int receiveDamage(int damageTaken) {
        return healthPoints -= damageTaken;
    }

    public void receiveExp(int expGiven) {
        expPoints += expGiven;
        if (expPoints >= 100) {
            int leftovers = expPoints - 100;
            this.levelUp(leftovers);
        }
    }

    public void receiveItem(String item) {
        itemList.add(item);
    }

    public int resetExp() {
        return expPoints = 0;
    }

    public int defeatEnemy() {
        return enemiesDefeated += 1;
    }

}
