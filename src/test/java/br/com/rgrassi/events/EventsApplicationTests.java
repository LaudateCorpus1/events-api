package br.com.rgrassi.events;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@Profile("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class EventsApplicationTests {
  @Test
  public void contextLoads() {
      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
      System.out.println("********** encode: " + encoder.encode("root"));
  }
}
