package Graph;

class Doubly_Linked_Edge {
    //A node class for doubly linked list

    //Initially, heaed and tail is set to null
    GraphNode head, tail = null;

    //add a node to the list of edges
    public GraphNode addNode(int nodeKey) {
        //Create a new node
        GraphNode newNode = new GraphNode(nodeKey);

        //if list is empty, head and tail points to newNode
        if (head == null) {
            head = tail = newNode;
            //head's previous will be null
            head.Prev = null;
            //tail's next will be null
        } else {
            //add newNode to the start of list.
            head.Prev = newNode;
            //newNode->next set to head
            newNode.Next = head;
            //            //newNode becomes new head
            head = newNode;
            //head's next point to null
        }
        head.Prev = null;
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
