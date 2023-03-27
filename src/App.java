import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
  public static void main(String[] args) throws Exception {
    System.out.println("Hello, World!");

    String imdbUrl = "https://gist.githubusercontent.com/lucasfugisawa/cbb0d10ee3901bd0541468e431c629b3/raw/1fe1f3340dfe5b5876a209c0e8226d090f6aef10/Top250Movies.json";
    URI address = URI.create(imdbUrl);
    HttpRequest request = HttpRequest.newBuilder(address).GET().build();
    HttpClient client = HttpClient.newHttpClient();
    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    String body = response.body();

    JsonParser jsonParser = new JsonParser();

    List<Map<String, String>> movies = jsonParser.parse(body);
    System.out.println(movies.get(0).get("title"));

    for (Map<String,String> movie : movies) {
      System.out.println(movie.get("title"));
      System.out.println(movie.get("image"));
      System.out.println(movie.get("imDbRating") + "\n");
    }

    System.out.println("👍");
    System.out.println(Character.toString(128_512));
    // Rodar no terminal cmd "chcp 65001" para aparecer os emojis
  }
}
