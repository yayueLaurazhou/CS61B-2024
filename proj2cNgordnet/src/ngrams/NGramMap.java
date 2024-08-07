package ngrams;

import java.sql.Time;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import edu.princeton.cs.algs4.In;
import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    // TODO: Add any necessary static/instance variables.
    private TimeSeries totalcount = new TimeSeries();
    private Map<String, TimeSeries> wordsData= new HashMap<>();

    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        // TODO: Fill in this constructor. See the "NGramMap Tips" section of the spec for help.
        In wordsin = new In(wordsFilename);
        while (wordsin.hasNextLine()) {
            String nextline = wordsin.readLine();
            String[] splitLine = nextline.split("\t");
            String word = splitLine[0];
            Integer year = Integer.parseInt(splitLine[1]);
            Double count = Double.parseDouble(splitLine[2]);
            if (wordsData.containsKey(word)){
                wordsData.get(word).put(year, count);
            } else {
                TimeSeries wordcount = new TimeSeries();
                wordcount.put(year, count);
                wordsData.put(word, wordcount);
            }
        }

        In in = new In(countsFilename);
        while (in.hasNextLine()) {
            String nextline = in.readLine();
            String[] splitLine = nextline.split(",");
            Integer year = Integer.parseInt(splitLine[0]);
            Double count = Double.parseDouble(splitLine[1]);
            totalcount.put(year, count);
        }
    }


    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        if (!wordsData.containsKey(word)) {
            return new TimeSeries();
        }
        TimeSeries wordTimeSeries = wordsData.get(word);
        return new TimeSeries(wordTimeSeries, startYear, endYear);
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        // TODO: Fill in this method.
        if (!wordsData.containsKey(word)) {
            return new TimeSeries();
        }
        TimeSeries wordTimeSeries = wordsData.get(word);
        return new TimeSeries(wordTimeSeries);
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        // TODO: Fill in this method.
        return new TimeSeries(totalcount);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        return new TimeSeries(weightHistoryHelper(word), startYear, endYear);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        // TODO: Fill in this method.
        return new TimeSeries(weightHistoryHelper(word));
    }


    private TimeSeries weightHistoryHelper(String word) {
        if (!wordsData.containsKey(word)) {
            return new TimeSeries();
        }
        TimeSeries wordTimeSeries = wordsData.get(word);
        TimeSeries total = totalCountHistory();
        return wordTimeSeries.dividedBy(total);
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        TimeSeries sum = new TimeSeries();
        TimeSeries total = totalCountHistory();
        for (String word : words) {
            if (!wordsData.containsKey(word)) {
                continue;
            }
            sum = wordsData.get(word).plus(sum);
        }
        return new TimeSeries(sum.dividedBy(total), startYear, endYear);
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        TimeSeries sum = new TimeSeries();
        TimeSeries total = totalCountHistory();
        for (String word : words) {
            if (!wordsData.containsKey(word)) {
                continue;
            }
            sum = wordsData.get(word).plus(sum);
        }
        return new TimeSeries(sum.dividedBy(total));
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
