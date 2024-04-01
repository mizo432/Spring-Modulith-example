package undecided;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.ApplicationModule;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

public class ApplicationTest {

  @Test
  void writeDocumentationSnippets(){
    var modules = ApplicationModules.of(ModulerModulithApplication.class).verify();

  new Documenter(modules)
      .writeModulesAsPlantUml()
      .writeIndividualModulesAsPlantUml();
  }

}
