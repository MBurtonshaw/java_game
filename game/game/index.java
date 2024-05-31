import java.util.Scanner;

class Index {
    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);
        System.out.println("What is your name?");
        String name = userInput.nextLine();

        Hero hero1 = new Hero(name, 1, 50, 20, 0, 10, 0);

        System.out.println(".............................................\n.......................................\n............................................");
        System.out.println(hero1.toString());
        
        System.out.println(".............................................\n.......................................\n............................................");
        Monster monster1 = new Monster("Barry", 20, 5, 50);
        System.out.println(monster1.appear());
        //conditional user input... attack, use spell, run
        getAction(hero1, monster1);
        //conditional user input... attack, use spell, run
        //conditional checking monster's health and user's health
        System.out.println(".............................................\n.......................................\n............................................");
        
        System.out.println(hero1.toString());
        
        System.out.println(".............................................\n.......................................\n............................................");
        System.out.println(".............................................\n.......................................\n............................................");
        Monster monster2 = new Monster("Larry", 30, 10, 50);
        System.out.println(monster2.appear());
        getAction(hero1, monster2);
        System.out.println(".............................................\n.......................................\n............................................");
        
        System.out.println(hero1.toString());

        System.out.println(".............................................\n.......................................\n............................................");
        System.out.println(".............................................\n.......................................\n............................................");
        Monster monster3 = new Monster("Terry", 45, 12, 35);
        System.out.println(monster3.appear());
        getAction(hero1, monster3);
        System.out.println(".............................................\n.......................................\n............................................");
    
        System.out.println(".............................................\n.......................................\n............................................");
        System.out.println(".............................................\n.......................................\n............................................");
        Monster monster4 = new Monster("Gary", 55, 17, 45);
        System.out.println(monster4.appear());
        getAction(hero1, monster4);
        System.out.println(".............................................\n.......................................\n............................................");
    
        System.out.println(".............................................\n.......................................\n............................................");
        System.out.println(".............................................\n.......................................\n............................................");
        Monster monster5 = new Monster("Mary", 65, 20, 55);
        System.out.println(monster5.appear());
        getAction(hero1, monster5);
        System.out.println(".............................................\n.......................................\n............................................");
    }

    static int getAction(Hero hero, Monster monster) {
        Scanner userInput = new Scanner(System.in);

        do {
            System.out.println("1 - Attack  ||  2 - Run");
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
                    hero.receiveExp(monster.getExpGiven());
                    System.out.println(hero.getName() + " has gained " + monster.getExpGiven() + " exp points!");
                    System.out.println(".............................................\n.......................................\n............................................");
                    hero.defeatEnemy();
                }
            } else if (decision == 2) {
                System.out.println(".............................................\n.......................................\n............................................");
                System.out.println(hero.getName() + " has run away from " + monster.getName() + "!");
                System.out.println(".............................................\n.......................................\n............................................");
                break;
            }
        } while (hero.getHealthPoints() > 0 && monster.getHealthPoints() > 0); 

        if (hero.getHealthPoints() <= 0) {
            System.out.println(hero.getName() + " has been defeated!!");
            System.out.println("Number of enemies defeated: " + (hero.getEnemiesDefeated() + 1));
        }
        
        return 0;
    }

}

