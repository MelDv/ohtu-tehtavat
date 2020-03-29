package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";

        String bodyText = Request.Get(url).execute().returnContent().asString();

//        System.out.println("json-muotoinen data:");
//        System.out.println(bodyText);
        Gson mapper = new Gson();
        Player[] players = mapper.fromJson(bodyText, Player[].class);
        ArrayList<Player> fin = new ArrayList<>();
        for (int i = 0; i < players.length; i++) {
            if (players[i].getNationality().equals("FIN")) {
                fin.add(players[i]);
            }
        }
        Player[] p = new Player[fin.size()];
        for (int i = 0; i < p.length; i++) {
            p[i] = fin.get(i);
        }
        for (int j = 0; j < p.length; j++) {
            for (int i = 0; i < p.length; i++) {
                if (p[i].getSum() < p[j].getSum()) {
                    Player t = p[i];
                    p[i] = p[j];
                    p[j] = t;
                }
            }
        }
        ZonedDateTime date = ZonedDateTime.now();
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss z yyyy");
        System.out.println("Players from FIN " + date.format(formattedDate));
        System.out.println("");

        for (Player player : p) {
            System.out.print(player);
        }
    }
}
