package functionality;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public interface BatchCalculatorFileReaderInterface {

    default Stream<String> read(String file) throws IOException {

        return Files.lines(Paths.get(file));
    }

}
