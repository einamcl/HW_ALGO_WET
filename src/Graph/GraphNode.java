package Graph;

public class GraphNode{
  private int key;
  private GraphNode parent = null;
  private GraphNode child = null;

  public GraphNode(int key){
    this.key = key;
  }


  public int getOutDegree(){}

  public int getInDegree(){}

  public int getkey(){
    return this.key;
  }

}
