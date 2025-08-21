package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class NextBusWait {

    public static String waitMinutes(String[] schedule, String time) {
        if (schedule == null || schedule.length == 0) return "-1";

        int now = toMinutes(time);
        // Convert schedule to minutes
        int[] times = Arrays.stream(schedule).mapToInt(NextBusWait::toMinutes).toArray();

        // Binary search for first time >= now
        int lo = 0, hi = times.length - 1, ans = -1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if (times[mid] >= now) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        if (ans == -1) return "-1";            // no more buses today
        return String.valueOf(times[ans] - now); // minutes to wait
    }

    private static int toMinutes(String hhmm) {
        // hhmm is "HH:MM" in 24-hour format
        int h = Integer.parseInt(hhmm.substring(0, 2));
        int m = Integer.parseInt(hhmm.substring(3, 5));
        return h * 60 + m;
    }
    public static String waitHHMM(String[] schedule, String time) {
        String mins = waitMinutes(schedule, time);
        if ("-1".equals(mins)) return "-1";
        int w = Integer.parseInt(mins);
        int h = w / 60, m = w % 60;
        return String.format("%02d:%02d", h, m);
    }

    public static void main(String[] args)
    {
        String[] schedule = {"07:15", "09:00", "12:30", "18:05"};
        System.out.println(waitMinutes(schedule, "09:00"));
        System.out.println(waitMinutes(schedule, "10:10"));
        System.out.println(waitMinutes(schedule, "19:00"));
        System.out.println(waitHHMM(schedule, "10:10"));

    }
}
