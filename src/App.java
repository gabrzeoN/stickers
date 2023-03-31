import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
  public static void main(String[] args) throws Exception {
    String envVar = System.getenv("ENV_VAR");
    System.out.println("Enviroment var: " + envVar);

    // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json";
    // String url = "https://gist.githubusercontent.com/lucasfugisawa/cbb0d10ee3901bd0541468e431c629b3/raw/1fe1f3340dfe5b5876a209c0e8226d090f6aef10/Top250Movies.json";
    // ImdbContentExtractor extractor = new ImdbContentExtractor();
    
    String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/NASA-APOD.json";
    NasaContentExtractor extractor = new NasaContentExtractor();

    String jsonData = new HttpClientApp().getData(url);

    List<Content> contents = extractor.extract(jsonData);

    String pink = "\u001b[35m";
    String reset = "\u001b[m";
    String bold = "\u001b[1m";
    String blueBg = "\u001b[37m \u001b[44m";

    Sticker sticker = new Sticker();
    String stickerPhrase = "muito massa mulecote";

    for (Content content : contents) {
      String likes = "";
      // for(int i = 0; i < intImDbRating; i++) likes += "❤️ ";

      System.out.println(pink + "Title: " + reset + content.getTitle());
      System.out.println(bold + "Image: " + reset + blueBg + content.getUrlImage() + reset);
      // System.out.println(bold + "Rating: " + reset + movie.get("imDbRating") + " " + likes +"\n");
      
      // String imagePath = "./inputs/movie.jpg";
      // BufferedImage originalImage = sticker.getOriginalImageFromPathname(imagePath);
  
      BufferedImage originalImage = sticker.getOriginalImageFromUrl(content.getUrlImage());
      sticker.create(content.getTitle(), originalImage, stickerPhrase);
    }

    System.out.println(Character.toString(128_512) + "👍" + " Done!");
    // Rodar no terminal cmd "chcp 65001" para aparecer os emojis
    // ' export ENV_VAR="VALUE" ' to set environment variables


  }
}
