package undecided.demo.relationship.internal.appl.config;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;
import org.springframework.lang.NonNull;

public class YamlPropertySourceFactory implements PropertySourceFactory {

  @Override
  public PropertySource<?> createPropertySource(@NonNull String name,
      @NonNull EncodedResource resource) throws IOException {
    YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
    factory.setResources(resource.getResource());

    // YAML形式のリソースをjava.util.Properties オブジェクトに変換
    Properties properties = factory.getObject();

    // ラッパーであるPropertiesPropertySourceのインスタンスを返却し、解析されたプロパティを読み取れるようにする
    return new PropertiesPropertySource(
        Objects.requireNonNull(resource.getResource().getFilename()),
        Objects.requireNonNull(properties));
  }
}
