package Graph;
import java.io.DataOutputStream;
import java.io.IOException;

public class RootedTree {
    public SCC_NODE<GraphNode> source;

    public RootedTree() {
        this.source = null;
    }
    public SCC_NODE<GraphNode> getRoot() {
        return source;
    }
    public void setRoot(SCC_NODE<GraphNode> source) {
        this.source = source;
    }
    public void load_kids( Doubly_Linked<GraphNode> layer, GraphNode currentnode ){ //loads childern into children list
        if(currentnode!=null) {
            currentnode = currentnode.left_child;
            while (currentnode != null) {
                layer.add_to_tail(currentnode);
                currentnode= currentnode.right_sibling;
            }
        }
    }

    // printlayer prints every node in parent_layer,
    // after every node it printed, printlayer loads its children to children_layer
    public void printlayer(DataOutputStream out,  Doubly_Linked<GraphNode> parent_layer, Doubly_Linked<GraphNode> children_layer) throws IOException {
        if(parent_layer.getLength() ==0) return;
        while (parent_layer.getLength() > 1) {
            Node<GraphNode> node = parent_layer.head;
            out.writeBytes(node.getData().getKey() + ",");
            load_kids(children_layer, node.getData());
            parent_layer.deleteNode(node);
        }
        if(parent_layer.getLength() ==1){
            Node<GraphNode> node = parent_layer.head;
            out.writeBytes(node.getData().getKey() + "");
            load_kids(children_layer, node.getData());
            parent_layer.deleteNode(node);
        }
    }

    public void printByLayer(DataOutputStream out) throws IOException {
        if (this.source == null) {
            return;
        }
        Doubly_Linked<GraphNode> parent_layer = new Doubly_Linked<GraphNode>();
        Doubly_Linked<GraphNode> child_layer = new Doubly_Linked<GraphNode>();
        Doubly_Linked temp;
        GraphNode currNode = this.source.getValue();
        parent_layer.add_to_head(currNode);
        while (parent_layer.getLength() + child_layer.getLength() > 0) {
            printlayer(out, parent_layer, child_layer);
            out.writeBytes("\n");
            temp = parent_layer;
            parent_layer = child_layer;
            child_layer = temp;
        }
    }


    public void preorderPrint(DataOutputStream out) throws IOException {
        out.writeBytes("preorder");
        int parent_sibling = 1;
        int child= 0;
        GraphNode x = this.source.getValue();
        int from = parent_sibling;
        while(x!=null){
            if(from == parent_sibling){
                if(x.right_sibling== null && x.parent== null){
                    out.writeBytes(x.getKey()+"");
                }
                else
                {
                    out.writeBytes(x.getKey()+",");
                }
                if(x.left_child !=null){
                    x= x.left_child;
                }
                else {
                    if (x.right_sibling != null) {
                        x = x.right_sibling;
                    }
                    else {
                        from = child;
                        x= x.parent;
                    }
                }
            }
            else{
                if(x.right_sibling!=null){
                    from= parent_sibling;
                    x= x.right_sibling;
                }
                else {
                    x= x.parent;
                }
            }
        }
    }
}

class SCC_NODE<T> {
    private T value;
    private SCC_NODE<T> parent;
    private SCC_NODE<T> left_child;
    private SCC_NODE<T> right_sibling;
    private SCC_NODE<T> mostRight;

    public SCC_NODE(T node) {
        this.value = node;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public SCC_NODE<T> getParent() {
        return parent;
    }

    public void setParent(SCC_NODE<T> parent) {
        this.parent = parent;
    }

    public SCC_NODE<T> getLeft_child() {
        return left_child;
    }

    public void setLeft_child(SCC_NODE<T> left_child) {
        this.left_child = left_child;
    }

    public SCC_NODE<T> getRight_sibling() {
        return right_sibling;
    }

    public void setRight_sibling(SCC_NODE<T> right_sibling) {
        this.right_sibling = right_sibling;
    }

    public SCC_NODE<T> getMostRight() {
        return mostRight;
    }

    public void setMostRight(SCC_NODE<T> mostRight) {
        this.mostRight = mostRight;
    }


}