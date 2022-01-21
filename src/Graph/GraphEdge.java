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

    public GraphNode getTo()
    {
        return this.To;
    }
    public GraphNode getFrom()
    {
        return this.From;
    }
    public void setTo(GraphNode To)
    {
        this.To=To;
    }
    public void setFrom(GraphNode From)
    {
        this.From=From;
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