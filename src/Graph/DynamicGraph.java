package Graph;

public class DynamicGraph {
    Doubly_Linked<GraphNode> graph_nodes;

    public DynamicGraph()
    {
        this.graph_nodes= new Doubly_Linked<GraphNode>();
    }

    public GraphNode insertNode(int nodeKey)
    {
        GraphNode node = new GraphNode(nodeKey);
        node.setLoc(graph_nodes.add_to_tail(node));
        return node;
    }

    public void deleteNode(GraphNode node) {
        if (node.getInDegree()>0||node.getOutDegree()>0) //If the head or tail are not null, then there is outgoing or incoming edge to the node
            return;
        else {
            graph_nodes.deleteNode(node.getMyLocation());
        }

    }

    public GraphEdge insertEdge(GraphNode From, GraphNode To) {

        GraphEdge Edge = new GraphEdge(From, To);
        Edge.setMyOutLocation(From.getOutEdges().add_to_head(Edge));
        Edge.setMyInLocation(To.getInEdges().add_to_head(Edge));
        return Edge;
    }

    public void deleteEdge(GraphEdge edge) {
        GraphNode From_Edge = edge.From;
        GraphNode To_Edge = edge.To;
        From_Edge.Out_Edge.deleteNode(edge.getMyOutLocation());
        To_Edge.In_Edge.deleteNode(edge.getMyInLocation());
    }


    public void enque(Queue Q, GraphNode node) {
        if(Q.head==null)
        {
            Q.head=node;
        }
        Q.tail = node;

    }

    public GraphNode deque(Queue Q) {
        GraphNode temp = Q.head;
        Q.head = temp.next;
        return temp;
    }


    public RootedTree BFS(GraphNode source) {
        Queue queue = new Queue();
        BFS_Init(source, queue);
        while (queue.head != null) {
            GraphNode u = deque(queue);
            Node<GraphEdge> temp=u.Out_Edge.getHead();
            GraphNode current=temp.getData().To;
            u.left_child= current;

            while (temp != null) {
                if (current.color.equals("white")) {
                    current.color = "gray";
                    current.distance = u.distance+1;
                    current.parent = u;
                    enque(queue, current);
                    temp = temp.next;
                    if(temp!=null)
                    current=temp.getData().To;
                }
                else
                    break;
            }
            u.color = "black";
        }
        RootedTree tree = new RootedTree();
        tree.source = source;
        return tree;
    }

    public void BFS_Init(GraphNode source, Queue queue) {
        Node <GraphNode>temp = graph_nodes.getHead();
        while (temp != null) {
            temp.getData().color = "white";
            temp.getData().distance = Integer.MAX_VALUE;
            temp.getData().parent = null;
            temp = temp.next;
        }
        source.color = "gray";
        source.distance = 0;
        source.parent = null;
        enque(queue,source);
    }

    public void DFS_Visit(GraphNode node, int time) {
        node.time = time;
        node.color = "gray";
        GraphNode temp = node.Out_Edge.getTail().getData().To;
        node.left_child = temp;
        while (temp != null) {
            if (temp.color == "white") {
                temp.parent = node;
                DFS_Visit(temp, time+1);
            }
            temp.right_sibling = temp.Next_Edge;
            temp = temp.Next_Edge;
        }
        node.color = "black";
        time += 1;
        node.fin_time = time;

    }

    public RootedTree DFS(GraphNode source) {
        source.color = "white";
        source.parent = null;
        GraphNode temp = source.next;
        while (temp != null) {
            temp.color = "white";
            temp.parent = null;
            temp = temp.next;
        }
        int time = 0;
        temp = source;
        while (temp != null) {
            if (temp.color == "white")
                DFS_Visit(temp, time+1);
            temp = temp.next;
        }
        RootedTree tree = new RootedTree();
        tree.source = source;
        return tree;
    }

    public void transpose(GraphNode source) {
        while (source != null) {
            Doubly_Linked temp = source.Out_Edge;
            source.Out_Edge = source.In_Edge;
            source.In_Edge = temp;
            source = source.next;
        }
    }

    public RootedTree scc(GraphNode source) {
        RootedTree scc_for = new RootedTree();
        scc_for.source = new GraphNode(0);
        RootedTree vertices_second_dfs = DFS(source);
        transpose(vertices_second_dfs.source);
        RootedTree vertices_final_dfs = DFS(vertices_second_dfs.source);
        GraphNode temp = vertices_final_dfs.source;
        GraphNode helper = new GraphNode(source.nodeKey);
        while (temp != null) {
            helper.nodeKey = temp.nodeKey;
            temp = temp.next;
        }
        temp = vertices_final_dfs.source;
        while (temp != null) {
            if (temp.parent == null)
                new GraphEdge(scc_for.source, helper);
            else
                new GraphEdge(temp.parent, helper);

            temp = temp.next;
            helper = helper.next;
        }
        return scc_for;
    }
}

