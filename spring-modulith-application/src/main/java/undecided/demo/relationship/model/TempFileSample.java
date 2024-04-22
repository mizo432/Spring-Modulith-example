package undecided.demo.relationship.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TempFileSample {

  public void anyMethod() {
    try {
      Path tmpPath = Files.createTempFile(Paths.get("/tmp"), "prefix", ".suffix");
      File file = tmpPath.toFile();
      writeFile(file, "こんにゃく");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @SuppressWarnings("ResultOfMethodCallIgnored")
  private void writeFile(File file, String content) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        PrintWriter pw = new PrintWriter(bw)) {
      pw.write(content);
      pw.flush();
    } catch (IOException ignored) {
    } finally {
      // delete the file
      if (file != null && file.exists()) {
        file.delete();
      }
    }
  }
}
