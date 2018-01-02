package db;

import simulation.GlobalParameters;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DateHandler {

    public static Date getRelativeDate (Date initDate, int relativeDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(initDate);
        calendar.add(Calendar.DAY_OF_YEAR, +relativeDays);
        return calendar.getTime();
    }

    public static String getRelativeDate (String initDate, int relativeDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getDateFromString(initDate));
        calendar.add(Calendar.DAY_OF_YEAR, +relativeDays);
        return getStringDate(calendar.getTime());
    }

    public static Date getRelativeTime (Date initDate, int relativeHours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(initDate);
        calendar.add(Calendar.HOUR_OF_DAY, +relativeHours);
        return calendar.getTime();
    }

    public static String getStringDate (Date date) {
        return new java.sql.Date(date.getTime()).toString();
    }

    public static Date getDateFromString (String date) {
        date = date.replace("-","");
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date myDate = null;
        try {
            myDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(myDate);
        return calendar.getTime();
    }

    public static Date GetDate(String Day, String Hour) {
        String[] DayTab = Day.split("-");
        String[] HourTab = Hour.split(":");

        int year = Integer.parseInt(DayTab[0]);
        int month = Integer.parseInt(DayTab[1]);
        int day = Integer.parseInt(DayTab[2]);
        int hour = Integer.parseInt(HourTab[0]);
        int minute = Integer.parseInt(HourTab[1]);
        // int second = Integer.parseInt(TmpTab[0]);
        // TODO: Check why splitting string does not work in this case

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        // Because of some reasons Java lists months from zero...
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    public static String getRandomDate() {
        int year = ThreadLocalRandom.current().nextInt(2015, 2016 + 1); // losowy rok
        int day = ThreadLocalRandom.current().nextInt(1, 364 + 1); // losowy dzień roku
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.DAY_OF_YEAR, day);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String s = sdf.format(calendar.getTime());
        s = s.replace("-", "");
        return s;
    }

    public static String getRandomTime() {
        final Random random = new Random();
        final int millisInDay = 24 * 60 * 60 * 1000;
        Time time = new Time((long) random.nextInt(millisInDay));
        return time.toString();
    }

    public static int getPercentOfTimeLeft(Date StartDate, Date EndDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(GlobalParameters.currentTime);
        long now = calendar.getTimeInMillis();
        long s = StartDate.getTime();
        long e = EndDate.getTime();
        if (s >= e || now >= e) {
            return 0;
        }
        if (now <= s) {
            return 100;
        }
        return (int) ((e - now) * 100 / (e - s));
    }

}
