package tests;

import org.junit.BeforeClass;
import org.junit.Test;

import java.ItemType;
import java.ShoppingCart;

/**
 * Class for testing class "ShoppingCart"
 */
public class ShoppingCartTest {
    static ShoppingCart shoppingCart;
    static ShoppingCart shoppingCartForOverSizeTest;

    public ShoppingCartTest()
    {
    }

    @BeforeClass
    public static void setUpClass()
    {
        shoppingCart = new ShoppingCart();
    }

    @Test(expected=IllegalArgumentException.class)
    public void addItemWithZeroTitleLenght()
    {
        shoppingCart.addItem("", 10, 0, ItemType.NEW);
    }

    @Test
    public void addItemTest1()
    {
        shoppingCart.addItem("t", 10, 10, ItemType.NEW);
        shoppingCart.addItem("title", 10, 10, ItemType.NEW);
        shoppingCart.addItem("titleWith32SymbolsLong_123456789", 10, 10, ItemType.NEW);
        shoppingCart.addItem("title", 1, 10, ItemType.NEW);
        shoppingCart.addItem("title", 1145, 10, ItemType.NEW);
        shoppingCart.addItem("title", 999, 10, ItemType.NEW);
        shoppingCart.addItem("title", 10, 10, ItemType.REGULAR);
        shoppingCart.addItem("title", 10, 10, ItemType.SECOND_FREE);
        shoppingCart.addItem("title", 10, 10, ItemType.SALE);
    }

    @Test(expected=IllegalArgumentException.class)
    public void addItemWithOversizedTitle()
    {
        shoppingCart.addItem("titleWith6989898SymbolsLong_1234567890", 10, 10, ItemType.NEW);
    }

    @Test(expected=IllegalArgumentException.class)
    public void addItemWithZeroPrice()
    {
        shoppingCart.addItem("title", 0, 10, ItemType.NEW);
    }

    @Test(expected=IllegalArgumentException.class)
    public void addItemWithOverprice()
    {
        shoppingCart.addItem("title", 1000, 10, ItemType.NEW);
    }

    @Test(expected=IllegalArgumentException.class)
    public void addItemWithZeroQuantity()
    {
        shoppingCart.addItem("title", 10, 0, ItemType.NEW);
    }

    @Test(expected=IllegalArgumentException.class)
    public void addItemWithOvequantity()
    {
        shoppingCart.addItem("title", 10, 1001, ItemType.REGULAR);
    }

    @Test(expected=IllegalArgumentException.class)
    public void addItemWithUnknownType()
    {
        shoppingCart.addItem("te", 10, 10, null);
    }

    @Test
    public void capacityTest()
    {
        shoppingCartForOverSizeTest = new ShoppingCart();
        for(int i = 0; i < 99; i++)
        {
            shoppingCartForOverSizeTest.addItem("title", 10, 10, ItemType.REGULAR);
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void capacityOversizeTest()
    {
        capacityTest();
        shoppingCartForOverSizeTest.addItem("title", 10, 10, ItemType.REGULAR);
    }

}
