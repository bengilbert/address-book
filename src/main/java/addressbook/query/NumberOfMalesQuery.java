package addressbook.query;

import addressbook.entry.Entry;
import addressbook.entry.Gender;

import java.util.Optional;
import java.util.Set;


public class NumberOfMalesQuery implements CountingQuery {

    private Set<Entry> addressBookEntries;

    public NumberOfMalesQuery(Set<Entry> addressBookEntries) {
        this.addressBookEntries = addressBookEntries;
    }

    @Override
    public Optional<Long> runQuery() {
        return Optional.of(addressBookEntries.stream().filter(entry -> Gender.MALE.equals(entry.getGender())).count());
    }
}
