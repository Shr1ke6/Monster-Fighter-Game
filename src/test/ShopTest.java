package test;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import monsterfighter.core.Item;
import monsterfighter.core.Monster;
import monsterfighter.core.Purchasable;


public class ShopTest {
    int day = 1;
    ArrayList<ArrayList<Purchasable>> shopInventory = new ArrayList<ArrayList<Purchasable>>();
    final List<Item> items = new ArrayList<>(1);
    Monster monster = new Monster(0, "Fireboy", Monster.Type.FIRE, 50, 20, 200);


    @Test
    public void testFillShop() {
        shopInventory.add(new ArrayList<Purchasable>());
        shopInventory.add(new ArrayList<Purchasable>());
        shopInventory.add(new ArrayList<Purchasable>());
        Assertions.assertEquals(3, shopInventory.size());

    }

    @Test
    public void TestShopIsEmpty() {
        boolean isEmpty = true;
        ArrayList<Purchasable> object = new ArrayList<Purchasable>();
        object.add(monster);
        shopInventory.add(new ArrayList<Purchasable>());
        if (!object.isEmpty()) {
                isEmpty = false;
        }
        Assertions.assertEquals(false, isEmpty);
    }

    @Test 
    public void TestRemoveObject() {
        shopInventory.add(new ArrayList<Purchasable>());
        shopInventory.add(new ArrayList<Purchasable>());
        shopInventory.remove(0);
        Assertions.assertEquals(1, shopInventory.size());

    }

}