package com.redislabs.edu.modex.bloom;

import org.apache.commons.lang3.StringUtils;

import io.github.cdimascio.dotenv.Dotenv;
import io.rebloom.client.Client;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterException;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class RedisBloomCMSExample {
  public static void main(String[] args) throws TwitterException {

    Dotenv dotenv = Dotenv.load();
    String consumerKey = dotenv.get("TWITTER_OAUTH_CONSUMER_KEY");
    String consumerSecret = dotenv.get("TWITTER_OAUTH_CONSUMER_SECRET");
    String accessToken = dotenv.get("TWITTER_OAUTH_ACCESS_TOKEN");
    String accessTokenSecret = dotenv.get("TWITTER_OAUTH_ACCESS_TOKEN_SECRET");

    String sketchKey = "doge:buyOrSell";

    ConfigurationBuilder cb = new ConfigurationBuilder() //
        .setDebugEnabled(true) //
        .setOAuthConsumerKey(consumerKey) //
        .setOAuthConsumerSecret(consumerSecret) //
        .setOAuthAccessToken(accessToken) //
        .setOAuthAccessTokenSecret(accessTokenSecret);

    FilterQuery query = new FilterQuery("dogecoin", "stock", "doge", "crypto", "#sell", "#buy");

    try (Client redisBloom = new Client("localhost", 6379)) {
      redisBloom.cmsInitByDim(sketchKey, 16L, 16L);

      new TwitterStreamFactory(cb.build()).getInstance().addListener(new StatusListener() {
        @Override
        public void onStatus(Status status) {
          System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
          int buyCount = StringUtils.countMatches(status.getText(), "buy");
          int sellCount = StringUtils.countMatches(status.getText(), "sell");
          System.out.println("Counts: BUY " + buyCount + ", SELL " + sellCount);
          try (Client redisBloom = new Client("localhost", 6379)) {
            redisBloom.cmsIncrBy(sketchKey, "buy", buyCount);
            redisBloom.cmsIncrBy(sketchKey, "sell", sellCount);
          }
        }

        @Override
        public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
          System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
        }

        @Override
        public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
          System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
        }

        @Override
        public void onScrubGeo(long userId, long upToStatusId) {
          System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
        }

        @Override
        public void onStallWarning(StallWarning warning) {
          System.out.println("Got stall warning:" + warning);
        }

        @Override
        public void onException(Exception ex) {
          ex.printStackTrace();
        }
      }).filter(query);
    }
  }
}
