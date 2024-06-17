import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.ls.LSOutput;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class HeroTests {
    public List<String> spellList = new ArrayList<>();

    //FREEZE SPELL
    @Test
    public void castFreezeSpellTest() throws InterruptedException {
        Hero h = new Hero("hero", 1, 50, 20, 0, spellList, 10, 0);
        h.levelUp(0);
        h.castFreezeSpell();
        Assert.assertEquals(h.getMagicPoints(), 20);
    }

    @Test
    public void castFreezeSpellTestLowMana() throws InterruptedException {
        Hero h = new Hero("hero", 1, 50, -2, 0, spellList, 10, 0);
        h.levelUp(0);
        h.castFreezeSpell();
        Assert.assertEquals(h.getMagicPoints(), 8);
    }

    @Test
    public void castFireSpellTest() throws InterruptedException {
        Hero h = new Hero("hero", 1, 50, 0, 0, spellList, 10, 0);
        h.levelUp(0);
        h.levelUp(0);
        h.castFireSpell();
        Assert.assertEquals(h.getMagicPoints(), 5);
    }

    @Test
    public void castFireSpellTestLowMana() throws InterruptedException {
        Hero h = new Hero("hero", 1, 50, -16, 0, spellList, 10, 0);
        h.levelUp(0);
        h.levelUp(0);
        h.castFireSpell();
        Assert.assertEquals(h.getMagicPoints(), 4);
    }

    @Test
    public void drinkMagicPotionTest() {
        
    }
}
