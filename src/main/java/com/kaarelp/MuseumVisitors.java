package com.kaarelp;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MuseumVisitors {

    public static void main(String[] args) {
        MuseumVisitors museumVisitors = new MuseumVisitors();
        try (Stream<String> lines = FileUtil.getVisitorsFileLines()) {
            List<VisitationPeriod> maxVisitorsPeriod = museumVisitors.findMaxVisitorsPeriod(lines);
            System.out.println(maxVisitorsPeriod.toString());
        } catch (URISyntaxException | IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public List<VisitationPeriod> findMaxVisitorsPeriod(Stream<String> visitationsStream) {
        if (visitationsStream == null) return Collections.emptyList();
        Map<Integer, Integer> visitationsMap = read(visitationsStream);
        if (visitationsMap.isEmpty()) return Collections.emptyList();
        int maxSimultaneousVisitors = getMaxSimultaneousVisitors(visitationsMap);
        List<Map.Entry<Integer, Integer>> period = getVisitationPeriodOf(visitationsMap, maxSimultaneousVisitors);
        return mapTo(period);
    }

    private Map<Integer, Integer> read(Stream<String> visitations) {
        Map<Integer, Integer> map = new HashMap<>();
        visitations.forEach(s -> {
            String[] startAndEndTimestamps = s.split(",");
            addVisitationToMap(map, toMinutes(startAndEndTimestamps[0]), toMinutes(startAndEndTimestamps[1]));
        });
        return map;
    }

    private int toMinutes(String timestamp) {
        String[] hoursAndMinutes = timestamp.split(":");
        return Integer.parseInt(hoursAndMinutes[0]) * 60 + Integer.parseInt(hoursAndMinutes[1]);
    }

    private void addVisitationToMap(Map<Integer, Integer> map, int startMinute, int endMinute) {
        for (int i = startMinute; i <= endMinute; i++) {
            if (map.containsKey(i)) {
                int visitationsDuringThisMinute = map.get(i);
                map.put(i, visitationsDuringThisMinute + 1);
            } else {
                map.put(i, 1);
            }
        }
    }

    private int getMaxSimultaneousVisitors(Map<Integer, Integer> visitations) {
        return visitations.values().stream().max(Integer::compareTo).orElse(0);
    }

    private List<Map.Entry<Integer, Integer>> getVisitationPeriodOf(Map<Integer, Integer> visitations,
                                                                    int nrOfSimultaneousVisitors) {
         return visitations.entrySet().stream()
                 .filter(entry -> entry.getValue().equals(nrOfSimultaneousVisitors))
                 .collect(Collectors.toList());
    }

    private List<VisitationPeriod> mapTo(List<Map.Entry<Integer, Integer>> visitationPeriodMinutes) {
        List<Map.Entry<Integer, Integer>> sorted = sortByKeys(visitationPeriodMinutes);
        List<VisitationPeriod> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : sorted) {
            VisitationPeriod period = new VisitationPeriod(entry);
            if (result.isEmpty()) {
                result.add(period);
                continue;
            }
            VisitationPeriod previous = result.get(result.size() - 1);
            if (period.isAdjacentTo(previous)) {
                previous.add(period);
            } else {
                result.add(period);
            }
        }
        return result;
    }

    private List<Map.Entry<Integer, Integer>> sortByKeys(List<Map.Entry<Integer, Integer>> unsorted) {
        return unsorted.stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toList());
    }
}
