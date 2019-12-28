package functionality;

import java.util.IntSummaryStatistics;
import java.util.List;

public class StatisticClass {

    private IntSummaryStatistics mSummaryStatistics;

    public StatisticClass(IntSummaryStatistics intSummaryStatistics) {
        mSummaryStatistics = intSummaryStatistics;
    }


    public Integer getAverage(List<Integer> samples) {
        samples.forEach(i ->
                mSummaryStatistics.accept(i));
       Double result = mSummaryStatistics.getAverage();
        // return the sum using getAverage);
        return Double.valueOf(result).intValue();
    }
}
