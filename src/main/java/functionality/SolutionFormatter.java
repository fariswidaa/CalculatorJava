package functionality;

import org.springframework.stereotype.Component;

@Component
public interface SolutionFormatter {

    String format(Integer solution);
}
