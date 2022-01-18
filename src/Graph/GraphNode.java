package Graph;

import java.io.DataOutputStream;
import java.io.IOException;

public class GraphNode{
  public GraphNode prev = null;
  public GraphNode next = null;
  public int nodeKey;
  public Doubly_Linked<GraphEdge> In_Edge;
  public Doubly_Linked<GraphEdge> Out_Edge;
  public GraphNode left_child= null;
  public GraphNode right_sibling= null;
  public GraphNode parent = null;
  public  GraphNode root;
  public String color;
  public int time;
  public int fin_time;
  public int distance;
  public GraphNode Next_Edge=null;
  public GraphNode Prev_Edge=null;
  private Node<GraphNode> myLocation;




  public GraphNode getLeft_child() {
    return left_child;
  }

  public GraphNode(int nodeKey) {
    this.nodeKey=nodeKey;
    this.Out_Edge=new Doubly_Linked<GraphEdge>();
    this.In_Edge= new Doubly_Linked<GraphEdge>();
  }
  public GraphNode(GraphNode node)
  {
    this.nodeKey=node.nodeKey;

  }

  public void setRoot(){
    this.nodeKey = 0;
  }
  public int getOutDegree(){
    return Out_Edge.getLength();
  }

  public int getInDegree(){
     return In_Edge.getLength();
  }
  public Node<GraphNode> getMyLocation() {
    return myLocation;
  }
  public Doubly_Linked<GraphEdge> getInEdges() {
    return In_Edge;
  }
  public Doubly_Linked<GraphEdge> getOutEdges() {
    return Out_Edge;
  }

  public void setLoc(Node<GraphNode> myLocation) {
    this.myLocation = myLocation;
  }

  public int getkey(){
    return nodeKey;
  }

}