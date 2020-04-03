package com.luckycatlabs.sunrisesunset.api.demo.service;

import com.luckycatlabs.sunrisesunset.api.demo.TimezoneMapper;
import com.luckycatlabs.sunrisesunset.api.demo.moonCalculator.MoonFx;
import com.luckycatlabs.sunrisesunset.api.demo.sunCalculator.Location;
import com.luckycatlabs.sunrisesunset.api.demo.sunCalculator.SunriseSunsetCalculator;
import com.luckycatlabs.sunrisesunset.api.demo.model.SunPositionModel;
import com.luckycatlabs.sunrisesunset.api.demo.moonRiseSet.MoonTimes;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

@Service
public class SonarSystemService {
    public SunPositionModel getSolarModelForDate(long utcTimeMillis, double latitude, double longitude) throws ParseException {
        TimeZone timeZone = TimeZone.getTimeZone(ZoneOffset.UTC);
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.setTimeInMillis(utcTimeMillis);
        Location location = new Location(latitude, longitude);
        SunriseSunsetCalculator calculator = new SunriseSunsetCalculator(location, timeZone);
        /////
        long officialSunrise = calculator.getOfficialSunriseCalendarForDate(calendar).getTimeInMillis();
        long officialSunset = calculator.getOfficialSunsetCalendarForDate(calendar).getTimeInMillis();

        /////
        //System.out.println("s1 rise : " + s1 + " s2 set: " + s2 );

        MoonFx mnf = new MoonFx();
        //LocalDateTime testDate = null;
        Instant instant = Instant.ofEpochMilli(utcTimeMillis);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
        mnf.setDate(localDateTime);
        long moonVisualPercentage = Math.round(mnf.getIlluminatedRatio(mnf.getSynodicPhase()) * 100);

        /////
        //MOON RISE-SET TIMES
        /*Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2020, Calendar.MARCH, 14);*/
        //Date date = calendar.getTime();

        Instant instant1 = Instant.ofEpochMilli(utcTimeMillis);
        LocalDateTime localDateTime1 = LocalDateTime.ofInstant(instant1, ZoneOffset.UTC);
        Date date = Date.from( localDateTime1.atZone( ZoneId.systemDefault()).toInstant());
        MoonTimes moonTimes = MoonTimes.compute().on(date).at(latitude, longitude).execute();
       /* MoonTimes m1 = MoonTimes.compute().on(calendar.getTime()).at(35.9310, 36.6418).execute();
        System.out.println("m1:  " + m1.getRise());*/
        //////
        SunPositionModel model = new SunPositionModel();
        model.setSunrise(Date.from(Instant.ofEpochMilli(officialSunrise)));
        //System.out.println("set ten sonra sun rise: " + model.getSunrise());
        model.setSunset(Date.from(Instant.ofEpochMilli(officialSunset)));
        model.setDayTime(Date.from(Instant.ofEpochMilli(officialSunset-officialSunrise-7200000)));
        model.setMoonVisualPercentage(moonVisualPercentage);
        //System.out.println("getrise: " + moonTimes.getRise());
        model.setMoonRise(moonTimes.getRise());
        //System.out.println("getset: " + moonTimes.getSet());
        model.setMoonSet(moonTimes.getSet());
        ////////////
        return model;
    }

     /*String s1 = String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(officialSunrise),
                TimeUnit.MILLISECONDS.toMinutes(officialSunrise) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(officialSunrise)), // The change is in this line
                TimeUnit.MILLISECONDS.toSeconds(officialSunrise) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(officialSunrise)));
        String s2 = String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(officialSunset),
                TimeUnit.MILLISECONDS.toMinutes(officialSunset) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(officialSunset)), // The change is in this line
                TimeUnit.MILLISECONDS.toSeconds(officialSunset) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(officialSunset)));
        DateFormat simple = new SimpleDateFormat("HH:mm:ss");
        Date s2date = simple.parse(s2);
        Date s1date = simple.parse(s1);
        long dif = s2date.getTime() - s1date.getTime();*/
   /* // TODO : rest service olarak acilmali
    public SunPositionModel getSolarModelForDate(Date date, Location location, String timeZone) throws ParseException {

        SunriseSunsetCalculator calculator = new SunriseSunsetCalculator(location, timeZone);

        // TODO : fonksiyondan donen bigdecimal degeri direk time millis gibi biseyse new Date yapip burada don
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String officialSunrise = calculator.getOfficialSunriseForDate(calendar);
        String officialSunset = calculator.getOfficialSunsetForDate(calendar);
        Date d1 = new Date(officialSunrise);
        Date d2 = new Date(officialSunset);
        // TODO : moon fonksiyonun localdate timesiz calistir bunu yapamazsan eger Date i localDate e cevir moon a parametre gonder


        MoonFx mnf = new MoonFx();
        LocalDateTime testDate = null;
        mnf.setDate(testDate);
        long round = Math.round(mnf.getIlluminatedRatio(mnf.getSynodicPhase()) * 100);

        SunPositionModel model = new SunPositionModel();

        model.setSunrise(d1);
        model.setSunset(d2);
        model.setDayTime(calculator.getDayLength(officialSunset, officialSunrise));
        model.setMoonVisualPercentage(round);

        // TODO : ay dogus , ay batis

        return model;

    }*/
}
