package Graph;

class Doubly_Linked {
    //A node class for doubly linked list

    //Initially, head and tail is set to null
    GraphNode head, tail = null;

    //add a node to the list
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
            //add newNode to the end of list. tail->next set to newNode
            tail.Next = newNode;
            //newNode->previous set to tail
            newNode.Prev = tail;
            //            //newNode becomes new tail
            tail = newNode;
            //tail's next point to null
        }
        tail.Next = null;
        return newNode;
    }
}