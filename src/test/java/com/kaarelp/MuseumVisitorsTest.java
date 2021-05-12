package com.kaarelp;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.empty;

/**
 * Naming convention used for tests: UnitOfWork_StateUnderTest_ExpectedBehavior
 * https://osherove.com/blog/2005/4/3/naming-standards-for-unit-tests.html
 */
class MuseumVisitorsTest {
//
//    @Test
//    void findMaxVisitorsPeriod_null_emptyList() {
//        MuseumVisitors m = new MuseumVisitors();
//
//        List<Map.Entry<Integer, Integer>> result = m.findMaxVisitorsPeriod(null);
//
//        assertThat(result, is(empty()));
//    }

//    @Test
//    void findMaxVisitorsPeriod_emptyStream_emptyList() {
//        MuseumVisitors m = new MuseumVisitors();
//
//        List<Map.Entry<Integer, Integer>> result = m.findMaxVisitorsPeriod(Stream.empty());
//
//        assertThat(result, is(empty()));
//    }
//
//    @Test
//    void findMaxVisitorsPeriod_emptyStream_emptyList() {
//        MuseumVisitors m = new MuseumVisitors();
//
////        List<Map.Entry<Integer, Integer>> result = m.findMaxVisitorsPeriod(Stream.empty());
//
//        assertThat(result, is(empty()));
//    }

    private Stream<String> asd() {
        return Stream.of("00:00,00:01");
    }
}