package Graph;
import java.io.DataOutputStream;
import java.io.IOException;

public class RootedTree {
    public GraphNode source;


    public RootedTree(){
        this.source=null;
    }


    public void printByLayer(DataOutputStream out) throws IOException {
        if (this.source == null) {return;}
        out.writeBytes(this.source.getkey() + "\n");
        GraphNode currNode = this.source.left_child;                // use dataoutputstream
        while (currNode != null){
            printRightSiblings(out,currNode);
            out.writeBytes("\n");
            currNode = currNode.left_child;
        }
    }
    public void printRightSiblings(DataOutputStream out,GraphNode currNode) throws IOException {
        out.writeBytes(currNode.getkey()+ "");
        GraphNode right = currNode.right_sibling;
        while (right != null){
            out.writeBytes(","+ right.getkey() + "");
            right = right.right_sibling;
        }
    }
    public void preorderPrint(DataOutputStream out) throws IOException{
        if(this.source==null)return;
        else{
            out.writeBytes(source.getkey()+ "");
           this.source=source.left_child;
           preorderPrint(out);
            while(source.right_sibling!=null){
                this.source=source.right_sibling;
                preorderPrint(out);
                this.source=source.parent;
            }
        }
    }



}
