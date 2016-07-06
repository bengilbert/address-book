import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;
import java.util.Set;


public class EntryFactory {

    public static final int NUMBER_OF_FIELDS = 3;

    public static Set<Entry> fromFile(File addressBookFile) {
        Set<Entry> entries = new HashSet<Entry>();

        try {
            Reader in = new FileReader(addressBookFile);
            Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(in);
            for (CSVRecord record : records) {
                if (record.size() >= NUMBER_OF_FIELDS) {
                    String name = record.get(0).trim();
                    String genderText = record.get(1).trim();
                    String dateOfBirth = record.get(2).trim();

                    if (isValidEntry(name, genderText, dateOfBirth)) {

                        Gender gender = Gender.UNKNOWN;

                        if ("male".equalsIgnoreCase(genderText)) {
                            gender = Gender.MALE;
                        } else if ("female".equalsIgnoreCase(genderText)) {
                            gender = Gender.FEMALE;
                        }

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
        return !name.isEmpty() && !genderText.isEmpty() && !dateOfBirth.isEmpty();
    }
}
