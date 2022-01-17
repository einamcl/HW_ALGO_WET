package Graph;

public class Queue {
    GraphNode tail = null;
    GraphNode head = null;

    //add a node to the list
    public GraphNode addNode(int nodeKey) {
        //Create a new node
        GraphNode newNode = new GraphNode(nodeKey);
        if(nodeKey==0)
            return head;
        //if list is empty, head and tail points to newNode
        if (head.next == null) {
            head.next = newNode;
            tail = newNode;
        }
        else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        tail.next = null;
        return newNode;
    }
}

