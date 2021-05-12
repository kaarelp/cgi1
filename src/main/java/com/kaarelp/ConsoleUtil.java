package com.kaarelp;

import java.util.List;

public class ConsoleUtil {

    public static void printResults(List<VisitationPeriod> results) {
        System.out.println(FileUtil.VISITORS_FILE + " tulemused:");
        if (results.isEmpty()) {
            System.out.println("ei leitud midagi :(");
            return;
        }
        System.out.print("Muuseumis viibis kõige rohkem " + results.get(0).getNrSimultaneousOfVisitors()
                + " samaaegset külastajat");
        if (results.size() == 1) {
            System.out.println(", seda ajavahemikus:");
        } else {
            System.out.println(", seda ajavahemikel:");
        }
        results.forEach(visitationPeriod -> System.out.println(">" + visitationPeriod.getStartingMinuteIn24hFormat()
                                                + "-" + visitationPeriod.getEndingMinuteIn24hFormat()));
    }

    public static void printFailure(Exception e) {
        System.out.println("viskas exception :(");
        e.printStackTrace();
    }
}
