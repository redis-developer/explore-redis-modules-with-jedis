package com.redislabs.edu.modex.graph;

import com.redislabs.redisgraph.Record;
import com.redislabs.redisgraph.ResultSet;
import com.redislabs.redisgraph.graph_entities.Node;
import com.redislabs.redisgraph.impl.api.RedisGraph;

public class RedisGraphExample {
  public static void main(String[] args) {
    System.out.println(">>> RedisGraph in Action...");

    String authorId = args[0];

    String cypherQuery = "MATCH (au:Author { id: '%s' })-[:AUTHORED]->(ar:Article)<-[:AUTHORED]-(cau:Author) WHERE au <> cau RETURN au, cau, ar";

    try (RedisGraph graph = new RedisGraph()) {
      ResultSet resultSet = graph.query("article-graph", String.format(cypherQuery, authorId));
      while (resultSet.hasNext()) {
        Record record = resultSet.next();
        Node coAuthor = record.getValue("cau");
        Node article = record.getValue("ar");

        System.out.println("============================================================");
        System.out.println(String.format("%s : %s", "id", coAuthor.getProperty("id").getValue().toString()));
        System.out.println(String.format("%s : %s", "name", coAuthor.getProperty("name").getValue().toString()));
        System.out.println(String.format("%s : %s", "titleId", article.getProperty("id").getValue().toString()));
        System.out.println(String.format("%s : %s", "title", article.getProperty("title").getValue().toString()));
      }
    }
  }
}
