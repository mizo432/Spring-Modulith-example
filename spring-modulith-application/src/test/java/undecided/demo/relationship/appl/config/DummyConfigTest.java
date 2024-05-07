package undecided.demo.relationship.appl.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import undecided.demo.relationship.internal.appl.config.DummyConfig;

@SpringBootTest
class DummyConfigTest {

  @Autowired
  DummyConfig targetConfig;

  @Test
  void getTitle() {
    System.out.println(targetConfig);

  }
}