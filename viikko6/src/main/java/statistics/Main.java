package statistics;

import statistics.matcher.*;

public class Main {

    public static void main(String[] args) {
        // seuraavassa osoitteessa 27.11.2019 päivitetyt tilastot
        String url = "https://nhl27112019.herokuapp.com/players.txt";
        // ajan tasalla olevat tilastot osoitteessa
//        String url = "https://nhlstatisticsforohtu.herokuapp.com/players.txt";

        QueryBuilder query = new QueryBuilder();
        Statistics stats = new Statistics(new PlayerReaderImpl(url));
        Matcher m = query.build();

        for (Player player : stats.matches(m)) {
            System.out.println(player);
        }
    }
}
