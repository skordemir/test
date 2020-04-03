package com.luckycatlabs.sunrisesunset.api.demo.api;

import com.luckycatlabs.sunrisesunset.api.demo.model.SunPositionModel;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static java.lang.Double.parseDouble;

@SpringBootTest
class SonarControllerTest {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    SonarController sonarController;

    @Test
    void init() {
    }

    @Test
    void getSunRiseTime() throws ParseException, IOException, JSONException {
        String listDate, trueSunrise, trueSunset, dayLength, trueMoonrise, trueMoonset;
        double latitude, longitude;
        Scanner scanner = new Scanner(new File("output.txt"));

        int whileCount = 0;
        int trueCounter = 0, falseCounter = 0;
        while (scanner.hasNextLine()) {
            whileCount++;
            latitude = parseDouble(scanner.nextLine());
            System.out.println(latitude);
            longitude = parseDouble(scanner.nextLine());
            System.out.println(longitude);
            listDate = scanner.nextLine();
            System.out.println(listDate);
            trueSunrise = scanner.nextLine();
            System.out.println(trueSunrise);
            trueSunset = scanner.nextLine();
            System.out.println(trueSunset);

            dayLength = scanner.nextLine();
            System.out.println(dayLength);

            trueMoonrise = scanner.nextLine();
            System.out.println(trueMoonrise);

            trueMoonset = scanner.nextLine();
            System.out.println(trueMoonset);

            listDate += "T14:00:00Z";
            Date date = sonarController.getSunRiseTime(listDate, latitude, longitude);
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss a");
            String dateString = dateFormat.format(date);
            System.out.println("Current time in AM/PM: " + dateString);

            // TODO Burayi kontrol et! dogru parse edip saat farkinin 2 dk dan fazla olmamsi lazim !!!
            /*if (trueSunrise.contains("A")) {
                trueSunrise = trueSunrise.substring(0, trueSunrise.length() - 3);*/
            int trueHour = Integer.parseInt(trueSunrise.substring(0, trueSunrise.indexOf(":")));
            int myHour = Integer.parseInt(dateString.substring(0, 2));
            if ((Math.abs(myHour - trueHour) == 3) || (Math.abs(myHour - trueHour) == 9) || (Math.abs(myHour - trueHour) == 15)) {
                int trueMin = Integer.parseInt(trueSunrise.substring(trueSunrise.indexOf(":") + 1, trueSunrise.lastIndexOf(":")));
                int myMin = Integer.parseInt(dateString.substring(3, 5));
                if ((Math.abs(myMin - trueMin) > 3)) {
                    System.out.println("FALSE TIME: " + Math.abs(myMin - trueMin));
                    falseCounter++;
                } else {
                    trueCounter++;
                }
            } else {
                System.out.println("FALSE TIME: " + Math.abs(myHour - trueHour));
                falseCounter++;
            }

            //}

        }

        System.out.println("true counter: " + trueCounter);
        System.out.println("false Counter: " + falseCounter);
        System.out.println("while Counter: " + whileCount);

        assert falseCounter < 30;
    }

    @Test
    void getSunSetTime() throws ParseException, IOException, JSONException {
        String listDate, trueSunrise, trueSunset, dayLength, trueMoonrise, trueMoonset;
        double latitude, longitude;
        Scanner scanner = new Scanner(new File("output.txt"));

        int whileCount = 0;
        int trueCounter = 0, falseCounter = 0;
        while (scanner.hasNextLine()) {
            whileCount++;
            latitude = parseDouble(scanner.nextLine());
            longitude = parseDouble(scanner.nextLine());
            listDate = scanner.nextLine();
            trueSunrise = scanner.nextLine();
            trueSunset = scanner.nextLine();
            dayLength = scanner.nextLine();
            trueMoonrise = scanner.nextLine();
            trueMoonset = scanner.nextLine();
            listDate += "T14:00:00Z";
            Date date = sonarController.getSunSetTime(listDate, latitude, longitude);
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss a");
            String dateString = dateFormat.format(date);
            System.out.println("Current time in AM/PM: " + dateString);

            int trueHour = Integer.parseInt(trueSunset.substring(0, trueSunset.indexOf(":")));
            int myHour = Integer.parseInt(dateString.substring(0, 2));
            if ((Math.abs(myHour - trueHour) == 3) || (Math.abs(myHour - trueHour) == 9) || (Math.abs(myHour - trueHour) == 15)) {
                int trueMin = Integer.parseInt(trueSunset.substring(trueSunset.indexOf(":") + 1, trueSunset.lastIndexOf(":")));
                int myMin = Integer.parseInt(dateString.substring(3, 5));
                if ((Math.abs(myMin - trueMin) > 3)) {
                    System.out.println("FALSE TIME FOR MINUTE: " + Math.abs(myMin - trueMin));
                    falseCounter++;
                } else {
                    trueCounter++;
                }
            } else {
                System.out.println("FALSE TIME FOR HOUR: " + Math.abs(myHour - trueHour));
                falseCounter++;
            }

            //}

        }

        System.out.println("true counter: " + trueCounter);
        System.out.println("false Counter: " + falseCounter);
        System.out.println("while Counter: " + whileCount);

        assert falseCounter < 30;
    }

