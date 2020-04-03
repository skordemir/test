package com.luckycatlabs.sunrisesunset.api.demo.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SunPositionModel {

    private Date sunrise;

    private Date sunset;

    private Date sunMiddle;

    private double moonVisualPercentage;

    private Date dayTime;

    private Date moonRise;

    private Date moonSet;


    public Date getSunrise() {
        return sunrise;
    }

    public void setSunrise(Date sunrise) {
        this.sunrise = sunrise;
    }

    public Date getSunset() {
        return sunset;
    }

    public void setSunset(Date sunset) {
        this.sunset = sunset;
    }

    public Date getSunMiddle() {
        return sunMiddle;
    }

    public void setSunMiddle(Date sunMiddle) {
        this.sunMiddle = sunMiddle;
    }

    public double getMoonVisualPercentage() {
        return moonVisualPercentage;
    }

    public void setMoonVisualPercentage(double moonVisualPercentage) {
        this.moonVisualPercentage = moonVisualPercentage;
    }

    public Date getMoonRise() {
        return moonRise;
    }

    public void setMoonRise(Date moonRise) {
        this.moonRise = moonRise;
    }

    public Date getMoonSet() {
        return moonSet;
    }

    public void setMoonSet(Date moonSet) {
        this.moonSet = moonSet;
    }

    public Date getDayTime() {
        return dayTime;
    }

    public void setDayTime(Date dayTime) throws ParseException {
       /* String s = String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(dayTime),
                TimeUnit.MILLISECONDS.toMinutes(dayTime) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(dayTime)), // The change is in this line
                TimeUnit.MILLISECONDS.toSeconds(dayTime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(dayTime)));
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");*/
        this.dayTime = dayTime;
        //this.dayTime = dayTime;
    }
}
