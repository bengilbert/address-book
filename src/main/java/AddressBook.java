import java.io.File;
import java.time.LocalDate;
import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

public class AddressBook {

    private Set<Entry> entries = new HashSet<>();

    public long genderCount(final Gender gender) {
        return entries.stream().filter(entry -> gender.equals(entry.getGender())).count();
    }

    public List<Entry> oldest() {
        Map<LocalDate, List<Entry>> ages = entries.stream().collect(groupingBy(Entry::getDateOfBirth));

        Optional<LocalDate> oldestDate = ages.keySet().stream().min(LocalDate::compareTo);

        if (oldestDate.isPresent()) {
            return ages.get(oldestDate.get());
        }

        return Collections.emptyList();
    }

    public void loadFromFile(File addressBookFile) {
        entries.addAll(EntryFactory.fromFile(addressBookFile));
    }
}
