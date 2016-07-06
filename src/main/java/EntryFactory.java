import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;


public class EntryFactory {

    private static final int NUMBER_OF_FIELDS = 3;

    public static Set<Entry> fromFile(File addressBookFile) {
        Set<Entry> entries = new HashSet<>();

        try {
            Reader in = new FileReader(addressBookFile);
            Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(in);
            for (CSVRecord record : records) {
                if (record.size() >= NUMBER_OF_FIELDS) {
                    String name = record.get(0).trim();
                    String genderText = record.get(1).trim();
                    String dateOfBirthText = record.get(2).trim();

                    if (isValidEntry(name, genderText, dateOfBirthText)) {

                        Gender gender = Gender.UNKNOWN;

                        if ("male".equalsIgnoreCase(genderText)) {
                            gender = Gender.MALE;
                        } else if ("female".equalsIgnoreCase(genderText)) {
                            gender = Gender.FEMALE;
                        }

                        LocalDate dateOfBirth = parseDateOfBirth(dateOfBirthText);

                        Entry entry = new Entry(name, gender, dateOfBirth);
                        entries.add(entry);
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return entries;
    }

    private static boolean isValidEntry(String name, String genderText, String dateOfBirth) {
        return !name.isEmpty() && !genderText.isEmpty() && !dateOfBirth.isEmpty() && dateOfBirth.matches("^\\d+\\/\\d+\\/\\d+$");
    }

    private static LocalDate parseDateOfBirth(final String dateOfBirthText) {
        LocalDate dateOfBirth = LocalDate.parse(dateOfBirthText, DateTimeFormatter.ofPattern("dd/MM/yy"));

        if (dateOfBirth.isAfter(ChronoLocalDate.from(LocalDate.now()))) {
            return dateOfBirth.minusYears(100);
        }

        return dateOfBirth;

    }
}
