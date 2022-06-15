package Discounts;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DiscountsTest {

    @Test
    public void testDiscountByAge_GreaterThanOrEqual5() {
        assertEquals(Discounts.discountByAge(2017), 2); //5anos
    }

    @Test
    public void testDiscountByAge_GreaterThanOrEqual5AndLessThan10() {
        assertEquals(Discounts.discountByAge(2016), 3); //6 anos
    }

    @Test
    public void testDiscountByAge_GreaterThan10() {
        System.out.println(11/2);
        assertEquals(Discounts.discountByAge(2011), 6); //11 anos
    }

    @Test
    public void testDiscountByBrand() {

    }

    @Test
    public void testCalculateTotalDiscount() {

    }
}