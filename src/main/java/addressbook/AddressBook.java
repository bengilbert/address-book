package addressbook;

import addressbook.entry.Entry;
import addressbook.entry.EntryFactory;
import addressbook.query.DaysBetweenEntriesQuery;
import addressbook.query.NumberOfMalesQuery;
import addressbook.query.OldestEntriesQuery;

import java.io.File;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

    public static void main(String args[]) {
        AddressBook addressBook = new AddressBook();
        try {
            addressBook.loadFromFile(new File(AddressBook.class.getResource("/AddressBook.csv").toURI()));
            System.out.println(String.format("Number of males in address book is : %d", addressBook.numberOfMales()));

            System.out.println(String.format("The oldest persion in address book is/are : %s", addressBook.oldest().stream().map(Entry::getName).collect(Collectors.joining(", "))));

            Optional<Long> daysBetweenEntriesByName = addressBook.findDaysBetweenEntriesByName("Bill McKnight", "Paul Robinson");
            if (daysBetweenEntriesByName.isPresent()) {
                System.out.println(String.format("The days between Bill and Paul's birthday is : %d", daysBetweenEntriesByName.get()));
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
