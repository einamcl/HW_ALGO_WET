package Graph;
import java.io.DataOutputStream;
import java.io.IOException;

public class RootedTree {
    public GraphNode source;

    public RootedTree() {
        this.source = null;
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
            out.writeBytes(node.getData().getkey() + ",");
            load_kids(children_layer, node.getData());
            parent_layer.deleteNode(node);
        }
        if(parent_layer.getLength() ==1){
            Node<GraphNode> node = parent_layer.head;
            out.writeBytes(node.getData().getkey() + "");
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
        GraphNode currNode = this.source;
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
        GraphNode x = this.source;
        int from = parent_sibling;
        while(x!=null){
            if(from == parent_sibling){
                if(x.right_sibling== null && x.parent== null){
                    out.writeBytes(x.getkey()+"");
                }
                else
                {
                    out.writeBytes(x.getkey()+",");
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

//    public GraphNode printRightSiblings(DataOutputStream out,GraphNode currNode) throws IOException {
//        if(currNode == null) return null;
//        GraphNode temp = null;
//        out.writeBytes(currNode.getKey()+ "");
//        currNode= currNode.right_sibling;
//        while (currNode != null){
//            out.writeBytes(","+ currNode.getKey() + "");
//            temp= currNode;
//            currNode = currNode.right_sibling;
//        }
//        return temp;
//    }