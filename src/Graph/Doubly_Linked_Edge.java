/*
package Graph;

//public class Doubly_Linked_Edge<GraphEdge> {
    //A node class for doubly linked list

    //Initially, heaed and tail is set to null
    GraphEdge head, tail = null;


    //add a node to the list of edges
    public Insert edge(GraphEdge edge) {
        //Create a new node

        //if list is empty, head and tail points to newNode
        if (head == null) {
            head = tail = edge;
        } else {
            //add newNode to the start of list.
            head, = newNode;
            //newNode->next set to head
            newNode.Next_Edge = head;
            //            //newNode becomes new head
            head = newNode;
            //head's next point to null
        }
        head.Prev_Edge = null;
        return newNode;
    }

    public void add_to_out(GraphNode node)
    {
        if(this.head==null)
            this.head=node;
        else{
            this.head.Next_Edge=node;
            node.Prev_Edge=this.head;
            this.head=node;
        }
    }
    public void add_to_in(GraphNode node)
    {
        if(this.head==null)
            this.head=node;
        else{
            this.head.Prev_Edge=node;
            node.Next_Edge=this.head;
            this.head=node;
        }
    }
    public void deleteNode(GraphNode node){

        if(node.Prev_Edge!=null) {
            GraphNode node_prev = node.Prev_Edge;
            node_prev.Next_Edge=node.Next_Edge;
        }
        if(node.Next_Edge!=null) {
            GraphNode node_next= node.Next_Edge;
            node_next.Prev_Edge=node.Prev_Edge;
        }

    }

}*/
