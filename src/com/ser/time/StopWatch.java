package com.ser.time;

import java.time.LocalTime;

/**
 * Created by Bradley
 * on 6/27/2015.
 */
public class StopWatch {
    private long start;
    private long end;

    private final long milisecondsInHour = 60 * 60 * 1000;
    private final long milisecondsInMinute = 60 * 1000;
    private final long milisecondsInSecond = 1000;

    public StopWatch() {
        start = end = 0;
    }

    public String start() {
        start = System.currentTimeMillis();
        end = 0;
        return now();
    }

    public String end() {
        end = System.currentTimeMillis();
        return now();
    }

    public String now() {
        LocalTime now = LocalTime.now();
        String meridiem = "AM";
        int currHour = now.getHour();//getHourOfDay();
        if (currHour > 12) {
            currHour -= 12;
            meridiem = "PM";
        }
        String hour = String.valueOf(currHour);
        String minute = String.valueOf(now.getMinute());//.getMinuteOfHour());
        String second = String.valueOf(now.getSecond());//.getSecondOfMinute());
        minute = timeFormat(minute);
        second = timeFormat(second);

        return (String.format("%s:%s.%s %s", hour, minute, second, meridiem));
    }

    private String timeFormat(String time) {
        if (time.length() < 2) {
            return String.format("0%s", time);
        }
        return time;
    }

    public long elapsedTime(){
        return end != 0 ? end - start : System.currentTimeMillis() - start;
    }

    @Override
    public String toString() {
        if (start <= end) {
            long totalTime = end - start;
            long hours = totalTime / milisecondsInHour;
            long minutes = (totalTime - (hours * milisecondsInHour)) / milisecondsInMinute;
            long seconds = (totalTime - (hours * milisecondsInHour) - (minutes * milisecondsInMinute)) / milisecondsInSecond;

            StringBuilder time = new StringBuilder();
            if (hours > 0) {
                time.append(hours).append("h ");
                if (minutes == 0) {
                    time.append("00m ");
                    if (seconds == 0) {
                        time.append("00s");
                    }
                }
            }
            if (minutes > 0) {
                if (String.valueOf(minutes).length() > 1) {
                    time.append(minutes);
                } else {
                    time.append("0").append(minutes);
                }
                time.append("m ");
                if (seconds == 0) {
                    time.append("00s");
                }
            }
            if (seconds > 0) {
                time.append(seconds).append("s");
            }
            if (time.length() == 0) {
                time.append("0s");
            }
            return time.toString();
        } else {
            return String.format("Bad time - start: %s | end: %s", String.valueOf(start), String.valueOf(end));
        }
    }
}
