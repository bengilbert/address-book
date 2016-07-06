package addressbook.query;

import java.util.Optional;

public interface CountingQuery {
    Optional<Long> runQuery();
}