    @Test
    void getMoonRiseTime() throws ParseException, IOException, JSONException {
        String listDate, trueSunrise, trueSunset, dayLength, trueMoonrise, trueMoonset;
        double latitude, longitude;
        Scanner scanner = new Scanner(new File("output.txt"));

        int whileCount = 0;
        int trueCounter = 0, falseCounter = 0;
        while (scanner.hasNextLine()) {
            whileCount++;
            latitude = parseDouble(scanner.nextLine());
            System.out.println(latitude);
            longitude = parseDouble(scanner.nextLine());
            System.out.println(longitude);
            listDate = scanner.nextLine();
            System.out.println(listDate);
            trueSunrise = scanner.nextLine();
            System.out.println(trueSunrise);
            trueSunset = scanner.nextLine();
            System.out.println(trueSunset);

            dayLength = scanner.nextLine();
            System.out.println(dayLength);

            trueMoonrise = scanner.nextLine();
            System.out.println(trueMoonrise);

            trueMoonset = scanner.nextLine();
            System.out.println(trueMoonset);

            listDate += "T14:00:00Z";
            Date date = sonarController.getMoonRiseTime(listDate, latitude, longitude);
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss a");
            String dateString = dateFormat.format(date);
            System.out.println("Current time in AM/PM: " + dateString);

            // TODO Burayi kontrol et! dogru parse edip saat farkinin 2 dk dan fazla olmamsi lazim !!!
            /*if (trueSunrise.contains("A")) {
                trueSunrise = trueSunrise.substring(0, trueSunrise.length() - 3);*/
            int trueHour = Integer.parseInt(trueMoonrise.substring(0, trueMoonrise.indexOf(":")));
            int myHour = Integer.parseInt(dateString.substring(0, 2));
            /*if(trueHour == myHour){*/
/*
            if( (Math.abs(myHour-trueHour)==3) || (Math.abs(myHour-trueHour) == 9) || (Math.abs(myHour-trueHour) == 15) ){
*/
            if ((Math.abs(myHour - trueHour) == 3)) {
                int trueMin = Integer.parseInt(trueMoonrise.substring(trueMoonrise.indexOf(":") + 1, trueMoonrise.lastIndexOf(":")));
                int myMin = Integer.parseInt(dateString.substring(3, 5));
                if ((Math.abs(myMin - trueMin) > 3)) {
                    System.out.println("FALSE TIME FOR MINUTE: " + Math.abs(myMin - trueMin));
                    falseCounter++;
                } else {
                    trueCounter++;
                }
            } else {
                System.out.println("FALSE TIME FOR HOUR: " + Math.abs(myHour - trueHour));
                falseCounter++;
            }

            //}

        }

        System.out.println("true counter: " + trueCounter);
        System.out.println("false Counter: " + falseCounter);
        System.out.println("while Counter: " + whileCount);

        assert falseCounter < 30;
    }

