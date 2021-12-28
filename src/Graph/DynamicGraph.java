package Graph;

public class DynamicGraph {
    public  Doubly_Linked Node_List=new Doubly_Linked();

    public DynamicGraph(){

    }

    public GraphNode insertNode(int nodeKey){
        return(Node_List.addNode(nodeKey));
    }

    public void deleteNode(GraphNode node){
        if(node.In_Edge.head_edge!=null||node.Out_Edge.tail_edge!=null) //If the head or tail are not null, then there is outgoing or incoming edge to the node
            return;
        else
        {
            if(node.Prev!=null) {
                GraphNode node_prev = node.Prev;
                node_prev.Next=node.Next;
            }
            if(node.Next!=null) {
                GraphNode node_next= node.Next;
                node_next.Prev=node.Prev;
            }

        }

    }
    public GraphEdge insertEdge(GraphNode From, GraphNode To)
    {
        From.Out_Edge.addNode(To.nodeKey);
        To.In_Edge.addNode(From.nodeKey);
        GraphEdge Edge = new GraphEdge(From,To);
        return Edge;
    }

    public void deleteEdge(GraphEdge edge){
        GraphNode From_Edge = edge.From;
        GraphNode To_Edge=edge.To;
        From_Edge.Out_Edge.deleteNode(To_Edge);
        To_Edge.In_Edge.deleteNode(From_Edge);
    }

/*
    public RootedTree scc(){}


    public RootedTree bfs(GraphNode source){}
/*/

}