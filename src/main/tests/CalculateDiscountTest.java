package tests;
import java.Item;
import java.ItemType;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Class for testing method "CalculateDiscount"
 */
@RunWith(Parameterized.class)
public class CalculateDiscountTest
{
    private Item testItem;
    private int expectedResult;

    public CalculateDiscountTest(Item item, Integer discount)
    {
        this.testItem = item;
        this.expectedResult = discount;
    }

    @Test
    public void calculateDiscountTest()
    {
        assertEquals(expectedResult, ItemType.calculateDiscount(testItem.getItemType(),testItem.getQuantity()));
    }

    @Parameters
    public static Collection testData()
    {
        return Arrays.asList(new Object[][]
                {
                        { new Item("item", ItemType.REGULAR, 10, 1), 0 },
                        { new Item("item", ItemType.SECOND_FREE, 10, 1), 0 },
                        { new Item("item", ItemType.SECOND_FREE, 10, 2), 50 },
                        { new Item("item", ItemType.NEW, 10, 1), 10 },
                        { new Item("item", ItemType.NEW, 10, 10), 20 },
                        { new Item("item", ItemType.NEW, 10, 20), 30 },
                        { new Item("item", ItemType.NEW, 10, 30), 40 },
                        { new Item("item", ItemType.NEW, 10, 40), 50 },
                        { new Item("item", ItemType.NEW, 10, 50), 50 },
                        { new Item("item", ItemType.SALE, 10, 1), 90 }, //80
                        { new Item("item", ItemType.REGULAR, 10, 100), 10 },
                        { new Item("item", ItemType.REGULAR, 10, 200), 20 },
                        { new Item("item", ItemType.REGULAR, 10, 300), 30 },
                        { new Item("item", ItemType.REGULAR, 10, 400), 40 },
                        { new Item("item", ItemType.REGULAR, 10, 600), 50 },
                        { new Item("item", ItemType.REGULAR, 10, 600), 60 },
                        { new Item("item", ItemType.REGULAR, 10, 700), 70 },
                        { new Item("item", ItemType.REGULAR, 10, 800), 80 },
                        { new Item("item", ItemType.REGULAR, 10, 900), 80 },
                        { new Item("item", ItemType.SECOND_FREE, 10, 100), 60 },
                        { new Item("item", ItemType.SECOND_FREE, 10, 200), 70 },
                        { new Item("item", ItemType.SECOND_FREE, 10, 300), 80 },
                        { new Item("item", ItemType.SECOND_FREE, 10, 400), 80 },
                        { new Item("item", ItemType.SALE, 10, 100), 60 },
                        { new Item("item", ItemType.NEW, 10, 200), 70 },
                        { new Item("item", ItemType.NEW, 10, 300), 80 },
                        { new Item("item", ItemType.NEW, 10, 400), 80 },
                });
    }
}
