package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";

        String bodyText = Request.Get(url).execute().returnContent().asString();

//        System.out.println("json-muotoinen data:");
//        System.out.println(bodyText);

        Gson mapper = new Gson();
        Player[] players = mapper.fromJson(bodyText, Player[].class);

        ZonedDateTime date = ZonedDateTime.now();
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss z yyyy");
        System.out.println("Players from FIN " + date.format(formattedDate));
        System.out.println("");
        for (Player player : players) {
            System.out.print(player);
        }
    }
}
