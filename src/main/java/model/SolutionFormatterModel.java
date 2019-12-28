package model;

import functionality.SolutionFormatter;
import org.springframework.stereotype.Component;

@Component
public class SolutionFormatterModel implements SolutionFormatter {
    @Override
    public String format(Integer solution) {
        return String.format("%,d", solution);
    }
}
