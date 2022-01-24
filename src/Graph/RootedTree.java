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
    public void load_kids(  Doubly_Linked<SCC_NODE> layer, SCC_NODE currentnode ){ //loads childern into children list
        if(currentnode!=null) {
            currentnode = currentnode.getLeft_child();
            while (currentnode != null) {
                layer.add_to_tail(currentnode);
                currentnode= currentnode.getRight_sibling();

            }
        }
    }

    // printlayer prints every node in parent_layer,
    // after every node it printed, printlayer loads its children to children_layer
    public void printlayer(DataOutputStream out, Doubly_Linked<SCC_NODE> parent_layer, Doubly_Linked<SCC_NODE> children_layer) throws IOException {

        if(parent_layer.getLength() ==0) return;
        while (parent_layer.getLength() > 1) {
            Node <SCC_NODE> node = parent_layer.head;
            out.writeBytes(node.getData().getValue() + ",");
            load_kids(children_layer, node.getData());
            parent_layer.deleteNode(node);
        }
        if(parent_layer.getLength() ==1){
            Node<SCC_NODE> node = parent_layer.getHead();
            out.writeBytes(node.getData().getValue() + "");
            load_kids(children_layer, node.getData());
            parent_layer.deleteNode(node);
        }
    }

    public void printByLayer(DataOutputStream out) throws IOException {
        if (this.source == null) {
            return;
        }
        Doubly_Linked<SCC_NODE> parent_layer = new Doubly_Linked<>();
        Doubly_Linked<SCC_NODE> child_layer = new Doubly_Linked<>();
        Doubly_Linked temp;
        SCC_NODE <GraphNode> currNode = this.source;
        parent_layer.add_to_head(currNode);
        while (parent_layer.getLength() + child_layer.getLength() > 0) {
            printlayer(out, parent_layer, child_layer);
            if(child_layer.getLength()==0)
                break;
            out.writeBytes("\n");
            temp = parent_layer;
            parent_layer = child_layer;
            child_layer = temp;
        }

    }


    public void preorderPrint(DataOutputStream out) throws IOException {
        int parent_sibling = 1;
        int child= 0;
        SCC_NODE <GraphNode> x = this.source;
        int from = parent_sibling;
        while(x!=null){
            if(from == parent_sibling){
                if(x.getParent()== null){
                    out.writeBytes(x.getValue()+"");
                }
                else
                {
                    out.writeBytes(","+x.getValue());
                }
                if(x.getLeft_child() !=null){
                    x= x.getLeft_child();
                }
                else {
                    if (x.getRight_sibling() != null) {
                        x = x.getRight_sibling();
                    }
                    else {
                        from = child;
                        x= x.getParent();
                    }
                }
            }
            else{
                if(x.getRight_sibling()!=null){
                    from= parent_sibling;
                    x= x.getRight_sibling();
                }
                else {
                    x= x.getParent();
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