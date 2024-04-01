package undecided.demo;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

public class SpringModulithTests {


  ApplicationModules modules = ApplicationModules.of(ModulithDemoApplication.class);

  @Test
  void verifyPackageConformity() {
    modules.verify();
  }

  @Test
  void createModulithsDocumentation() {
    new Documenter(modules).writeDocumentation();
  }
}

