import java.util.Scanner;

class Index {
    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);
        System.out.println("What is your name?");
        String name = userInput.nextLine();

        Hero hero1 = new Hero(name, 1, 50, 20, 0, 10);
        
        System.out.println(".............................................\n.......................................\n............................................");
        System.out.println(hero1.toString());
        
        System.out.println(".............................................\n.......................................\n............................................");
        Monster monster1 = new Monster("Barry", 20, 10, 25);
        System.out.println(monster1.appear());
        //conditional user input... attack, use spell, run
        getAction(hero1, monster1);
        //conditional user input... attack, use spell, run
        //conditional checking monster's health and user's health
        System.out.println(".............................................\n.......................................\n............................................");
        
        System.out.println(hero1.toString());




       

    }
    static int getAction(Hero hero, Monster monster) {
        Scanner userInput = new Scanner(System.in);

        do {
            System.out.println("1 - Attack   2 - Run");
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
                    hero.expReceived(monster.getExpGiven());
                    System.out.println(hero.getName() + " has gained " + monster.getExpGiven() + " exp points!");
                    System.out.println(".............................................\n.......................................\n............................................");
                    if ( hero.getExpPoints() > 100 ) {
                        hero.levelUp();
                        hero.resetExp();
                        System.out.println(hero.getName() + " has leveled up!");
                        System.out.println(".............................................\n.......................................\n............................................");
                        hero.toString();
                        System.out.println(".............................................\n.......................................\n............................................");
                    }
                }
            } else if (decision == 2) {
                System.out.println(".............................................\n.......................................\n............................................");
                System.out.println(hero.getName() + " has run away from " + monster.getName() + "!");
                System.out.println(".............................................\n.......................................\n............................................");
                break;
            }
        } while (hero.getHealthPoints() > 0 && monster.getHealthPoints() > 0); 
        
        return 0;
    }

}

