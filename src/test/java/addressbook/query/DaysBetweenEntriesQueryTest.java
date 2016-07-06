package addressbook.query;

import addressbook.entry.Entry;
import addressbook.entry.Gender;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class DaysBetweenEntriesQueryTest {

    @Test
    public void ifEitherNameDoesntExistThenEmptyIsReturned() {
        DaysBetweenEntriesQuery daysBetweenEntriesQuery = new DaysBetweenEntriesQuery(Collections.emptySet(), "missing name one", "missing name two");
        assertThat(daysBetweenEntriesQuery.runQuery().isPresent(), is(false));
    }

    @Test
    public void shouldReturnZeroWhenBothNameExistAndHaveSameBirthday() throws IOException {
        ImmutableSet<Entry> entries = ImmutableSet.of(new Entry("name one", Gender.MALE, LocalDate.of(1980, 1, 1)),
                new Entry("name two", Gender.MALE, LocalDate.of(1980, 1, 1)));

        DaysBetweenEntriesQuery daysBetweenEntriesQuery = new DaysBetweenEntriesQuery(entries, "Name one", "Name two");

        assertThat(daysBetweenEntriesQuery.runQuery().isPresent(), is(true));
        assertThat(daysBetweenEntriesQuery.runQuery().get(), is(0L));
    }

    @Test
    public void shouldReturnOneWhenBothNameExistAndHaveBirthdayOneDayApart() throws IOException {
        ImmutableSet<Entry> entries = ImmutableSet.of(new Entry("name one", Gender.MALE, LocalDate.of(1980, 1, 1)),
                new Entry("name two", Gender.MALE, LocalDate.of(1980, 1, 2)));

        DaysBetweenEntriesQuery daysBetweenEntriesQuery = new DaysBetweenEntriesQuery(entries, "Name one", "Name two");

        assertThat(daysBetweenEntriesQuery.runQuery().isPresent(), is(true));
        assertThat(daysBetweenEntriesQuery.runQuery().get(), is(1L));
    }

    @Test
    public void orderDoesntMatter() throws IOException {
        ImmutableSet<Entry> entries = ImmutableSet.of(new Entry("name one", Gender.MALE, LocalDate.of(1980, 1, 1)),
                new Entry("name two", Gender.MALE, LocalDate.of(1980, 1, 2)));

        DaysBetweenEntriesQuery daysBetweenEntriesQuery = new DaysBetweenEntriesQuery(entries, "Name two", "Name one");

        assertThat(daysBetweenEntriesQuery.runQuery().isPresent(), is(true));
        assertThat(daysBetweenEntriesQuery.runQuery().get(), is(1L));
    }

}