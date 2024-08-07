package graph;
import edu.princeton.cs.algs4.In;
import ngrams.TimeSeries;
import org.checkerframework.checker.units.qual.A;

import java.util.*;

public class HyponymsGraph {
    Graph g = new Graph();

    public HyponymsGraph(String hyponymsFilename, String SynsetsFilename){
        In hyponymsIn = new In(hyponymsFilename);
        In synsetsIn = new In(SynsetsFilename);
        while (synsetsIn.hasNextLine()) {
            String nextline = synsetsIn.readLine();
            String[] splitLine = nextline.split(",");
            Integer index = Integer.parseInt(splitLine[0]);
            String[] splitWord = splitLine[1].split(" ");
            g.addNode(index, splitWord);
        }

        while (hyponymsIn.hasNextLine()) {
            String nextline = hyponymsIn.readLine();
            String[] splitLine = nextline.split(",");
            Integer index = Integer.parseInt(splitLine[0]);
            Graph.Node parent = g.findNodeAtIndex(index);
            for (int i = 1; i < splitLine.length; i++){
                Integer graphIndex = Integer.parseInt(splitLine[i]);
                Graph.Node child = g.findNodeAtIndex(graphIndex);
                g.addEdgeAtoB(parent, child);
            }
        }
    }

    public String[] findHyponymsSingle(String word){
        TreeSet<String> result = findHyponymsHelper(word);
        String[] hyponyms = new String[result.size()];
        hyponyms = result.toArray(hyponyms);
        return hyponyms;
    }


    public String[] findHyponymsList(ArrayList<String> words){
        TreeSet<String> result = findHyponymsHelper(words.get(0));
        for (int i = 1; i < words.size(); i++){
            result.retainAll(findHyponymsHelper(words.get(i)));
        }
        String[] hyponyms = new String[result.size()];
        hyponyms = result.toArray(hyponyms);
        return hyponyms;
    }

    public TreeSet<String> findHyponymsHelper(String word){
        TreeSet<String> result = new TreeSet<>();
        ArrayList<Graph.Node> parents = g.findNodesContainsWord(word);
        ArrayList<Graph.Node> childNodes = new ArrayList<>();
        for (Graph.Node parent : parents){
            childNodes.addAll(g.traverse(parent));
        }
        for (Graph.Node node : childNodes){
            result.addAll(List.of(node.wordsList));
        }
        return result;
    }

    public TreeSet<String> findAncestorsSingle(String word){
        ArrayList<Graph.Node> currentNodes = g.findNodesContainsWord(word);
        TreeSet<String> result = new TreeSet<>();
        for (Graph.Node node : currentNodes){
            result.addAll(g.findNodeAncestor(node));
        }
       return result;
    }

    public String[] findCommonAncestors(ArrayList<String> words){
        TreeSet<String> result = new TreeSet<>(findAncestorsSingle(words.get(0)));
        for (int i = 1; i < words.size(); i++){
            result.retainAll(findAncestorsSingle(words.get(i)));
        }
        String[] ancestors = new String[result.size()];
        ancestors = result.toArray(ancestors);
        return ancestors;
    }
}
