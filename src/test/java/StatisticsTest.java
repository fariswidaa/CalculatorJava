import functionality.StatisticClass;
import org.assertj.core.internal.Integers;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith (MockitoJUnitRunner.class)
public class StatisticsTest {

    @Spy
    IntSummaryStatistics intSummaryStatistics;

    private StatisticClass cut ;

    @Before
    public void setUp() throws Exception {
        cut = new StatisticClass(intSummaryStatistics);
    }

    @After
    public void tearDown() throws Exception {
        cut = null ;
    }

    @Test
    public void average_shouldSample_allIntegersProvided(){
        ArgumentCaptor<Integer> sampleCaptor = ArgumentCaptor.forClass(Integer.class);
        List<Integer> samples = Arrays.asList(2, 8, 5, 3, 7);

        Integer result = cut.getAverage(samples);
        verify(intSummaryStatistics, times(samples.size())).accept(sampleCaptor.capture());

        List<Integer> capturedList = sampleCaptor.getAllValues();
        assertThat(capturedList, IsIterableContainingInOrder.contains(samples.toArray()));
    }

    @Test
    public void average_shouldReturnTheMean_ofAListOfIntegers () {
        List<Integer> samples = Arrays.asList(2,8,5,7,3) ;
        Integer average = cut.getAverage(samples);
        assertThat(average,is(equalTo(5)));

    }
}
