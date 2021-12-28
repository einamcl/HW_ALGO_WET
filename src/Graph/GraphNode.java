package Graph;

public class GraphNode{
  public GraphNode Prev = null;
  public GraphNode Next = null;
  public int nodeKey;
  public Doubly_Linked_Edge In_Edge;
  public Doubly_Linked_Edge Out_Edge;

  public GraphNode(int nodeKey) {
    this.nodeKey=nodeKey;
  }


  public int getOutDegree(){
  }

  public int getInDegree(){
  }

  public int getkey(){
    return nodeKey;
  }

}