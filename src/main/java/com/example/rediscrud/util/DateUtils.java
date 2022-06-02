package com.example.rediscrud.util;

import java.util.Calendar;
import java.util.Date;

public final class DateUtils {

    public static Boolean validateProductExpire(Date currentdate, Date productGenerateDate, int hours) {
        Calendar productGenCalendarDate = Calendar.getInstance();
        productGenCalendarDate.setTime(productGenerateDate);
        productGenCalendarDate.add(Calendar.HOUR_OF_DAY, hours);
        if (productGenCalendarDate.getTime().before(currentdate)){
            return true;
        }else {
            return false;
        }
    }
}
