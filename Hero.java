import java.util.HashMap;
import java.util.List;

import java.util.ArrayList;
import java.util.Map;

public class Hero {

    private String name;
    private int lvl;
    private int healthPoints;
    private int magicPoints;
    private int expPoints = 0;
    private List<String> spellsList = new ArrayList<>();

    private Map<String, Integer> inventory = new HashMap<>();
    private int damage = 10;
    private int enemiesDefeated;

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

    public Map<String, Integer> getInventory() {
        return inventory;
    }

    public void castFreezeSpell() throws InterruptedException {
        if (getMagicPoints() >= 10) {
            System.out.println(Utility.getCYAN() + this.getName() + " has cast freeze spell!");
            Utility.playSound("eerie_wind.wav");
            Thread.sleep(1000);
            this.magicPoints -= 10;
            System.out.println(Utility.getCYAN() + "Enemy is hit with frost! Damage is halved!");
            Thread.sleep(1000);
            System.out.println("...");
            Thread.sleep(1000);
        } else {
            System.out.println(Utility.getRED() + "///" + "Not enough magic points!" + Utility.getGREEN());
        }

    }

    public void castFireSpell() throws InterruptedException {
        if (getMagicPoints() >= 15) {
            System.out.println(Utility.getYELLOW() + this.getName() + " has cast fire spell!");
            Utility.playSound("crackling_fire.wav");
            Thread.sleep(1200);
            this.magicPoints -= 15;
            System.out.println(Utility.getYELLOW() + "Enemy has been burnt!");
            Thread.sleep(1200);
            System.out.println("...");
            Thread.sleep(1000);
        } else {
            System.out.println(Utility.getRED() + "///" + "Not enough magic points!" + Utility.getGREEN());
        }
    }

    public void drinkMagicPotion() throws InterruptedException {

        for (Map.Entry<String, Integer> item : inventory.entrySet()) {
            if (item.getKey().equals("magicPotion")) {
                if (item.getValue() > 0) {
                    Utility.playSound("uncork.wav");
                    Utility.playSound("drink.wav");
                    Thread.sleep(1000);
                    System.out.println(getName() + " has used a magic potion!");
                    Thread.sleep(1000);
                    System.out.println(getName() + " has gained 10 MP!");
                    this.magicPoints += 10;
                    System.out.println("Current MP: " + getMagicPoints());
                } else {
                    System.out.println("No magic potions!");
                }
            }
        }
        if (inventory.containsKey("magicPotion")) {
            inventory.remove("magicPotion", 1);
        }
    }

    public void drinkHealthPotion() throws InterruptedException {

        for (Map.Entry<String, Integer> item : inventory.entrySet()) {
            if (item.getKey().equals("healthPotion")) {
                if (item.getValue() > 0) {
                    Utility.playSound("uncork.wav");
                    Utility.playSound("drink.wav");
                    Thread.sleep(1000);
                    System.out.println(getName() + " has used a health potion!");
                    Thread.sleep(1000);
                    System.out.println(getName() + " has gained 30 HP!");
                    this.healthPoints += 30;
                    System.out.println("Current HP: " + getHealthPoints());
                } else {
                    System.out.println("No health potions!");
                }
            }
        }
        if (inventory.containsKey("healthPotion")) {
            inventory.remove("healthPotion", 1);
        }
    }

    public String toString() {
        String returner = "";

        if (this.getSpellsList().size() > 0) {
            returner = Utility.getGREEN() + getName() + " is lvl " + getLvl() + "!\n Their health points are: " + getHealthPoints()
                    + ", their magic points are: " + getMagicPoints() + ", their damage is: " + getDamage()
                    + ",\n and their experience points are currently: " + getExpPoints() + ".\n" + "\n"
                    + " They have the "
                    + getSpellsList() + " spell(s).\n";
        } else {
            returner = Utility.getGREEN() + getName() + " is lvl " + getLvl() + "!\n Their health points are: " + getHealthPoints()
                    + ", their magic points are: " + getMagicPoints() + ", and their damage is: " + getDamage()
                    + ".\n Their experience points are currently: " + getExpPoints() + ".\n";
        }
        if (this.getInventory().size() > 0) {
            returner += " Current items: " + getInventory() + "\n";
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

    public void receiveItem(String item, int quantity) {
        inventory.put(item, quantity);
    }

    public int resetExp() {
        return expPoints = 0;
    }

    public int defeatEnemy() {
        return enemiesDefeated += 1;
    }

}
