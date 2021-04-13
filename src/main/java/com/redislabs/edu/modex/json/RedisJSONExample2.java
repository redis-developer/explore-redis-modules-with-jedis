package com.redislabs.edu.modex.json;

import java.util.Arrays;

import com.redislabs.modules.rejson.JReJSON;
import com.redislabs.modules.rejson.Path;

public class RedisJSONExample2 {
  public static void main(String[] args) {
    System.out.println(">>> RedisJSON in Action -  Read at a path...");

    JReJSON json = new JReJSON();
    Path path = Path.of(".tags");
    var items = json.get("json:shopping_cart", String[].class, path);

    System.out.println(">>> ========================");
    System.out.println(">>> json:shopping_cart .tags");
    System.out.println(">>> ========================");

    System.out.println(Arrays.toString(items));
  }
}
