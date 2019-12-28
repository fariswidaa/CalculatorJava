import model.SolutionFormatterModel;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;


public class SolutionFormatterTest {
    private SolutionFormatterModel cut;

    @Before
    public void setup() {
        cut = new SolutionFormatterModel();
    }

    @Test
    public void format_returnsAFormattedSolution_whenGivenAThreeDigitsNumber() {
        String formattedInteger = cut.format(999);
        assertThat(formattedInteger, is(equalTo("999")));
    }

    @Test
    public void format_returnsAFormattedSolution_whenGivenAFourDigitsNumber() {
        String formattedInteger = cut.format(1610);
        assertThat(formattedInteger, is(equalTo("1,610")));
    }

    @Test
    public void format_returnsAFormattedSolution_whenGivenAFiveDigitsNumber() {
        String formattedInteger = cut.format(16100);
        assertThat(formattedInteger, is(equalTo("16,100")));
    }

    @Test
    public void format_returnsAFormattedSolution_whenGivenASixDigitsNumber() {
        String formattedInteger = cut.format(1000000);
        assertThat(formattedInteger, is(equalTo("1,000,000")));
    }

    @Test
    public void format_returnsAFormattedSolution_whenGivenANegativeSixDigitsNumber() {
        String formattedInteger = cut.format(-100000);
        assertThat(formattedInteger, is(equalTo("-100,000")));
    }

    @Test
    public void format_returnsTwoCommaFormattedSolution_whenGivenASixDigitsNumber() {
        String formattedInteger = cut.format(1000020);
        assertThat(formattedInteger, is(equalTo("1,000,020")));
    }
}
