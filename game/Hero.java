import java.util.List;

import java.util.ArrayList;

public class Hero {
    private String name;
    private int lvl;
    private int healthPoints;
    private int magicPoints;
    private int expPoints = 0;
    //private List<String> spellsList = new ArrayList<>();
    private int damage = 10;

    //hero needs exp bar, spells list, damage, run feature, hit feature

    public Hero(String name, int lvl, int healthPoints, int magicPoints, int expPoints, int damage /*List<String> spellsList*/) {
        this.name = name;
        this.lvl = lvl;
        this.healthPoints = healthPoints;
        this.magicPoints = magicPoints;
        this.expPoints = expPoints;
        //this.spellsList = spellsList;
        this.damage = damage;
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

    public void levelUp() {
        this.lvl = lvl + 1;
        this.healthPoints = (int)( healthPoints * 3.3 ) / 2;
        this.magicPoints = magicPoints + 5;
        this.damage = (int)(( damage * 3 ) / 1.8);
    }

    public String toString() {
        return getName() + " is lvl " + getLvl() + ". Their health points are: " + getHealthPoints() + ", their magic points are: " + getMagicPoints() + ", and their damage is: " + getDamage()+ ".";
    }
}
