package Discounts;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

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
    @CsvSource({
            "5, VW, 2022-06-16, 5", //idade 5 -> desconto 2; VW -> desconto 3; 2022-06-16(quinta-feira) -> desconto = 2+3 = 5 (MAX = nao atingido)
            "50, VW, 2022-06-16, 25", //idade 50 -> desconto 26; VW -> desconto 3; 2022-06-16(quinta-feira) -> desconto = 26+3 = 29 (MAX = 25)
            "50, VW, 2022-06-17, 25", //idade 50 -> desconto 26; VW -> desconto 3; 2022-06-17(sexta-feira) -> desconto = 26+3+5 = 34 (MAX = 25)
            "5, VW, 2022-06-17, 10" //idade 5 -> desconto 2; VW -> desconto 3; 2022-06-17(sexta-feira) -> desconto = 2+3+5 = 10 (MAX = nao atingido)
    })

    void calculateTotalDiscount(int age, String brand, String todayString, int expected) {
        int actualYear = LocalDate.now().getYear(); //ano atual
        int carYear = actualYear - age; //idade carro
        LocalDate today = LocalDate.parse(todayString);

        //Chamar funcao
        int discount = Discounts.calculateTotalDiscount(carYear, brand, today); //obter desconto
        assertEquals(expected, discount);
    }
}