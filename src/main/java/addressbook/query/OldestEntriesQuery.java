package addressbook.query;

import addressbook.entry.Entry;

import java.time.LocalDate;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;

public class OldestEntriesQuery implements EntryQuery {

    private Set<Entry> addressBookEntries;

    public OldestEntriesQuery(final Set<Entry> addressBookEntries) {
        this.addressBookEntries = addressBookEntries;
    }

    @Override
    public List<Entry> runQuery() {
        Map<LocalDate, List<Entry>> ages = addressBookEntries.stream().collect(groupingBy(Entry::getDateOfBirth));

        Optional<LocalDate> oldestDate = ages.keySet().stream().min(LocalDate::compareTo);

        if (oldestDate.isPresent()) {
            return ages.get(oldestDate.get());
        }

        return Collections.emptyList();
    }
}
