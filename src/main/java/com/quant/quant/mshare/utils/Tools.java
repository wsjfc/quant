package com.quant.quant.mshare.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools {
    public static boolean judgeNormalTime(String tm) {
        String day = tm.split(" ")[0];
        SimpleDateFormat form = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        boolean res = false;
        try {
            Date tradeTime =  form.parse(tm);
            Date normalOpenTime = form.parse(day + " " + "09:30:01");
            Date normalCloseTime = form.parse(day + " " + "16:00:01");
            if (tradeTime.before(normalCloseTime) && tradeTime.after(normalOpenTime)){
                res = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static String formatInterval(String interval){
        String formattedInterval;
        switch (interval){
            case "15_SEC":
                formattedInterval =  "15 secs";
                break;
            case "1_SEC":
                formattedInterval =  "1 secs";
                break;
            default:
                formattedInterval = "15 secs";
        }
        return formattedInterval;
    }
}