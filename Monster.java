public class Monster {

    private String name;
    private int healthPoints;
    private int damage;
    private int expGiven;

    private String color;

    private String itemDropped;

    public Monster(String name, int healthPoints, int damage, int expGiven, String color, String itemDropped) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.damage = damage;
        this.expGiven = expGiven;
        this.color = color;
        this.itemDropped = itemDropped;
    }

    public String getColor() {
        return color;
    }

    public int getDamage() {
        return damage;
    }

    public int getExpGiven() {
        return expGiven;
    }

    public String getItem() {
        return itemDropped;
    }

    public boolean hasItem() {
        if (itemDropped != "") {
            return true;
        }
        return false;
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

    public int receiveDamage(int damageTaken) throws InterruptedException {
        Utility.playSound("sword_clash.wav");
        Thread.sleep(700);
        return this.healthPoints -= damageTaken;
    }

    public void takeFreezeDamage() {
        this.damage = damage / 2;
    }

    public void takeFireDamage() throws InterruptedException {
        this.healthPoints = 0;
    }

}
