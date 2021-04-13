package com.redislabs.edu.modex.json;

import java.util.Arrays;

import com.redislabs.modules.rejson.JReJSON;
import com.redislabs.modules.rejson.Path;

public class RedisJSONExample4 {
  public static void main(String[] args) {
    System.out.println(">>> RedisJSON in Action -  Mutate an array in the doc...");

    JReJSON json = new JReJSON();
    Path path = Path.of(".tags");
    json.arrInsert("json:shopping_cart", path, 1L, "baz");

    var tags = json.get("json:shopping_cart", String[].class, path);

    System.out.println(">>> =========================================");
    System.out.println(">>> json:shopping_cart JSON.ARRINSERT @ .tags");
    System.out.println(">>> =========================================");

    System.out.println(Arrays.toString(tags));
  }
}
