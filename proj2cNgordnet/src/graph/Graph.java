package graph;

import java.util.*;

public class Graph {
    public ArrayList<Node> allNodes;

    public Graph(){
        this.allNodes = new ArrayList<>();
    }

    public class Node{
        public String[] wordsList;
        public Node parentNode;
        public ArrayList<Node> adjNode;

        public Node(String[] words) {
            this.wordsList = words;
            this.parentNode = null;
            this.adjNode = new ArrayList<>();
        }
    }

    public ArrayList<Node> findNodesContainsWord(String word){
        ArrayList<Node> result = new ArrayList<>();
        for (Node n : allNodes){
            if (Arrays.asList(n.wordsList).contains(word)){
                result.add(n);
            }
        }
        return result;
    }

    public Node findNodeAtIndex(Integer index){
        return allNodes.get(index);
    }


    public void addEdgeAtoB(Node a, Node b){
        a.adjNode.add(b);
        b.parentNode = a;
    }

    public void addNode(Integer index, String[] words){
        allNodes.add(index, new Node(words));
    }

    public ArrayList<Node> traverse(Node root){
        ArrayList<Node> result = new ArrayList<>();
        traverseHelper(root, result);
        return result;
    }

    public void traverseHelper(Node parent, ArrayList<Node> result){
        result.add(parent);
        if (parent.adjNode.isEmpty()){
            return;
        }
        for (Node child: parent.adjNode){
            traverseHelper(child, result);
        }
    }

    public TreeSet<String> findNodeAncestor(Node node){
        TreeSet<String> result = new TreeSet<>();
        findAncestorHelper(node, result);
        return result;
    }

    public void findAncestorHelper(Node node, TreeSet<String> result){
        result.addAll(List.of(node.wordsList));
        if (node.parentNode == null){
            return;
        }
        findAncestorHelper(node.parentNode, result);
    }
}
