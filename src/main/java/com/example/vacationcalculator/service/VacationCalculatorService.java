package com.example.vacationcalculator.service;

/**
 * Этот интерфейс определяет методы для расчета отпускных выплат.
 */
public interface VacationCalculatorService {
    double calculateVacationPayBasic(double averageSalary, int vacationDays);
    double calculateVacationPayAdvanced (double averageSalary, int vacationDays, String startDate, String endDate);
}
