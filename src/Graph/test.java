package Graph;

import java.io.DataOutputStream;
import java.io.IOException;

public class test {
    public static void main(String[] args) {
        DataOutputStream outStream = new DataOutputStream(System.out);
        DynamicGraph graph = new DynamicGraph();
        GraphNode nine = graph.insertNode(9);
        GraphNode eight = graph.insertNode(8);
        GraphNode seven = graph.insertNode(7);
        GraphNode six = graph.insertNode(6);
        GraphNode five = graph.insertNode(5);
        GraphNode four = graph.insertNode(4);
        GraphNode three = graph.insertNode(3);
        GraphNode two = graph.insertNode(2);
        GraphNode one = graph.insertNode(1);
        GraphNode source = graph.insertNode(0);
        graph.insertEdge(source, one);
        graph.insertEdge(source, two);
        graph.insertEdge(one, two);
        graph.insertEdge(one, three);
        graph.insertEdge(one, four);
        graph.insertEdge(two, three);
        graph.insertEdge(two, five);
        graph.insertEdge(two, nine);
        graph.insertEdge(three, eight);
        graph.insertEdge(four, eight);
        graph.insertEdge(five, seven);
        graph.insertEdge(six, four);
        graph.insertEdge(seven, six);
        graph.insertEdge(eight, five);
        graph.insertEdge(eight, seven);
        graph.insertEdge(nine, five);
        RootedTree tree = graph.BFS(source);
        try {
            tree.printByLayer(outStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            tree.preorderPrint(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}