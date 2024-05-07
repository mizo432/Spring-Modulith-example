package undecided.common.primitive;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Streams {

  public static void sample() {
    try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
      os.writeBytes(new byte[]{(byte) 1, (byte) 2});
      os.flush();
      System.out.println(Arrays.toString(os.toByteArray()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }
}
