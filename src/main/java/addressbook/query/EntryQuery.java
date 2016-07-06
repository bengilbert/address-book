package addressbook.query;

import addressbook.entry.Entry;

import java.util.List;

public interface EntryQuery {
    List<Entry> runQuery();
}
