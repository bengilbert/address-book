package addressbook;

import addressbook.entry.Entry;
import addressbook.entry.EntryFactory;
import addressbook.query.DaysBetweenEntriesQuery;
import addressbook.query.NumberOfMalesQuery;
import addressbook.query.OldestEntriesQuery;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class AddressBook {

    private Set<Entry> entries = new HashSet<>();

    public long numberOfMales() {
        return new NumberOfMalesQuery(entries).runQuery().get();
    }

    public List<Entry> oldest() {
        return new OldestEntriesQuery(entries).runQuery();
    }

    public Optional<Long> findDaysBetweenEntriesByName(final String nameOne, final String nameTwo) {
        return new DaysBetweenEntriesQuery(entries, nameOne, nameTwo).runQuery();
    }

    public void loadFromFile(File addressBookFile) {
        entries.addAll(EntryFactory.fromFile(addressBookFile));
    }
}
