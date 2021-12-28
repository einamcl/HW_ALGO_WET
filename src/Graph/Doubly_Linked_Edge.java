package Graph;

class Doubly_Linked_Edge {
    //A node class for doubly linked list

    //Initially, head and tail is set to null
    GraphNode head_edge, tail_edge = null;

    //add a node to the list of edges
    public GraphNode addNode(int nodeKey) {
        //Create a new node
        GraphNode newNode = new GraphNode(nodeKey);

        //if list is empty, head and tail points to newNode
        if (head_edge == null) {
            head_edge = tail_edge = newNode;
            //head's previous will be null
            head_edge.Prev = null;
            //tail's next will be null
        } else {
            //add newNode to the end of list. tail->next set to newNode
            head_edge.Next = newNode;
            //newNode->previous set to tail
            newNode.Prev = tail_edge;
            //            //newNode becomes new tail
            head_edge = newNode;
            //tail's next point to null
        }
        head_edge.Next = null;
        return newNode;
    }
    public void deleteNode(GraphNode node){

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
}