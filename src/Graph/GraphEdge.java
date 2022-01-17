package Graph;

public class GraphEdge {
    public GraphNode From=null;
    public GraphNode To=null;
    private  Node<GraphEdge> myInLocation;
    private  Node<GraphEdge> myOutLocation;

    public GraphEdge(GraphNode From,GraphNode To) {
        this.From = From;
        this.To = To;
    }
    public  Node<GraphEdge> getMyInLocation() {
        return myInLocation;
    }

    public Node<GraphEdge> getMyOutLocation() {
        return myOutLocation;
    }

    public void setMyInLocation( Node<GraphEdge> myInLocation) {
        this.myInLocation = myInLocation;
    }

    public void setMyOutLocation( Node<GraphEdge> myOutLocation) {
        this.myOutLocation = myOutLocation;
    }

}