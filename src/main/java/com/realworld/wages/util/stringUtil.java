package com.realworld.wages.util;

import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class stringUtil {

    static final String REGEXPATTERN = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    static final String ESCAPECHARS = "+-&&||!(){}[]^\"~*?:/";
    static final String LOCALTIMEZONE = "Asia/Kolkata";

    public static Date getIndiaTime(Date date) {
        if (date != null) {
            SimpleDateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            utcFormat.setTimeZone(TimeZone.getTimeZone("UTC")); //

            SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            isoFormat.setTimeZone(TimeZone.getTimeZone(LOCALTIMEZONE));
            // reducing as default app timezone is UTC per spring boot config - refer app
            try {
                // log.debug(localDate+"isoformat date"+isoFormat.format(date));
                String localDate = isoFormat.format(date); // utcFormat.format(date);
                //log.debug(localDate + isoFormat.parse(localDate).toString() + "isoformat date" + isoFormat.format(date));
                Date retDate = utcFormat.parse(isoFormat.format(date));
                return retDate;
            } catch (ParseException pe) {
                // TODO Auto-generated catch block
//                log.debug("Date error"+pe);
                // throw new CapaResponseException(jsonUtil.getMessage("err.capa.none"),pe);
            }
        }
        return null;
    }
}
