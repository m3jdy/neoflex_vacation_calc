package com.example.vacationcalculator.service;

import com.example.vacationcalculator.util.HolidaysCollection;
import com.example.vacationcalculator.util.VacationCalculatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


/**
 * Этот класс реализует логику расчета отпускных выплат.
 */
@Service
@Component
public class VacationCalculatorServiceImpl implements VacationCalculatorService {

    private final HolidaysCollection holidays;

    @Autowired
    public VacationCalculatorServiceImpl(HolidaysCollection holidays) {
        this.holidays = holidays;
    }
    /**
     * Метод для расчета базовой отпускной выплаты без учета праздничных дней.
     * @return сумма отпускной выплаты
     * @throws RuntimeException если введены некорректные данные (отрицательная зарплата или количество дней)
     */
    @Override
    public double calculateVacationPayBasic(double averageSalary, int vacationDays) {
        if (averageSalary < 1 || vacationDays < 1) {
            throw new RuntimeException("Incorrect input data");
        } else {
            return VacationCalculatorUtils.calculateVacationPayBasic(averageSalary, vacationDays);
        }
    }
    /**
     * Метод для расчета отпускной выплаты с учетом праздничных и выходных дней.
     * @return сумма отпускной выплаты
     * @throws RuntimeException если введены некорректные данные (отрицательная зарплата или количество дней,
     *                          дата начала позже даты окончания, количество дней не соответствует диапазону дат)
     */
    @Override
    public double calculateVacationPayAdvanced(double averageSalary, int vacationDays, String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
        LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);
        long countDays = ChronoUnit.DAYS.between(start,end)+1;

        if (averageSalary < 1 || vacationDays < 1 || start.isAfter(end) || countDays != vacationDays) {
            throw new RuntimeException("Incorrect input data!");
        } else {
            while (!start.isAfter(end)){
                if (start.getDayOfWeek().getValue() >= 6 || holidays.isHoliday(start)){
                    vacationDays--;
                }
                start = start.plusDays(1);
            }
            return VacationCalculatorUtils.calculateVacationPayBasic(averageSalary,vacationDays);
        }
    }
}
