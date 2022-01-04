package Graph;

public class srct_algo {

    public void Main() {
        DynamicGraph g = new DynamicGraph();
        GraphNode node1 = g.insertNode(1);
        GraphNode node2 =  g.insertNode(2);
        GraphNode node3 =  g.insertNode(3);
        g.insertNode(4);
        g.insertEdge(node1, node2);
        g.insertEdge(node3, node1);
    }
}
