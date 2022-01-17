package Graph;

public class Node<T>{
    Node<T> next;
    Node<T> prev;
    private T data;

    public Node(T data)
    {
        this.data=data;
    }

    public T getData() {
        return this.data;
    }
}
