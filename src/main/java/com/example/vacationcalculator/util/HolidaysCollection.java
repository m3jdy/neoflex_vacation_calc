package com.example.vacationcalculator.util;

import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


/**
 * Этот класс представляет коллекцию праздничных дней для текущего года.
 * Он используется для проверки, является ли определенная дата праздничной.
 */
@Component
public class HolidaysCollection {
    private final Set<LocalDate> holidays;

    /**
     * Метод для проверки, является ли указанная дата праздничной.
     *
     * @param date Дата, которую необходимо проверить.
     * @return true, если дата является праздничной, false в противном случае.
     */
    public boolean isHoliday(LocalDate date){
        return holidays.contains(date);
    }
    /**
     * Конструктор класса HolidaysCollection.
     * Здесь инициализируется множество holidays и добавляются праздничные дни
     * для текущего года.
     */
    public HolidaysCollection (){
        holidays = new HashSet<>();
        holidays.add(LocalDate.of(LocalDate.now().getYear(),1,1));
        holidays.add(LocalDate.of(LocalDate.now().getYear(),1,2));
        holidays.add(LocalDate.of(LocalDate.now().getYear(),1,3));
        holidays.add(LocalDate.of(LocalDate.now().getYear(),1,4));
        holidays.add(LocalDate.of(LocalDate.now().getYear(),1,5));
        holidays.add(LocalDate.of(LocalDate.now().getYear(),1,6));
        holidays.add(LocalDate.of(LocalDate.now().getYear(),1,7));
        holidays.add(LocalDate.of(LocalDate.now().getYear(),1,8));
        holidays.add(LocalDate.of(LocalDate.now().getYear(),2,23));
        holidays.add(LocalDate.of(LocalDate.now().getYear(),3,8));
        holidays.add(LocalDate.of(LocalDate.now().getYear(),5,1));
        holidays.add(LocalDate.of(LocalDate.now().getYear(),5,9));
        holidays.add(LocalDate.of(LocalDate.now().getYear(),6,12));
        holidays.add(LocalDate.of(LocalDate.now().getYear(),11,4));
        holidays.add(LocalDate.of(LocalDate.now().getYear(),12,31));
    }
}
