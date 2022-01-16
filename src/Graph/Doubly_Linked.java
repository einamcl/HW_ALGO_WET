package Graph;

class Doubly_Linked {
    //A node class for doubly linked list

    //Initially, head and tail is set to null
    GraphNode tail = null;
    GraphNode head = new GraphNode(0);

    //add a node to the list
    public GraphNode addNode(int nodeKey) {
        //Create a new node
        GraphNode newNode = new GraphNode(nodeKey);
        if(nodeKey==0)
            return head;
        //if list is empty, head and tail points to newNode
        if (head.Next == null) {
            head.Next = newNode;
            tail = newNode;
        }
        else {
            tail.Next = newNode;
            newNode.Prev = tail;
            tail = newNode;
        }
        tail.Next = null;
        return newNode;
    }
}