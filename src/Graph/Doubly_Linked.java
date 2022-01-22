package Graph;

class Doubly_Linked<T> {
    //A node class for doubly linked list
    //Initially, head and tail is set ro null
    public Node<T> tail;
    public Node<T> head;
    int length;

    public Doubly_Linked()
    {
        this.length=0;
    }
    //add a node to the list
    public Node<T> add_to_tail(T data) {
        //Create a new node

        if (head== null) {
            head= new Node<T>(data);
            tail=head;
            this.length++;
            return head;
        }
        tail.next=new Node<T>(data);
        tail.next.prev=tail; // set new tails prev pointer
        tail=tail.next;
        this.length++;
        return tail;
    }
    public Node<T> add_to_head(T data) {
        //Create a new node
        Node<T> Head = new Node<T>(data);
        Head.next=head;
        if (head!=null) {
            head.prev = Head;
        }
        head=Head;
        this.length++;
        return head;
    }
    public void deleteNode(Node<T> node) {

        Node<T> prev=node.prev;

        Node<T> next=node.next;
        if(prev!=null)
            node.prev.next = next;
        if(next!=null)
        node.next.prev = prev;

        if(node==head)
            head=node.next;
        if(node==tail)
            tail=node.prev;
        this.length--;
    }
    public int getLength()
    {
        return this.length;
    }

    public Node<T> getHead() {
        return head;
    }

    public Node<T> getTail() {
        return tail;
    }
}