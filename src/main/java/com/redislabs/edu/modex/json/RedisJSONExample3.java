package com.redislabs.edu.modex.json;

import com.redislabs.modules.rejson.JReJSON;
import com.redislabs.modules.rejson.Path;

public class RedisJSONExample3 {
  public static void main(String[] args) {
    System.out.println(">>> RedisJSON in Action -  Read a field of object in an array...");

    JReJSON json = new JReJSON();
    Path path = Path.of(".items[1].name");
    var secondItemName = json.get("json:shopping_cart", String.class, path);

    System.out.println(">>> =================================");
    System.out.println(">>> json:shopping_cart .items[1].name");
    System.out.println(">>> =================================");

    System.out.println(secondItemName);
  }
}
