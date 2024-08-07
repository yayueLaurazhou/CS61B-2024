package ngrams;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    /** If it helps speed up your code, you can assume year arguments to your NGramMap
     * are between 1400 and 2100. We've stored these values as the constants
     * MIN_YEAR and MAX_YEAR here. */
    public static final int MIN_YEAR = 1400;
    public static final int MAX_YEAR = 2100;

    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        // TODO: Fill in this constructor.
        for (int i = startYear; i <= endYear; i++) {
            if (ts.containsKey(i)) {
                this.put(i, ts.get(i));
            }
        }
    }

    public TimeSeries(TimeSeries ts) {
        super(ts);
    }

    /**
     * Returns all years for this TimeSeries (in any order).
     */
    public List<Integer> years() {
        // TODO: Fill in this method.
        Set<Integer> keys = this.keySet();
        return new ArrayList<>(keys);
    }

    /**
     * Returns all data for this TimeSeries (in any order).
     * Must be in the same order as years().
     */
    public List<Double> data() {
        // TODO: Fill in this method.
        List<Integer> years = this.years();
        List<Double> data = new ArrayList<>();
        for (int year : years){
            data.add(this.get(year));
        }
        return data;
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     *
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {
        // TODO: Fill in this method.
        return yearwiseOperation(ts, "plus");
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     *
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        // TODO: Fill in this method.
        return yearwiseOperation(ts, "divide");
    }



    private TimeSeries yearwiseOperation(TimeSeries ts, String operation) {

        TimeSeries result = new TimeSeries();
        for (int year : this.keySet()) {
            if (ts.containsKey(year)) {
                if (operation.equals("plus")){
                    result.put(year, this.get(year) + ts.get(year));
                } else {
                    result.put(year, this.get(year) / ts.get(year));
                }
            } else if (operation.equals("plus")) {
                result.put(year, this.get(year));
            } else {
                throw new IllegalArgumentException("Missing year in given time series");
            }
        }

        if (operation.equals("plus")) {
            for (int year : ts.keySet()) {
                if (!result.containsKey(year)) {
                    result.put(year, ts.get(year));
                }
            }
        }

        return result;
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
