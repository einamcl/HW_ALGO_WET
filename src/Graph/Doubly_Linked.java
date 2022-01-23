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
        if(this.tail== null){
            tail= new Node<T>(data);
            head=tail;
            this.length++;
            return tail;
        }
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
        if(node == null)
            return;
        if (node.prev == null && node.next == null) {
            node = null;
            this.length--;
            return;
        }
        if (node.next!=null && node.prev!=null) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
        }
        else {
            if (node.getData() == head.getData()) {
                Node<T>temp=head.next;
                temp.prev=null;
                this.head=temp;
            }
            if (node.getData() == tail.getData()) {
                Node<T>temp=tail.prev;
                temp.next=null;
                this.tail=temp;
            }
        }
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