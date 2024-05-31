public class Monster {

    private String name;
    private int healthPoints;
    private int damage;
    private int expGiven;

    public Monster(String name, int healthPoints, int damage, int expGiven) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.damage = damage;
        this.expGiven = expGiven;
    }

    public int getDamage() {
        return damage;
    }

    public int getExpGiven() {
        return expGiven;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public String getName() {
        return name;
    }

    public String appear() {
        return "Your opponent, " + getName() + ", has appeared!";
    }

    public int receiveDamage(int damageTaken) {
        return this.healthPoints -= damageTaken;
    }

    public int checkIfDead() {
        if (this.healthPoints <= 0) {
            return 1;
        }
        return 0;
    }

}
