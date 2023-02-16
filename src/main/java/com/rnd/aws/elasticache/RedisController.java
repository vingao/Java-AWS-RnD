package com.rnd.aws.elasticache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/redis")
public class RedisController {

  @Autowired RedisUtil redisUtil;

  @Autowired CacheService cacheService;

  @GetMapping("/cache/{name}")
  public ResponseEntity<String> cacheMethod(
          @PathVariable("name") String name) {
    return ResponseEntity.ok(cacheService.cacheMethod(name));
  }

  @PostMapping("/cache/{name}")
  public ResponseEntity<String> clearCache(
          @PathVariable("name") String name) {
    return ResponseEntity.ok(cacheService.clearCache(name));
  }

  @GetMapping("/keys")
  public ResponseEntity<List<String>> getAllKeys() {
    return ResponseEntity.ok(redisUtil.getKeys());
  }

  @GetMapping("/keys/{key}")
  public ResponseEntity<Object> getKey(@PathVariable("key") String key) {
    Instant start = Instant.now();
    Object value = redisUtil.getValue(key);
    long duration = Duration.between(start, Instant.now()).toMillis();
    ResponseEntity<Object> ok = ResponseEntity.ok("got value " + value + " in " + duration + " ms");
    return ok;
  }

  @PostMapping("/keys/{key}/{value}")
  public ResponseEntity<Boolean> setKeys(
      @PathVariable("key") String key, @PathVariable("value") String value) {
    redisUtil.setValue(key, value);
    return ResponseEntity.ok(true);
  }

  @DeleteMapping("/keys/{key}")
  public ResponseEntity<Boolean> deleteKey(@PathVariable("key") String key) {
    redisUtil.deleteKey(key);
    return ResponseEntity.ok(true);
  }
}
