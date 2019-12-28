import calculator.Calculator;
import calculator.CalculatorController;
import functionality.SolutionFormatter;
import model.CalculatorModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CalculatorController.class)
@RunWith(SpringJUnit4ClassRunner.class)

public class CalculatorControllerSIT {

    public CalculatorController classUnderTest;

    @Autowired
    public CalculatorModel calculatorModel;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Calculator calculator;

    @MockBean
    private SolutionFormatter solutionFormatter;


    @Test
    public void givenAStudentUsingTheApp_whenAreRequestIsMadeToAdd_thenASolutionSouldBeShown() throws Exception {
        when(calculator.add(2, 3)).thenReturn(5);
        mockMvc.perform(post("/calculator")
                .param("leftArgument", "2")
                .param("rightArgument", "3")
                .param("calculationType", "ADDITION")
        ).andExpect(status().is2xxSuccessful()).
                andExpect(content().string(containsString("id=\"solution\""))).
                andExpect(content().string(containsString(">5</span>")));


        verify(calculator).add(2, 3);
        verify(solutionFormatter).format(5);
    }

}
