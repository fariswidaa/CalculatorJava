import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;


@RunWith(PowerMockRunner.class)
@PrepareForTest({BatchCalculatorFileReader.class, Files.class, Paths.class})
public class BatchReaderFileTest {

    @Test
    public void getCalculationsFromBatchFile_shouldReturnAStreamOfCalculations_whenGivenAValidFile() throws IOException {

        // ARRANGE
        BatchCalculatorFileReader classUnderTest = new BatchCalculatorFileReader();

        // Mocking
        String fakeBatchFile = "/path/to/batchFile.txt";
        // fake response stream
        List<String> expectedCalculations = Arrays.asList("1 + 2", "3 - 2");


        PowerMockito.mockStatic(Files.class);
        PowerMockito.mockStatic(Paths.class);

        Path path = mock(Path.class);
        when(Paths.get(fakeBatchFile)).thenReturn(path);
        // return the stream of our calculations
        when(Files.lines(path)).thenReturn(expectedCalculations.stream());

        // ACT
        Stream<String> response = classUnderTest.read(fakeBatchFile);
        List<String> responseAsList = response.collect(Collectors.toList());

        // ASSERT
        assertThat(responseAsList, containsInAnyOrder(expectedCalculations.toArray()));
    }

}