    @Test
    void getDayLength() throws ParseException, IOException, JSONException {
        String listDate, trueSunrise, trueSunset, dayLength, trueMoonrise, trueMoonset;
        double latitude, longitude;
        Scanner scanner = new Scanner(new File("output.txt"));

        int whileCount = 0;
        int trueCounter = 0, falseCounter = 0;
        while (scanner.hasNextLine()) {
            whileCount++;
            latitude = parseDouble(scanner.nextLine());
            System.out.println(latitude);
            longitude = parseDouble(scanner.nextLine());
            System.out.println(longitude);
            listDate = scanner.nextLine();
            System.out.println(listDate);
            trueSunrise = scanner.nextLine();
            System.out.println(trueSunrise);
            trueSunset = scanner.nextLine();
            System.out.println(trueSunset);

            dayLength = scanner.nextLine();
            System.out.println(dayLength);

            trueMoonrise = scanner.nextLine();
            System.out.println(trueMoonrise);

            trueMoonset = scanner.nextLine();
            System.out.println(trueMoonset);

            listDate += "T14:00:00Z";
            Date dateMillis = sonarController.getDayLength(listDate, latitude, longitude);
            System.out.println("date millis: " + dateMillis);
            //Date date = new Date(dateLong);
           /* String s =String.format("%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(dateMillis),
                    TimeUnit.MILLISECONDS.toMinutes(dateMillis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(dateMillis)), // The change is in this line
                    TimeUnit.MILLISECONDS.toSeconds(dateMillis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(dateMillis)));
            System.out.println("S:" + s);*/
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            String dateString = dateFormat.format(dateMillis);
            System.out.println("Current time in AM/PM: " + dateString);

            // TODO Burayi kontrol et! dogru parse edip saat farkinin 2 dk dan fazla olmamsi lazim !!!
            /*if (trueSunrise.contains("A")) {
                trueSunrise = trueSunrise.substring(0, trueSunrise.length() - 3);*/
            int trueHour = Integer.parseInt(dayLength.substring(0, dayLength.indexOf(":")));
            int myHour = Integer.parseInt(dateString.substring(0, 2));
            if (trueHour == myHour) {
                int trueMin = Integer.parseInt(dayLength.substring(dayLength.indexOf(":") + 1, dayLength.lastIndexOf(":")));
                int myMin = Integer.parseInt(dateString.substring(3, 5));
                if ((Math.abs(myMin - trueMin) > 3)) {
                    System.out.println("FALSE TIME FOR MINUTE: " + Math.abs(myMin - trueMin));
                    falseCounter++;
                } else {
                    trueCounter++;
                }
            } else {
                System.out.println("FALSE TIME FOR HOUR: " + Math.abs(myHour - trueHour));
                falseCounter++;
            }

            //}

        }

        System.out.println("true counter: " + trueCounter);
        System.out.println("false Counter: " + falseCounter);
        System.out.println("while Counter: " + whileCount);

        assert falseCounter < 30;
    }
}

   /* DateFormat displayFormat = new SimpleDateFormat("HH:mm:ss");
    DateFormat parseFormat = new SimpleDateFormat("hh:mm:ss aa");*/

        /*
                Date trueDate = parseFormat.parse(trueSunrise.substring(0, trueSunrise.length()-3));
*/
        /*System.out.println("sunrise without am-pm: " + trueDate);
            System.out.println("millis: " + trueDate.getTime());*/
          /*String myDate = date + "";
            myDate = myDate.substring(11,19);*/
/*long millis = date.getTime();*/
        /*Date date1 = parseFormat.parse(trueSunrise);
                String str = displayFormat.format(date1);
                System.out.println("str: " + str + " datestr: " + dateStr);*/
    /*String input = "23/12/2014 10:22:12 PM";
    //Format of the date defined in the input String
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");
    //Desired format: 24 hour format: Change the pattern as per the need
    DateFormat outputformat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
    Date date = null;
    String output = null;
      try{
        //Converting the input String to Date
        date= df.parse(input);
        //Changing the format of date and storing it in String
        output = outputformat.format(date);
        //Displaying the date
        System.out.println(output);
    }catch(ParseException pe){
        pe.printStackTrace();
    }*/

       /* List<String> list = sonarController.test();
         latitude = parseDouble(list.get(0));
         longitude = parseDouble(list.get(1));
         listDate = list.get(2) + "T14:00:00Z";
        Date mySunrise = sonarController.getSunRiseTime(listDate, latitude, longitude);
        //mySunrise.getHours();
        String mySunriseStr = mySunrise + "";
        mySunriseStr = mySunriseStr.substring(11, 19);
        System.out.println("my sunrise: " + mySunriseStr);
        trueSunrise = list.get(3);*/


/*assert (!list.get(3).equals(mySunriseStr));*/

/*List<String> list = sonarController.test();
        double lt = Double.parseDouble(list.get(0).substring(0, 7));
        double lo = Double.parseDouble(list.get(1).substring(0, 7));
        if (list.get(3).equals(sonarController.getSunRiseTime(list.get(3), lt, lo) + "")) {
            System.out.println("equals");
        } else {
            System.out.println("not equals");
        }*//*


    }

}



/*
        assert !car.getSunrise().equals(sonarController.getSunRiseTime("2021-12-28T17:00:00Z", -18.42196987917298, -176.46748825090367));
*//*


//assertThat(liste, cont);
*/
/*
        ObjectMapper objectMapper = new ObjectMapper();
*//*


//sonarController.params.values().iterator().next();
*/
/*
        FileInputStream input = new FileInputStream("C:\\Users\\xyykucuk\\Downloads\\demo\\demo\\src\\main\\resources\\data.json");
*//*

 */
/* System.out.println("input read: " + input.read());
        SunriseSunsetResult car = objectMapper.readValue(input, SunriseSunsetResult.class);
        System.out.println("json file: " + car.getSunrise());*//*


 */
/*List<String> liste = sonarController.test();*//*


//System.out.println("asfasfwf" + sonarController.getSunRiseTime("2021-12-28T17:00:00Z",-18.42196987917298,-176.46748825090367));

*/
/* final String url = "https://api.sunrise-sunset.org/json";


        Map<String, String> params = new LinkedHashMap<>();
        params.put("lat", "36.7201600");
        params.put("lng", "-4.4203400");
        params.put("date", "2020-03-10");
        String result = restTemplate.getForObject(url, String.class, params);

        assert result != null;*/

