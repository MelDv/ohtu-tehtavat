package statistics.matcher;

import statistics.Player;

public class QueryBuilder {

    private And a;
    private HasAtLeast hal;

    public QueryBuilder() {
        a = new And();
    }

    public And build() {
        return a;
    }
}
