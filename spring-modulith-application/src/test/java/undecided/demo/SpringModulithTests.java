package undecided.demo;

import java.io.File;
import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;
import org.springframework.modulith.test.ApplicationModuleTest;

@ApplicationModuleTest
public class SpringModulithTests {


  ApplicationModules modules =
      ApplicationModules.of(ModulithDemoApplication.class);

  @Test
  void verifyPackageConformity() {
    System.out.println(new File(".").getAbsolutePath());

    System.out.println(modules.toString());
    modules.verify();
  }

  @Test
  void createModulithsDocumentation() {
    new Documenter(modules).writeDocumentation();
  }
}

