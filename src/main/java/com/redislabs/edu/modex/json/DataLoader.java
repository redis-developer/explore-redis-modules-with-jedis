package com.redislabs.edu.modex.json;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.redislabs.modules.rejson.JReJSON;

public class DataLoader {
  public static void main(String[] args) throws URISyntaxException, IOException {
    JReJSON reJSON = new JReJSON();
    reJSON.set("json:shopping_cart", loadFromResources("/json/shopping_cart.json"));
    reJSON.set("json:db", loadFromResources("/json/db.json"));
  }

  private static JsonObject loadFromResources(String path) throws IOException, URISyntaxException {
    String raw = Files.readString(Paths.get(DataLoader.class.getResource(path).toURI()));
    return new Gson().fromJson(raw, JsonObject.class);
  }
}
