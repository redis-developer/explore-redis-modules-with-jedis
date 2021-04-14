# ModEx - Explore Redis Modules with Jedis and Friends

A collection of mini-examples exploring Redis' Module Ecosystem using Jedis-based Redis Module Clients

* [RedisJSON](https://oss.redislabs.com/redisjson/) via [JRedisJSON](https://github.com/RedisJSON/JRedisJSON)
* [RediSearch](https://oss.redislabs.com/redisearch/) via [JRedisSearch](https://github.com/RediSearch/JRediSearch)
* [RedisGraph](https://oss.redislabs.com/redisgraph/) via [JRedisGraph](https://github.com/RedisGraph/JRedisGraph)
* [RedisBloom](http://redisbloom.io) via [JRedisBloom](https://github.com/RedisBloom/JReBloom)
* [RedisTimeSeries](https://oss.redislabs.com/redistimeseries/) via [JRedisTimeSeries](https://github.com/RedisTimeSeries/JRedisTimeSeries/)

**Prerequisites:**

* [Java 11](https://sdkman.io/jdks)
* [Maven 3.2+](https://sdkman.io/sdks#maven)
* [Docker](https://www.docker.com/products/docker-desktop)
* [Redis + Modules ](https://hub.docker.com/r/redislabs/redismod) 6.0.1 or greater

**NOTE:** If you're not on Mac or Windows, you may need to [install Docker Compose](https://docs.docker.com/compose/install/) as well.

* [Getting Started](#getting-started)
* [See Also](#see-also)
* [Help](#help)
* [License](#license)
* [Credit](#credit)

## Getting Started

### Clone the Repository w/ Submodules

To install this example application, run the following commands:

```bash
git clone git@github.com:redis-developer/explore-redis-modules-with-jedis.git --recurse-submodule
```

### Import into your IDE

You can also import the code straight into your IDE:
* [Visual Studio Code](https://code.visualstudio.com/docs/languages/java)
* [Spring Tool Suite (STS)](https://spring.io/guides/gs/sts)
* [IntelliJ IDEA](https://spring.io/guides/gs/intellij-idea/)

### Start the Docker Compose application:

    ```
    cd explore-redis-modules-with-jedis.git/docker
    docker-compose up
    ```

## See Also

Quick Tutorial on Redis' Powerful Modules:

* [RedisJSON Tutorial](https://developer.redislabs.com/howtos/redisjson)
* [RediSearch Tutorial](https://developer.redislabs.com/howtos/redisearch)
* [RedisGraph Tutorial](https://developer.redislabs.com/howtos/redisgraph)
* [Getting Started with Redis Streams and Java](https://redislabs.com/blog/getting-started-with-redis-streams-and-java/)
* [RedisBloom Tutorial](https://developer.redislabs.com/howtos/redisbloom)
* [Unlocking Time-Series Data with Redis](https://redislabs.com/blog/unlocking-timeseries-data-redis/)

The following links on Redis and Java may also be helpful:

* [Java and Redis](https://developer.redislabs.com/develop/java/)

## Help

Please post any questions and comments on the [Redis Discord Server](https://discord.gg/redis), and remember to visit our [Redis Developer Page](https://developer.redislabs.com) for awesome tutorials, project and tips. You can also email me bsb@redislabs.com.

## License

[MIT Licence](http://www.opensource.org/licenses/mit-license.html)

## Credit

Created by [Brian Sam-Bodden](https://github.com/bsbodden) @ [Redis Labs](https://redislabs.com)