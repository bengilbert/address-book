package addressbook;

import addressbook.entry.Entry;
import addressbook.entry.EntryFactory;
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

    public Optional<Entry> findByName(final String fullName) {
        return entries.stream().filter(entry -> entry.getName().equalsIgnoreCase(fullName)).findFirst();
    }

    public void loadFromFile(File addressBookFile) {
        entries.addAll(EntryFactory.fromFile(addressBookFile));
    }
}
