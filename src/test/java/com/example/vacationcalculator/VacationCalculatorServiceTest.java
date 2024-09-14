package com.example.vacationcalculator;

import com.example.vacationcalculator.service.VacationCalculatorServiceImpl;
import com.example.vacationcalculator.util.HolidaysCollection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class VacationCalculatorServiceTest {

    @Autowired
    private VacationCalculatorServiceImpl vacationCalculatorService;

    @MockBean
    private HolidaysCollection holidaysCollection;


    @Test
    void testCalculateVacationPayBasic() {
        double averageSalary = 50000;
        int vacationDays = 14;
        double expected = 23890.78; // (50000/29.3) * 14

        double result = vacationCalculatorService.calculateVacationPayBasic(averageSalary,vacationDays);
        assertEquals(expected,result,0.01);
    }

    /**
    * Этот тест проверяет, что метод calculateVacationPayBasic корректно рассчитывает базовую отпускную выплату.
    *
    */
    @Test
    void testCalculateVacationPayAdvanced() {
        when(holidaysCollection.isHoliday(LocalDate.of(2024,3,8))).thenReturn(true);
        double averageSalary = 50000;
        int vacationDays = 14;
        String startDate = "2024-03-01";
        String endDate = "2024-03-14";
        double expected = 15358.36; // (50000 / 29.3) * 9 (excluding 5 holidays)
        double result = vacationCalculatorService.calculateVacationPayAdvanced(averageSalary, vacationDays, startDate, endDate);
        assertEquals(expected, result, 0.01);
    }
    /**
    * Этот тест проверяет, что метод calculateVacationPayAdvanced корректно рассчитывает отпускную выплату
    * с учетом праздничных дней в указанном диапазоне дат.
    */
    @Test
    void testCalculateVacationPayBasicWithInvalidInput() {
        //Некорректный ввод зарплаты (отрицательное значение)
        assertThrows(RuntimeException.class, () -> vacationCalculatorService.calculateVacationPayBasic(-1, 14));
        //Некорректный ввод количества отпускных дней (отрицательное значение)
        assertThrows(RuntimeException.class, () -> vacationCalculatorService.calculateVacationPayBasic(50000, -1));
        //Некорректный ввод  зарплаты и количества отпускных дней
        assertThrows(RuntimeException.class, () -> vacationCalculatorService.calculateVacationPayBasic(-1, -1));
    }

    @Test
    void testCalculateVacationPayAdvancedWithInvalidInput() {
        when(holidaysCollection.isHoliday(LocalDate.of(2024, 3, 8))).thenReturn(true);
        //Некорректный ввод зарплаты (отрицательное значение)
        assertThrows(RuntimeException.class, () -> vacationCalculatorService.calculateVacationPayAdvanced(-1, 14, "2024-03-01", "2024-03-14"));
        //Некорректный ввод количества отпускных дней (отрицательное значение)
        assertThrows(RuntimeException.class, () -> vacationCalculatorService.calculateVacationPayAdvanced(50000, -1, "2024-03-01", "2024-03-14"));
        //Некорректный ввод дат (дата начала позже даты окончания)
        assertThrows(RuntimeException.class, () -> vacationCalculatorService.calculateVacationPayAdvanced(50000, 14, "2024-03-15", "2024-03-14"));
        //Разница между датами начала и окончания не равна количеству отпускных дней
        assertThrows(RuntimeException.class, () -> vacationCalculatorService.calculateVacationPayAdvanced(50000, 14, "2024-03-01", "2024-03-20"));
    }
}
