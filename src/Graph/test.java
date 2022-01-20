package Graph;

import java.io.DataOutputStream;
import java.io.IOException;

public class test {
    public static void main(String[] args) {
        DataOutputStream outStream = new DataOutputStream(System.out);
        DynamicGraph graph = new DynamicGraph();
        GraphNode source = graph.insertNode(0);
        GraphNode two = graph.insertNode(2);
        GraphNode twen = graph.insertNode(20);
        GraphNode four = graph.insertNode(4);
        GraphNode five = graph.insertNode(5);
        GraphNode nine = graph.insertNode(9);
        GraphNode seven = graph.insertNode(7);
        GraphNode one = graph.insertNode(1);
        graph.insertEdge(source, seven);
        graph.insertEdge(seven, twen);
        graph.insertEdge(seven, two);
        graph.insertEdge(one,four);
        graph.insertEdge(one,nine);
        graph.insertEdge(seven, one);
        graph.insertEdge(twen, five);
         RootedTree tree= graph.BFS(source);
//        graph.Clean_From_Dfs();
//        RootedTree sccTree = graph.scc(source);
//        RootedTree tree = graph.BFS(source);
//        RootedTree tree2 = graph.DFS(source);
        try {
            tree.preorderPrint(outStream, source);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            tree.printByLayer(outStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}