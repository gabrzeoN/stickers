import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
  public static void main(String[] args) throws Exception {
    String envVar = System.getenv("ENV_VAR");
    System.out.println("Enviroment var: " + envVar);

    String topTvsUrl = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json";
    String top250Url = "https://gist.githubusercontent.com/lucasfugisawa/cbb0d10ee3901bd0541468e431c629b3/raw/1fe1f3340dfe5b5876a209c0e8226d090f6aef10/Top250Movies.json";
    
    String body = new HttpClientApp().getUrlContent(top250Url);

    JsonParser jsonParser = new JsonParser();
    List<Map<String, String>> movies = jsonParser.parse(body);

    String pink = "\u001b[35m";
    String reset = "\u001b[m";
    String bold = "\u001b[1m";
    String blueBg = "\u001b[37m \u001b[44m";

    for (Map<String,String> movie : movies) {
      String imDbRating = movie.get("imDbRating");
      int intImDbRating = (int) Float.parseFloat(imDbRating);
      String likes = "";
      for(int i = 0; i < intImDbRating; i++) likes += "❤️ ";

      System.out.println(pink + "Title: " + reset + movie.get("title"));
      System.out.println(bold + "Poster: " + reset + blueBg + movie.get("image") + reset);
      System.out.println(bold + "Rating: " + reset + movie.get("imDbRating") + " " + likes +"\n");
    }

    System.out.println(Character.toString(128_512) + "👍" + " Done!");
    // Rodar no terminal cmd "chcp 65001" para aparecer os emojis
    // ' export ENV_VAR="VALUE" ' to set environment variables


    String stickerPhrase = "muito massa mulecote";
    Sticker sticker = new Sticker();
    
    // String imagePath = "./inputs/movie.jpg";
    // BufferedImage originalImage = sticker.getOriginalImageFromPathname(imagePath);

    String imagePath = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies_1.jpg";
    BufferedImage originalImage = sticker.getOriginalImageFromUrl(imagePath);
    
    new Sticker().create(originalImage, stickerPhrase);
  }
}
