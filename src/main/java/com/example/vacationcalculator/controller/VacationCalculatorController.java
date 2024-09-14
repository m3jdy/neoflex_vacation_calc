package com.example.vacationcalculator.controller;

import com.example.vacationcalculator.service.VacationCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Этот класс является REST-контроллером, обрабатывающим запросы для расчета отпускных выплат.
 */
@RestController
public class VacationCalculatorController {

    @Autowired
    private VacationCalculatorService vacationCalculatorService;
    /**
     * Метод-обработчик для HTTP GET-запросов на "/calculate".
     *
     * @param averageSalary средняя зарплата сотрудника
     * @param vacationDays  количество дней отпуска
     * @param startDate     (опционально) дата начала отпуска в формате "yyyy-MM-dd"
     * @param endDate       (опционально) дата окончания отпуска в формате "yyyy-MM-dd"
     * @return сумма отпускной выплаты
     */
    @GetMapping("/calculate")
    public double calculateVacationPay(
            @RequestParam double averageSalary,
            @RequestParam int vacationDays,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        // Если startDate и endDate не предоставлены, рассчитать базовую отпускную выплату
        if (startDate == null || endDate == null) {
            return vacationCalculatorService.calculateVacationPayBasic(averageSalary, vacationDays);
        } else {
            // Если startDate и endDate предоставлены, рассчитать отпускную выплату с учетом праздничных дней и выходных
            return vacationCalculatorService.calculateVacationPayAdvanced(averageSalary, vacationDays, startDate, endDate);
        }
    }
}
