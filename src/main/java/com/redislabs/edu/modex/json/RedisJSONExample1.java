package com.redislabs.edu.modex.json;

import com.google.gson.JsonObject;
import com.redislabs.modules.rejson.JReJSON;

public class RedisJSONExample1 {
  public static void main(String[] args) {
    System.out.println(">>> RedisJSON in Action - Read whole document...");

    JReJSON json = new JReJSON();
    JsonObject shoppingCart = json.get("json:shopping_cart", JsonObject.class);

    System.out.println(">>> ==================");
    System.out.println(">>> json:shopping_cart");
    System.out.println(">>> ==================");

    System.out.println(shoppingCart.toString());
  }
}
