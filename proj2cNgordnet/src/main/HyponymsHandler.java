package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import browser.NgordnetQueryType;
import graph.HyponymsGraph;
import ngrams.NGramMap;
import ngrams.TimeSeries;

import java.util.*;

public class HyponymsHandler extends NgordnetQueryHandler {

    public HyponymsGraph hg;
    public NGramMap np;

    public HyponymsHandler(String hyponymFile, String synsetFile, String wordFile, String countFile){
        hg = new HyponymsGraph(hyponymFile, synsetFile);
        np =  new NGramMap(wordFile, countFile);
    }

    @Override
    public String handle(NgordnetQuery q) {
        ArrayList<String> words = new ArrayList<>(q.words());
        if (q.ngordnetQueryType() == NgordnetQueryType.HYPONYMS){
            Integer limit = q.k();
            String[] result = hg.findHyponymsList(words);

            if (result.length < limit){
                return Arrays.toString(result);
            }

    //      Iterate over the hyponyms, and record their occurrences in ArrayList count
            ArrayList<Double> count = new ArrayList<>();
            for (int i = 0; i < result.length; i++){
                TimeSeries wordHistory = np.countHistory(result[i], q.startYear(), q.endYear());
                List<Double> countPerYear = wordHistory.data();
                Double total = 0.0;
                for (Double d : countPerYear){
                    total += d;
                }
                count.add(i,total);
            }
            ArrayList<Double> countSorted = new ArrayList<>(count);
            countSorted.sort(Collections.reverseOrder());

    //      Find the index of the first k words in the count, and find the corresponding word in result
            ArrayList<String> hyponyms = new ArrayList<>();
            for (int j = 0; j < limit; j++){
                int index = count.indexOf(countSorted.get(j));
                hyponyms.add(result[index]);
            }
            hyponyms.sort(null);
            return hyponyms.toString();
        }
        // find common ancestors
        String[] result = hg.findCommonAncestors(words);
        return Arrays.toString(result);
    }


}
