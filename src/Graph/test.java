package Graph;

import java.io.DataOutputStream;
import java.io.IOException;

public class test {
    public static void main(String[] args) {
        DataOutputStream outStream = new DataOutputStream(System.out);
        DynamicGraph graph = new DynamicGraph();
        GraphNode source = graph.insertNode(0);
        GraphNode two = graph.insertNode(2);
        GraphNode three = graph.insertNode(3);
        GraphNode four = graph.insertNode(4);
        GraphNode five = graph.insertNode(5);
        GraphNode six = graph.insertNode(6);
        GraphNode seven = graph.insertNode(7);
        GraphNode eight = graph.insertNode(8);
        graph.insertEdge(source, three);
        graph.insertEdge(source, two);
        graph.insertEdge(two, three);
        graph.insertEdge(three,five);
        graph.insertEdge(three,four);
        graph.insertEdge(five, source);
        graph.insertEdge(six, seven);
        graph.insertEdge(six, two);
        graph.insertEdge(seven,eight);
        graph.insertEdge(seven,source);
        graph.insertEdge(eight, six);
        graph.insertEdge(eight, five);
        RootedTree tree3= graph.DFS(source);
        graph.Clean_From_Dfs();
        RootedTree sccTree = graph.scc(source);
        RootedTree tree = graph.BFS(source);
        RootedTree tree2 = graph.DFS(source);
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