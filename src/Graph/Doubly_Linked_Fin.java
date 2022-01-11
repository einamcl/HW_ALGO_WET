package Graph;

class Doubly_Linked_Fin {
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
            newNode.setRoot();
        } else {
            head.Prev = newNode;
            newNode.Next = head;
            head = newNode;
        }
        head.Prev = null;
        return newNode;
    }
}