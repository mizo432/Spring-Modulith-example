package undecided.demo.relationship.appl.config;

import java.util.Map;
import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:/dummy.yml", factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "report")
@ToString
@Data
public class DummyConfig {

  private String title;

  private Map<String, String> definitions;

}
