package com.example.vacationcalculator.util;

/**
 * Этот класс содержит вспомогательные методы для расчета отпускных выплат.
 */
public class VacationCalculatorUtils {
    public static double calculateVacationPayBasic(double averageSalary, double vacationDays){
        return (averageSalary / 29.3) * vacationDays;
    }
}
