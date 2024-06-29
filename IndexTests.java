import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class IndexTests {

    public List<String> spellList = new ArrayList<>();

    Hero h = new Hero("hero", 1, 50, 20, 0, spellList, 10, 0);

    @Test
    public void attackMonster() throws InterruptedException {
        Monster m = new Monster("monster", 50, 10, 50, "", "");
        Index.attackMonster(h, m);
        Assert.assertEquals(h.getHealthPoints(), 40);
        Assert.assertEquals(m.getHealthPoints(), 40);
    }

    @Test
    public void attackMonster_defeat_monster() throws InterruptedException {
        Monster m = new Monster("monster", 10, 10, 50, "", "healthPotion");
        Index.attackMonster(h, m);
        Assert.assertEquals(m.getHealthPoints(), 0);
        Assert.assertEquals(h.getExpPoints(), 50);
        Assert.assertEquals(h.getInventory().size(), 1);
    }

    @Test
    public void castFireSpell() throws InterruptedException {
        Monster m = new Monster("monster", 50, 50, 50, "", "healthPotion");
        h.levelUp(0);
        h.levelUp(0);
        Index.castFireSpell(h, m);
        Assert.assertEquals("Fire spell should destroy enemy: ", m.getHealthPoints(), 0);
        Assert.assertEquals("Hero gains experience points: ", h.getExpPoints(), 50);
        Assert.assertEquals("Monster should drop a healthPotion: ", h.getInventory().size(), 1);
    }

    @Test
    public void castFreezeSpell() throws InterruptedException {
        Monster m = new Monster("monster", 50, 50, 50, "", "");
        h.levelUp(0);
        Index.castFreezeSpell(h, m);
        Assert.assertEquals("Freeze spell should cut enemy damage in half: ", m.getDamage(), 25);
        Assert.assertEquals("Enemy doesn't attack this round: ", h.getHealthPoints(), 125);
    }
}
