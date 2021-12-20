package Graph;

public class DynamicGraph {
    private GraphNode[] nodes = null;
    private  GraphEdge[] edges = null;

    public DynamicGraph(){

    }


    public GraphNode insertNode(int nodeKey){
        GraphNode  node = new GraphNode(nodeKey);
        return node;
    }

    public void deleteNode(GraphNode node){
        if (node.outegdes && node.inedges)
            return;
        node =  null;
    }

    public GraphEdge insertEdge(GraphNode from, GraphNode to){}

    public void deleteEdge(GraphEdge edge){}


        public RootedTree scc(){}


    public RootedTree bfs(GraphNode source){}


}
