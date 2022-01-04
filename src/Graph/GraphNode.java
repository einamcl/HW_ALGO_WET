package Graph;
public class GraphNode{
  public GraphNode Prev = null;
  public GraphNode Next = null;
  public int nodeKey;
  public Doubly_Linked_Edge In_Edge;
  public Doubly_Linked_Edge Out_Edge;
  public GraphNode left_child= null;
  public GraphNode right_sibling= null;
  public GraphNode parent = null;
  public  GraphNode root;
  public String color;
  public int time;




  public GraphNode getLeft_child() {
    return left_child;
  }

  public GraphNode(int nodeKey) {
    this.nodeKey=nodeKey;
  }

  public void setRoot(){
    this.nodeKey = 0;
  }
  public int getOutDegree(){
    GraphNode temp=this.Out_Edge.head_edge;
    int count_out = 0;
    if (temp.nodeKey==0)
      return count_out;
    while(temp.Next!=null)
    {
      count_out++;
    }
    return count_out;
  }

  public int getInDegree(){
    GraphNode temp=this.In_Edge.head_edge;
    int count_in = 0;
    if (temp.nodeKey==0) //Nodekey 0 is nonexisting/null
      return count_in;
    while(temp.Next!=null)
    {
      count_in++;
    }
    return count_in;
  }


  public int getkey(){
    return nodeKey;
  }

}