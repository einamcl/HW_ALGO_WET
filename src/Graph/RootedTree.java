package Graph;
import java.io.DataOutputStream;
import java.io.IOException;

public class RootedTree {
    public GraphNode source;

    public RootedTree() {
        this.source = null;
    }

//
//    public void printByLayerGOOD(DataOutputStream out) throws IOException {
//        if (this.source == null) {
//            return;
//        }
//        out.writeBytes(this.source.getkey() + "\n");
//        GraphNode currNode = this.source.left_child;                // use dataoutputstream
//        GraphNode branch = currNode.parent.right_sibling;                // use dataoutputstream
//        while (currNode != null) {
//            printlayer(out, currNode);
//            if(branch != null && branch.left_child !=null){
//                printlayer(out,branch.left_child);
//                branch = branch.parent.right_sibling;
//            }
//            else{
//                out.writeBytes("\n");
//                currNode = currNode.left_child;
//            }
//        }
//    }


//    public void printlayerGOOD(DataOutputStream out, GraphNode currNode) throws IOException {
//        GraphNode layer = currNode;
//        while (currNode != null) {
//            layer = printRightSiblings(out, layer);
//            if (layer != null && layer.parent != null && layer.parent.right_sibling != null && layer.parent.right_sibling.left_child != null) {
//                out.writeBytes(",");
//                layer = layer.parent.right_sibling.left_child;
//                if (layer == null)
//                    break;
//            } else break;
//        }
//    }

//    public GraphNode printRightSiblings(DataOutputStream out,GraphNode currNode) throws IOException {
//        if(currNode == null) return null;
//        GraphNode temp = null;
//        out.writeBytes(currNode.getkey()+ "");
//        currNode= currNode.right_sibling;
//        while (currNode != null){
//            out.writeBytes(","+ currNode.getkey() + "");
//            temp= currNode;
//            currNode = currNode.right_sibling;
//        }
//        return temp;
//    }

    public void printByLayer(DataOutputStream out) throws IOException {
        if (this.source == null) {
            return;
        }
        out.writeBytes(this.source.getkey() + "\n");
        GraphNode currNode = this.source.left_child;                // use dataoutputstream
        while (currNode != null) {
            printlayer(out, currNode);
            out.writeBytes("\n");
            currNode = currNode.left_child;
        }
    }

    public void printlayer(DataOutputStream out, GraphNode currNode) throws IOException {
        GraphNode layer = currNode;
        while (layer != null) {
            layer = printRightSiblings(out, layer);
            if (layer != null && layer.parent != null && layer.parent.right_sibling != null && layer.parent.right_sibling.left_child != null) {
                out.writeBytes(",");
                layer =layer.parent.right_sibling.left_child;
            } else break;
        }
    }

    public GraphNode printRightSiblings(DataOutputStream out,GraphNode currNode) throws IOException {
        if(currNode == null) return null;
        GraphNode temp = null;
        out.writeBytes(currNode.getkey()+ "");
        currNode= currNode.right_sibling;
        while (currNode != null){
            out.writeBytes(","+ currNode.getkey() + "");
            temp= currNode;
            currNode = currNode.right_sibling;
        }
        return temp;
    }

    public void preorderPrint_helper(DataOutputStream out, GraphNode currNode) throws IOException {
        if (currNode == null) { return;}
        out.writeBytes("," + currNode.getkey() + "");
        preorderPrint_helper(out, currNode.left_child);
        preorderPrint_helper(out, currNode.right_sibling);

    }

    public void preorderPrint(DataOutputStream out,GraphNode currNode ) throws IOException{
        out.writeBytes(currNode.getkey()+ "");
        preorderPrint_helper(out,currNode.left_child);
        out.writeBytes( "\n\n");
        }
}


