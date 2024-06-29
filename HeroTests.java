import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.ls.LSOutput;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HeroTests {
    public List<String> spellList = new ArrayList<>();

    @Test
    public void castFreezeSpell() throws InterruptedException {
        Hero h = new Hero("hero", 1, 50, 20, 0, spellList, 10, 0);
        // level up adds 10mp and gives access to freeze spell
        h.levelUp(0);
        h.castFreezeSpell();
        Assert.assertEquals("Freeze spell should cast, -10mp result: ", h.getMagicPoints(), 20);
    }

    @Test
    public void castFreezeSpell_low_mp() throws InterruptedException {
        Hero h = new Hero("hero", 1, 50, -2, 0, spellList, 10, 0);
        // level up adds 10mp and gives access to freeze spell
        h.levelUp(0);
        h.castFreezeSpell();
        Assert.assertEquals("Freeze spell should fail, not enough mp: ", h.getMagicPoints(), 8);
    }

    @Test
    public void castFireSpell() throws InterruptedException {
        Hero h = new Hero("hero", 1, 50, 0, 0, spellList, 10, 0);
        // level up adds 10mp and gives access to freeze spell
        h.levelUp(0);
        // level up adds 10mp and gives access to fire spell
        h.levelUp(0);
        h.castFireSpell();
        Assert.assertEquals("Fire spell should cast, -15mp result: ", h.getMagicPoints(), 5);
    }

    @Test
    public void castFireSpell_low_mana() throws InterruptedException {
        Hero h = new Hero("hero", 1, 50, -20, 0, spellList, 10, 0);
        // level up adds 10mp and gives access to freeze spell
        h.levelUp(0);
        // level up adds 10mp and gives access to fire spell
        h.levelUp(0);
        h.castFireSpell();
        Assert.assertEquals("Fire spell should fail, not enough mp: ", h.getMagicPoints(), 0);
    }

    @Test
    public void drinkMagicPotion() throws InterruptedException {
        Hero h = new Hero("hero", 1, 50, 20, 0, spellList, 10, 0);
        h.receiveItem("magicPotion", 1);
        h.drinkMagicPotion();
        Assert.assertEquals("Drink magic potion should restore 10mp: ", h.getMagicPoints(), 30);
        Assert.assertEquals("Drink magic potion should reduce inventory by 1: ", h.getInventory().size(), 0);
    }

    @Test
    public void drinkMagicPotion_no_quantity() throws InterruptedException {
        Hero h = new Hero("hero", 1, 50, 20, 0, spellList, 10, 0);
        h.drinkMagicPotion();
        Assert.assertEquals("Should fail; not enough magic potions in inventory: ", h.getMagicPoints(), 20);
    }

    @Test
    public void drinkHealthPotion() throws InterruptedException {
        Hero h = new Hero("hero", 1, 50, 20, 0, spellList, 10, 0);
        h.receiveItem("healthPotion", 1);
        h.drinkHealthPotion();
        Assert.assertEquals("Drink health potion should restore 30hp: ", h.getHealthPoints(), 80);
        Assert.assertEquals("Drink health potion should reduce inventory by 1: ", h.getInventory().size(), 0);
    }

    @Test
    public void drinkHealthPotion_no_quantity() throws InterruptedException {
        Hero h = new Hero("hero", 1, 50, 20, 0, spellList, 10, 0);
        h.drinkHealthPotion();
        Assert.assertEquals("Should fail; not enough health potions in inventory: ", h.getHealthPoints(), 50);
    }

    @Test
    public void receiveExperience() {
        Hero h = new Hero("hero", 1, 50, 20, 0, spellList, 10, 0);
        h.receiveExp(50);
        Assert.assertEquals(50, h.getExpPoints());
    }
}
