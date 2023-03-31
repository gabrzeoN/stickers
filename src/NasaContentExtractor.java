import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NasaContentExtractor {
  
  public List<Content> extract(String jsonData){
    
    // Extract the attributes needed (title, image, rating)
    JsonParser jsonParser = new JsonParser();
    List<Map<String, String>> attributesList = jsonParser.parse(jsonData);

    // Populate content list
    List<Content> contents = new ArrayList<>();
    for (Map<String, String> attributes : attributesList) {
      String title = attributes.get("title");
      String urlImage = attributes.get("url");
      Content content = new Content(title, urlImage);
      contents.add(content);
    }

    return contents;
  }
}
