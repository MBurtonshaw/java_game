import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.ls.LSOutput;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HeroTests {
    public List<String> spellList = new ArrayList<>();

    //FREEZE SPELL
    @Test
    public void castFreezeSpell() throws InterruptedException {
        Hero h = new Hero("hero", 1, 50, 20, 0, spellList, 10, 0);
        h.levelUp(0);
        h.castFreezeSpell();
        Assert.assertEquals(h.getMagicPoints(), 20);
    }

    @Test
    public void castFreezeSpell_low_mp() throws InterruptedException {
        Hero h = new Hero("hero", 1, 50, -2, 0, spellList, 10, 0);
        h.levelUp(0);
        h.castFreezeSpell();
        Assert.assertEquals(h.getMagicPoints(), 8);
    }

    @Test
    public void castFireSpell() throws InterruptedException {
        Hero h = new Hero("hero", 1, 50, 0, 0, spellList, 10, 0);
        h.levelUp(0);
        h.levelUp(0);
        h.castFireSpell();
        Assert.assertEquals(h.getMagicPoints(), 5);
    }

    @Test
    public void castFireSpell_low_mana() throws InterruptedException {
        Hero h = new Hero("hero", 1, 50, -20, 0, spellList, 10, 0);
        h.levelUp(0);
        h.levelUp(0);
        h.castFireSpell();
        Assert.assertEquals(h.getMagicPoints(), 0);
    }

    @Test
    public void drinkMagicPotion() throws InterruptedException {
        Hero h = new Hero("hero", 1, 50, 20, 0, spellList, 10, 0);
        h.receiveItem("magicPotion", 1);
        h.drinkMagicPotion();
        Assert.assertEquals(h.getMagicPoints(), 30);
        Assert.assertEquals(h.getInventory().size(), 0);
    }

    @Test
    public void drinkMagicPotion_no_quantity() throws InterruptedException {
        Hero h = new Hero("hero", 1, 50, 20, 0, spellList, 10, 0);
        h.drinkMagicPotion();
        Assert.assertEquals(h.getMagicPoints(), 20);
    }

    @Test
    public void drinkHealthPotion() throws InterruptedException {
        Hero h = new Hero("hero", 1, 50, 20, 0, spellList, 10, 0);
        h.receiveItem("healthPotion", 1);
        h.drinkHealthPotion();
        Assert.assertEquals(h.getHealthPoints(), 80);
        Assert.assertEquals(h.getInventory().size(), 0);
    }

    @Test
    public void drinkHealthPotion_no_quantity() throws InterruptedException {
        Hero h = new Hero("hero", 1, 50, 20, 0, spellList, 10, 0);
        h.drinkHealthPotion();
        Assert.assertEquals(h.getHealthPoints(), 50);
    }
}
