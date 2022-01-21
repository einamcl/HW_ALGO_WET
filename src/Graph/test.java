package Graph;

import java.io.DataOutputStream;
import java.io.IOException;

public class test {
    public static void main(String[] args) {
        DataOutputStream outStream = new DataOutputStream(System.out);
        DynamicGraph graph = new DynamicGraph();
        GraphNode source = graph.insertNode(1);
        GraphNode two = graph.insertNode(2);
        GraphNode three = graph.insertNode(3);
        GraphNode four = graph.insertNode(4);
        GraphNode five = graph.insertNode(5);
        GraphNode six = graph.insertNode(6);
        GraphNode seven = graph.insertNode(7);
        GraphNode eight = graph.insertNode(8);
        GraphNode ten = graph.insertNode(10);
        GraphNode twenty = graph.insertNode(20);
        GraphNode thirty = graph.insertNode(30);
        graph.insertEdge(ten, twenty);
        graph.insertEdge(twenty, thirty);
        graph.insertEdge(thirty,ten);
        graph.insertEdge(source, three);
        graph.insertEdge(source, two);
        graph.insertEdge(two, three);
        graph.insertEdge(three,five);
        graph.insertEdge(three,four);
        graph.insertEdge(five, source);
        graph.insertEdge(six, seven);
        graph.insertEdge(six, two);
        graph.insertEdge(seven, eight);
        graph.insertEdge(seven, source);
        graph.insertEdge(eight,six);
        graph.insertEdge(eight,five);
    Doubly_Linked<GraphNode>vertices=graph.DFS(graph.graph_nodes,false,false);
//        RootedTree tree= graph.BFS(graph.graph_nodes.head.getData());
          RootedTree sccTree = graph.scc();
//        RootedTree tree = graph.BFS(source);
//
//        try {
//            tree.preorderPrint(outStream, source);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            tree.printByLayer(outStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}