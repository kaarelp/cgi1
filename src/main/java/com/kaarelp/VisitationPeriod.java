package com.kaarelp;

import java.util.Map;

public class VisitationPeriod {

    private int startingMinute;

    private int endingMinute;

    private final int nrOfSimultaneousVisitors;

    public int getStartingMinute() {
        return startingMinute;
    }

    public int getEndingMinute() {
        return endingMinute;
    }

    public int getNrSimultaneousOfVisitors() {
        return nrOfSimultaneousVisitors;
    }

    public VisitationPeriod(int startingMinute, int endingMinute, int nrOfSimultaneousVisitors) {
        this.startingMinute = startingMinute;
        this.endingMinute = endingMinute;
        this.nrOfSimultaneousVisitors = nrOfSimultaneousVisitors;
    }

    public VisitationPeriod(Map.Entry<Integer, Integer> visitationsMapMinute) {
        this(visitationsMapMinute.getKey(), visitationsMapMinute.getKey(), visitationsMapMinute.getValue());
    }

    public boolean isAdjacentTo(VisitationPeriod other) {
        return isToTheLeft(other) || isToTheRight(other);
    }

    private boolean isToTheLeft(VisitationPeriod other) {
        return this.startingMinute - other.getEndingMinute() == 1;
    }

    private boolean isToTheRight(VisitationPeriod other) {
        return other.getStartingMinute() - this.endingMinute == 1;
    }

    public boolean add(VisitationPeriod other) {
        if (this.nrOfSimultaneousVisitors != other.getNrSimultaneousOfVisitors())
            return false;

        if (isToTheLeft(other)) {
            this.startingMinute = other.getStartingMinute();
        } else if (isToTheRight(other)) {
            this.endingMinute = other.getEndingMinute();
        }
        return true;
    }

    @Override
    public String toString() {
        return "VisitationPeriod{" +
                "startingMinute=" + getStartingMinuteIn24hFormat() +
                ", endingMinute=" + getEndingMinuteIn24hFormat() +
                ", nrOfSimultaneousVisitors=" + nrOfSimultaneousVisitors +
                '}';
    }

    public String getStartingMinuteIn24hFormat() {
        return String.format("%02d", startingMinute / 60) + ":" + String.format("%02d", startingMinute % 60);
    }

    public String getEndingMinuteIn24hFormat() {
        /* +1 is added to the minute because if we have a visitation 00:00,00:01 then it is a visitation
            lasting one minute. Internally this single minute occupies index 0 and it's startingMinute == 0 and
            endingMinute == 0 and otherwise human readable format would come out 00:00,00:00. */
        if (endingMinute % 60 == 59) { /* this handles minute overflow so in case ending is at the 59-th minute
                                          it wouldn't print 05:60 */
            return String.format("%02d", endingMinute / 60 + 1) + ":00";
        } else {
            return String.format("%02d", endingMinute / 60) + ":" + String.format("%02d", endingMinute % 60 + 1);
        }
    }
}
