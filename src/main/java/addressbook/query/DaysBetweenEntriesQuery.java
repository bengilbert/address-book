package addressbook.query;

import addressbook.entry.Entry;

import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Set;


public class DaysBetweenEntriesQuery implements CountingQuery {

    private Set<Entry> addressBookEntries;
    private String firstName;
    private String secondName;

    public DaysBetweenEntriesQuery(final Set<Entry> addressBookEntries, final String firstName, final String secondName) {
        this.addressBookEntries = addressBookEntries;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    @Override
    public Optional<Long> runQuery() {
        Optional<Entry> entry1 = addressBookEntries.stream().filter(entry -> entry.getName().equalsIgnoreCase(firstName)).findFirst();
        Optional<Entry> entry2 = addressBookEntries.stream().filter(entry -> entry.getName().equalsIgnoreCase(secondName)).findFirst();

        if (!entry1.isPresent() || !entry2.isPresent()) {
            return Optional.empty();
        }

        return Optional.of(Math.abs(ChronoUnit.DAYS.between(entry1.get().getDateOfBirth(), entry2.get().getDateOfBirth())));
    }
}
