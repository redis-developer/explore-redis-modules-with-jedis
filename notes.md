# presentation notes

## RedisJSON

- Perhaps the most powerful feature of document databases is the ability to nest objects inside of documents.
- A good rule of thumb for structuring data in JSON document databases is to prefer embedding data inside documents to breaking it apart into separate collections
- unless you have a good reason

JSON.GET "json:shopping_cart"
JSON.GET "json:shopping_cart" "."
JSON.GET "json:shopping_cart" ".userId"
JSON.GET "json:shopping_cart" ".tags"
JSON.GET "json:db"

## RedisBloom.

127.0.0.1:6379> CMS.QUERY "doge:buyOrSell" "buy"
127.0.0.1:6379> CMS.QUERY "doge:buyOrSell" "sell"

## RedisGraph

MATCH (n) RETURN distinct labels(n)
MATCH (n) RETURN distinct labels(n), count(n)
MATCH ()-[e]->() RETURN distinct type(e)
MATCH ()-[e]->() RETURN distinct type(e), count(e)
MATCH (au:Author) RETURN au
MATCH (au:Author { id: 'xxxx' }) RETURN au
MATCH (au:Author { id: 'xxxx' })-[:AUTHORED]->(ar:Article) RETURN ar
MATCH (au:Author { id: 'xxxx' })-[:AUTHORED]->(ar:Article)<-[:AUTHORED]-(cau:Author) WHERE au <> cau RETURN au, cau, ar"

