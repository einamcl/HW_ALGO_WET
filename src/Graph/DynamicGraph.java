package Graph;

public class DynamicGraph {
    public  Doubly_Linked Node_List=new Doubly_Linked();

    public DynamicGraph(){

    }

    public GraphNode insertNode(int nodeKey){
        return(Node_List.addNode(nodeKey));
    }

    public void deleteNode(GraphNode node){
        if(node.In_Edge.head!=null||node.Out_Edge.tail!=null) //If the head or tail are not null, then there is outgoing or incoming edge to the node
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


    public RootedTree scc(){

    }

    public void enque(Doubly_Linked Q, GraphNode node)
    {
        Q.tail=node;
        node.Next=null;
    }
    public GraphNode deque(Doubly_Linked Q)
    {
        GraphNode temp=Q.head;
        Q.head=temp.Next;
        return temp;
    }



    public RootedTree BFS(GraphNode source) {
       Doubly_Linked queue=new Doubly_Linked();
       BFS_Init(source,queue);
       while(queue.head!=null)
       {
           GraphNode u = deque(queue);
           GraphNode temp = u.Out_Edge.head;
           while( temp != null){
               if(temp.color.equals("white")){
                   temp.color = "gray";
                   temp.time = u.time+1;
                   temp.parent = u;
                   enque(queue, temp);
                   temp = temp.Next;
               }
               u.color = "black";
           }
       }
       RootedTree tree = new RootedTree();
       tree.source = source;
       return tree;
    }

 public void BFS_Init(GraphNode source,Doubly_Linked Queue)
 {
     GraphNode temp=source.Next;
     while(temp!=null)
     {
         temp.color="white";
         temp.time=Integer.MAX_VALUE;
         temp.parent=null;
         temp=temp.Next;
     }
     source.color="gray";
     source.time=0;
     source.parent=null;
     Queue.addNode(0);
 }


}