import functionality.BatchCalculatorFileReaderInterface;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class BatchCalculatorFileReader implements BatchCalculatorFileReaderInterface {

    @Override
    public Stream<String> read(String file) throws IOException {
        return Files.lines(Paths.get(file));
    }
}
