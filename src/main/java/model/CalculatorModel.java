package model;

public class CalculatorModel {
    private static final String SEPERATOR = "";
    private Integer leftArgument;
    private Integer rightArgument;
    private Integer solution;
    private CalculationType type;

    private String formattedSolution;

    public CalculatorModel(CalculationType calculationType, int leftArgument, int rightArgument) {
        this.type = calculationType;
        this.leftArgument = leftArgument;
        this.rightArgument = rightArgument;
    }

    /**
     * Convenience Constructor used in test
     */
    public CalculatorModel(CalculationType calculationType, int leftArgument, int rightArgument, Integer solution) {
        this.type = calculationType;
        this.leftArgument = leftArgument;
        this.rightArgument = rightArgument;
        this.solution = solution;
    }


    public static CalculatorModel fromText(String calculation) {
        String[] parts = calculation.split(" ");
        int leftArgument = Integer.parseInt(parts[0]);
        int rightArgument = Integer.parseInt(parts[2]);
        CalculationType calculationType = CalculationType.fromSymbol(parts[1]);

        return new CalculatorModel(
                calculationType, leftArgument, rightArgument);
    }

    public Integer getLeftArgument() {
        return leftArgument;
    }

    public void setLeftArgument(Integer leftArgument) {
        this.leftArgument = leftArgument;
    }

    public Integer getRightArgument() {
        return rightArgument;
    }

    public void setRightArgument(Integer rightArgument) {
        this.rightArgument = rightArgument;
    }

    public Integer getSolution() {
        return solution;
    }

    public void setSolution(Integer solution) {
        this.solution = solution;
    }

    public CalculationType getType() {
        return type;
    }

    public void setType(CalculationType type) {
        this.type = type;
    }

    public String getFormattedSolution() {
        return formattedSolution;
    }

    public void setFormattedSolution(String formattedSolution) {
        this.formattedSolution = formattedSolution;
    }

}

