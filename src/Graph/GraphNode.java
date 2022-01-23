package Graph;

public class GraphNode{
  public GraphNode prev = null;
  public GraphNode next = null;
  public int nodeKey;
  public Doubly_Linked<GraphEdge> In_Edge;
  public Doubly_Linked<GraphEdge> Out_Edge;
  public GraphNode left_child= null;
  public GraphNode right_sibling= null;
  public GraphNode parent = null;
  public String color;
  public int time;
  public int fin_time;
  public int distance;
  public GraphNode Next_Edge=null;
  public GraphNode Prev_Edge=null;
  private SCC_NODE<GraphNode> myLocation;
  public GraphNode mostRight;
  private Node<GraphNode> node_loc;

  public Node<GraphNode> get_node() {
    return node_loc;
  }

  public void set_node(Node<GraphNode> myLocation) {
    this.node_loc = node_loc;
  }
  public GraphNode getLeft_child() {
    return left_child;
  }

  public GraphNode(int nodekey) {
    this.nodeKey=nodekey;
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
  public Doubly_Linked<GraphEdge> getInEdges() {
    return In_Edge;
  }
  public Doubly_Linked<GraphEdge> getOutEdges() {
    return Out_Edge;
  }
  public void setLoc(SCC_NODE<GraphNode> myLocation) {
    this.myLocation = myLocation;
  }
  public SCC_NODE<GraphNode> getLoc() {
    return myLocation;
  }
  public GraphNode getMostRight() {
    return mostRight;
  }
  public GraphNode getRight_sibling() {
    return right_sibling;
  }

  public void setRight_sibling(GraphNode right_sibling) {
    this.right_sibling = right_sibling;
  }
  public void setMostRight(GraphNode mostRight) {
    this.mostRight = mostRight;
  }
  public int getKey(){
    return nodeKey;
  }

  @Override
  public String toString(){
    return (getKey()+"");
  }
}