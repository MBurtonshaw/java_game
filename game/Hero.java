import java.util.List;

import java.util.ArrayList;

public class Hero {
    private String name;
    private int lvl;
    private int healthPoints;
    private int magicPoints;
    private int expPoints = 0;
    private List<String> spellsList = new ArrayList<>();
    private int damage = 10;
    private int enemiesDefeated;

    //hero needs exp bar, spells list, damage, run feature, hit feature

    public Hero(String name, int lvl, int healthPoints, int magicPoints, int expPoints, List<String> spellsList, int damage, int enemiesDefeated ) {
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
        this.healthPoints = (int)( healthPoints * 5 ) / 2;
        this.magicPoints = magicPoints + 10;
        this.damage = (int)(( damage * 3 ) / 1.8);
        //if level two, add freeze spell to list
        if (this.getLvl() == 2) {
            spellsList.add("Freeze");
        }
        //if level three, add fire spell to list
        if (this.getLvl() == 3) {
            spellsList.add("Fire");
        }
        this.resetExp();
        this.expPoints = leftovers;
        System.out.println(".............................................\n");
        System.out.println(".............................................\n");
        System.out.println(this.toString());
    }

    public List<String> getSpellsList() {
        return spellsList;
    }

    public void castFreezeSpell() {
        if (this.magicPoints > 9) {
            System.out.println(this.getName() + " has cast freeze spell!");
            this.magicPoints -= 10;
            System.out.println("Enemy is hit with frost! Damage is halved!");
        } else {
            System.out.println("Not enough magic points!");
        }
    }

    public void castFireSpell() {
        if (this.magicPoints > 14) {
            System.out.println(this.getName() + " has cast fire spell!");
            this.magicPoints -= 15;
            System.out.println("Enemy has been burnt!");
        } else {
            System.out.println("Not enough magic points!");
        }
    }

    public String toString() {
        return getName() + " is lvl " + getLvl() + ". Their health points are: " + getHealthPoints() + ", their magic points are: " + getMagicPoints() + ", their damage is: " + getDamage() + " and their experience points are currently: " + getExpPoints() + ". They have the " + getSpellsList() + " spells.";
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

    public int resetExp() {
        return expPoints = 0;
    }

    public int defeatEnemy() {
        return enemiesDefeated += 1;
    }

}
