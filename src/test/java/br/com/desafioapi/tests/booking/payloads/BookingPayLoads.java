package br.com.desafioapi.tests.booking.payloads;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookingPayLoads {

    public JSONObject payLoadValidBooking() {
        JSONObject payLoad = new JSONObject();
        JSONObject bookingDates = new JSONObject();

        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2020-01-01");

        payLoad.put("firstname", "Ronaldo");
        payLoad.put("lastname", "Nazário");
        payLoad.put("totalprice", 540);
        payLoad.put("depositpaid", true);
        payLoad.put("bookingdates", bookingDates);
        payLoad.put("additionalneeds", "Breakfast");

        return payLoad;
    }

    public JSONObject payLoadChangeBooking() {
        Faker name = new Faker();

        JSONObject payLoad = new JSONObject();
        JSONObject bookingDates = new JSONObject();

        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2020-01-01");

        payLoad.put("firstname",name.lordOfTheRings().character());
        payLoad.put("lastname",name.animal().name());
        payLoad.put("totalprice", 540);
        payLoad.put("depositpaid", true);
        payLoad.put("bookingdates", bookingDates);
        payLoad.put("additionalneeds", "Breakfast");

        return payLoad;
    }

        public static JSONObject payLoadCreateANewBooking() {
        JSONObject payLoad = new JSONObject();
        JSONObject bookingDates = new JSONObject();
        Faker name = new Faker();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format(formatter);

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime nowplus = LocalDateTime.now().plusYears(1);
        String formattedDatePlus = nowplus.format(formatter1);


        bookingDates.put("checkin",formattedDate);
        bookingDates.put("checkout",formattedDatePlus);

        payLoad.put("firstname", name.dragonBall().character());
        payLoad.put("lastname", name.chuckNorris().fact());
        payLoad.put("totalprice", 540);
        payLoad.put("depositpaid", true);
        payLoad.put("bookingdates", bookingDates);
        payLoad.put("additionalneeds", "Breakfast");
    return payLoad;

    }
        public static JSONObject payLoadInvalidReturn(){

            JSONObject payLoad = new JSONObject();
            JSONObject bookingDates = new JSONObject();

            bookingDates.put("checkin","01-2017-12");
            bookingDates.put("checkout","02-2016-34");

            payLoad.put("firstname","batman");
            payLoad.put("lastname", "robin");
            payLoad.put("totalprice", 540);
            payLoad.put("depositpaid", true);
            payLoad.put("bookingdates", bookingDates);
            payLoad.put("additionalneeds", "Breakfast");
            return payLoad;

        }

    public static JSONObject createReservationWithMoreParametersThanAllowed() {
        JSONObject payLoad = new JSONObject();
        JSONObject bookingDates = new JSONObject();

        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2020-01-01");

        payLoad.put("firstname", "Ronaldo");
        payLoad.put("lastname", "Nazário");
        payLoad.put("surname","litlepotato");
        payLoad.put("age","92");
        payLoad.put("totalprice", 540);
        payLoad.put("depositpaid", true);
        payLoad.put("bookingdates", bookingDates);
        payLoad.put("additionalneeds", "Breakfast");

        return payLoad;
    }

}



