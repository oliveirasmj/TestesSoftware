package Discounts;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.testng.Assert.assertEquals;

class DiscountsTest {
    final int x = 2+10-3;

    @ParameterizedTest
    @CsvSource({"-5,0", "0,0", "4,0", "5,2", "6,5", "8,7", "9,8", "10,2", "11,6"})
    void discountByAge(int age, int expected) {
        int actualYear = LocalDate.now().getYear(); //ano atual
        int yearCar = actualYear - age; //idade carro
        int discount = Discounts.discountByAge(yearCar); //obter desconto
        assertEquals(expected, discount);
    }

    @ParameterizedTest
    @CsvSource({"VW,3", "Tesla,5", "BMW,7", "Mercedes,0"})
    void discountByBrand(String brand, int expected) {
        int discount = Discounts.discountByBrand(brand); //obter desconto
        assertEquals(expected, discount);
    }

    @ParameterizedTest
    @CsvSource({"VW,3", "Tesla,5", "BMW,7", "Mercedes,0"})
    void calculateTotalDiscount() {

    }
}