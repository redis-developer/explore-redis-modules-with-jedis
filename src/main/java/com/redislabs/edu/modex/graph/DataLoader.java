package com.redislabs.edu.modex.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

import com.github.javafaker.Faker;
import com.redislabs.redisgraph.impl.api.RedisGraph;

import org.apache.commons.lang3.StringUtils;

import redis.clients.jedis.Jedis;

public class DataLoader {
  enum ArticleType {
    INFLUENCE, LEARN, COOK, TEACH
  }

  static Map<ArticleType, String> titles = Map.of( //
      ArticleType.INFLUENCE, "The influence of %s on %s", //
      ArticleType.LEARN, "What I learn about %s from %s", //
      ArticleType.COOK, "How to cook %s with %s", //
      ArticleType.TEACH, "Teach your %s how to pilot a %s" //
  );

  private static String getFakeArticleName(Faker faker) {
    var keys = titles.keySet();
    var rando = (long) (keys.size() * Math.random());
    var key = keys.stream().skip(rando).findAny().get();
    var param1 = "";
    var param2 = "";
    switch (key) {
    case INFLUENCE:
      param1 = faker.ancient().god();
      param2 = faker.food().dish();
      break;
    case LEARN:
      param1 = faker.artist().name();
      param2 = faker.backToTheFuture().character();
      break;
    case COOK:
      param1 = faker.food().dish();
      param2 = StringUtils.capitalize(faker.animal().name());
      break;
    case TEACH:
      param1 = faker.animal().name();
      param2 = faker.aviation().aircraft();
      break;
    }

    return String.format(titles.get(key), param1, param2);
  }
  
  public static void main(String[] args) {
    var graphId = "article-graph";
    try (Jedis jedis = new Jedis()) {
      if (!jedis.exists(graphId)){
        try (RedisGraph graph = new RedisGraph()) {
          // create an index for Books on id
          graph.query(graphId, "CREATE INDEX ON :Article(id)");
          graph.query(graphId, "CREATE INDEX ON :Author(name)");

          var faker = new Faker();

          var authors = new HashMap<String, String>();
          IntStream.range(0, 250).forEach(n -> {
            var author = faker.name().fullName();
            if (!authors.keySet().contains(author)) {
              var authorId = UUID.randomUUID().toString();
              graph.query(graphId, String.format("CREATE (:Author {id: \"%s\", name: \"%s\"})", authorId, author));
              authors.put(author, authorId);
            }
          });
          System.out.println(">>> create 250 authors");

          var random = new Random();
          var titles = new HashSet<String>();
          IntStream.range(0, 2500).forEach(i -> {

            var articleName = getFakeArticleName(faker);
            while (titles.contains(articleName)) {
              articleName = getFakeArticleName(faker);
            }
            titles.add(articleName);
            var articleId = UUID.randomUUID().toString();

            graph.query(graphId, //
                String.format("CREATE (:Article {id: \"%s\", title: \"%s\", price: %s})", //
                    articleId, //
                    articleName, //
                    Double.parseDouble(faker.commerce().price())) //
            );

            IntStream.range(0, random.nextInt(2) + 1).forEach(j -> {
              var authorId = authors.values().stream().skip(new Random().nextInt(authors.size())).findFirst().get();

              graph.query(graphId,
                  String.format(
                      "MATCH (au:Author {id: \"%s\"}), (ar:Article {id: \"%s\"}) CREATE (au)-[:AUTHORED]->(ar)",
                      authorId, articleId));
            });
          });
          System.out.println(">>> create 2500 articles");
        }
      }
    }
  }
}
