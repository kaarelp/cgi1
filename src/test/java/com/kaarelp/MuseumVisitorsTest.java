package com.kaarelp;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Naming convention used for tests: UnitOfWork_StateUnderTest_ExpectedBehavior
 * https://osherove.com/blog/2005/4/3/naming-standards-for-unit-tests.html
 */
class MuseumVisitorsTest {

    @Test
    void findMaxVisitorsPeriod_null_emptyList() {
        MuseumVisitors m = new MuseumVisitors();

        List<VisitationPeriod> actual = m.findMaxVisitorsPeriod(null);

        assertThat(actual, is(empty()));
    }

    @Test
    void findMaxVisitorsPeriod_emptyStream_emptyList() {
        MuseumVisitors m = new MuseumVisitors();

        List<VisitationPeriod> actual = m.findMaxVisitorsPeriod(Stream.empty());

        assertThat(actual, is(empty()));
    }

    @Test
    void findMaxVisitorsPeriod_singleVisitation_found() {
        MuseumVisitors m = new MuseumVisitors();

        List<VisitationPeriod> actual = m.findMaxVisitorsPeriod(singleVisitationStream());

        assertThat(actual, is(not(empty())));
        assertThat(actual, hasSize(1));
        VisitationPeriod found = actual.get(0);
        assertEquals("00:00", found.getStartingMinuteIn24hFormat());
        assertEquals("00:05", found.getEndingMinuteIn24hFormat());
        assertEquals(1, found.getNrSimultaneousOfVisitors());
    }

    @Test
    void findMaxVisitorsPeriod_singleVisitationCaseMinuteOverflow_found() {
        MuseumVisitors m = new MuseumVisitors();

        List<VisitationPeriod> actual = m.findMaxVisitorsPeriod(singleVisitationStreamCaseMinuteOverflow());

        assertThat(actual, is(not(empty())));
        assertThat(actual, hasSize(1));
        VisitationPeriod found = actual.get(0);
        assertEquals("00:00", found.getStartingMinuteIn24hFormat());
        assertEquals("01:00", found.getEndingMinuteIn24hFormat());
        assertEquals(1, found.getNrSimultaneousOfVisitors());
    }

    @Test
    void findMaxVisitorsPeriod_twoLeftPartiallyOverlappingVisitations_found() {
        MuseumVisitors m = new MuseumVisitors();

        List<VisitationPeriod> actual = m.findMaxVisitorsPeriod(twoLeftPartiallyOverlappingVisitationsStream());

        assertThat(actual, is(not(empty())));
        assertThat(actual, hasSize(1));
        VisitationPeriod found = actual.get(0);
        assertEquals("13:00", found.getStartingMinuteIn24hFormat());
        assertEquals("13:03", found.getEndingMinuteIn24hFormat());
        assertEquals(2, found.getNrSimultaneousOfVisitors());
    }

    private Stream<String> singleVisitationStream() {
        return Stream.of("00:00,00:05");
    }

    private Stream<String> singleVisitationStreamCaseMinuteOverflow() {
        return Stream.of("00:00,01:00");
    }

    private Stream<String> twoLeftPartiallyOverlappingVisitationsStream() {
        return Stream.of("13:00,13:05", "13:00,13:03");
    }

}