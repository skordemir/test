package com.luckycatlabs.sunrisesunset.api.demo.api;

import com.luckycatlabs.sunrisesunset.api.demo.gen.SunriseSunsetResultWrapper;
import com.luckycatlabs.sunrisesunset.api.demo.service.SonarSystemService;
import com.luckycatlabs.sunrisesunset.api.demo.model.SunPositionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;

@RestController
@RequestMapping("abc")
public class SonarController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SonarController.class);
    @Autowired
    private SonarSystemService sonarSystemService;

    @Autowired
    RestTemplate restTemplate;

    Map<String, Object> params;

    public SonarController(SonarSystemService sonarSystemService) {
        this.sonarSystemService = sonarSystemService;
    }

    public SonarController() {
    }

    @PostConstruct
    public void init() throws Exception {
    }

    @GetMapping("/test")
    public List<String> test() throws IOException, ParseException {
        final String url = "https://api.ipgeolocation.io/astronomy?apiKey=37571d8a1eb444e5a152e67b789b5c79lat={lat}&lng={lng}&date={date}";
        //final String url = "https://api.sunrise-sunset.org/json?lat={lat}&lng={lng}&date={date}";
        params = new LinkedHashMap<>();
        List<String> list = new ArrayList<>();
        double longitude;
        for (int j = 2020; j < 2022; j++) {
            for (int i = 1; i < 13; i++) {
                for (int k = 0; k < 3; k++) {
                    double randLat = Math.random() * 60;
                    double randLon = Math.random() * 120;
                    double randSignLat = Math.random() - 0.5;
                    double randSignLon = Math.random() - 0.5;
                    if (randSignLat < 0) {
                        randLat = randLat * (-1);
                    }
                    if (randSignLon < 0) {
                        randLon = randLon * (-1);
                    }
                    double randDay = Math.random() * 28;
                    int randDayInt = (int) (Math.ceil(randDay));

                    String monthStr = i + "";
                    if (i < 10) {
                        monthStr = "0" + monthStr;
                    }

                    String dayStr = randDayInt + "";
                    if (randDayInt < 10) {
                        dayStr = "0" + dayStr;
                    }

                    String dateStr = j + "-" + monthStr + "-" + dayStr;
                    String lati = randLat + "";
                    String longi = randLon + "";
                    params.put("lat", lati);
                    params.put("lng", longi);
                    params.put("date", dateStr);
                    if (j == 2023 && i == 12) {
                        System.out.println("safaf: " + lati + " " + longi + " " + dateStr);
                    }
                    SunriseSunsetResultWrapper result = restTemplate.getForObject(url, SunriseSunsetResultWrapper.class, params);

                    list.add(lati);
                    list.add(longi);
                    list.add(dateStr);

                    list.add(result.getResults().getSunrise());
                    list.add(result.getResults().getSunset());
                    list.add(result.getResults().getDay_length());
                    list.add(result.getResults().getCivil_twilight_begin());
                    list.add(result.getResults().getCivil_twilight_end());

                }
            }
        }

        FileWriter writer = new FileWriter("output.txt");
        for (String str : list) {
            writer.write(str + System.lineSeparator());
        }
        writer.close();

        return list;
    }

    @GetMapping("/getSunRiseTime")
    public Date getSunRiseTime(String date, double lat, double lon) throws ParseException {
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(date);
        System.out.println("controller-getsunrise offsetdatetime: " + offsetDateTime);
        SunPositionModel solarModelForDate = sonarSystemService.getSolarModelForDate(offsetDateTime.toInstant().toEpochMilli(), lat, lon);
        System.out.println("true rise:  " + solarModelForDate.getSunrise());

        return solarModelForDate.getSunrise();
    }

    @GetMapping("/getSunSetTime")
    public Date getSunSetTime(String date, double lat, double lon) throws ParseException {
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(date);
        System.out.println("controller-getsunset offsetdatetime: " + offsetDateTime);
        SunPositionModel solarModelForDate = sonarSystemService.getSolarModelForDate(offsetDateTime.toInstant().toEpochMilli(), lat, lon);
        System.out.println("true rise:  " + solarModelForDate.getSunset());

        return solarModelForDate.getSunset();
    }

    @GetMapping("/getDayLength")
    public Date getDayLength(String date, double lat, double lon) throws ParseException {
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(date);
        System.out.println("controller-getdaylength offsetdatetime: " + offsetDateTime);
        SunPositionModel solarModelForDate = sonarSystemService.getSolarModelForDate(offsetDateTime.toInstant().toEpochMilli(), lat, lon);
        System.out.println("true rise:  " + solarModelForDate.getDayTime());

        return solarModelForDate.getDayTime();
    }

    @GetMapping("/getMoonRiseTime")
    public Date getMoonRiseTime(String date, double lat, double lon) throws ParseException {
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(date);
        //ZonedDateTime zonedDateTime = ZonedDateTime.parse(date);
        //System.out.println("controller-getmoonrise zoneddatetime: " + zonedDateTime);
        System.out.println("controller-getmoonrise offsetdatetime: " + offsetDateTime);
        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date date1 = sdf.parse(date);
        long millis = date1.getTime();*/
        SunPositionModel solarModelForDate = sonarSystemService.getSolarModelForDate(offsetDateTime.toInstant().toEpochMilli(), lat, lon);
        System.out.println("true rise:  " + solarModelForDate.getMoonRise());

        return solarModelForDate.getMoonRise();
    }

    @GetMapping("/getMoonSetTime")
    public Date getMoonSetTime(String date, double lat, double lon) throws ParseException {
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(date);
        //ZonedDateTime zonedDateTime = ZonedDateTime.parse(date);
        //System.out.println("controller-getmoonrise zoneddatetime: " + zonedDateTime);
        System.out.println("controller-getmoonrise offsetdatetime: " + offsetDateTime);
        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date date1 = sdf.parse(date);
        long millis = date1.getTime();*/
        SunPositionModel solarModelForDate = sonarSystemService.getSolarModelForDate(offsetDateTime.toInstant().toEpochMilli(), lat, lon);
        System.out.println("true rise:  " + solarModelForDate.getMoonSet());

        return solarModelForDate.getMoonSet();
    }
}

 /*System.out.println("-------------------------");
        System.out.println("guncel tarih:   " + date);

        String str = TimezoneMapper.latLngToTimezoneString(lat, lon);
        System.out.println("syria timezone: " + str);*/

