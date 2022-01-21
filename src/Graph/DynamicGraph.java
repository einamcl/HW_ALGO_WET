package Graph;

public class DynamicGraph {
    Doubly_Linked<GraphNode> graph_nodes;
    static int time;

    public DynamicGraph() {
        this.graph_nodes = new Doubly_Linked<GraphNode>();
    }

    public GraphNode insertNode(int nodeKey) {
        GraphNode node = new GraphNode(nodeKey);
        node.setLoc(graph_nodes.add_to_tail(node));
        return node;
    }

    public void deleteNode(GraphNode node) {
        if (node.getInDegree() > 0 || node.getOutDegree() > 0) //If the head or tail are not null, then there is outgoing or incoming edge to the node
            return;
        else {
            graph_nodes.deleteNode(node.getMyLocation());
        }

    }

    public GraphEdge insertEdge(GraphNode From, GraphNode To) {

        GraphEdge Edge = new GraphEdge(From, To);
        Edge.setMyOutLocation(From.Out_Edge.add_to_head(Edge));
        Edge.setMyInLocation(To.In_Edge.add_to_head(Edge));
        return Edge;
    }

    public void deleteEdge(GraphEdge edge) {
        GraphNode From_Edge = edge.From;
        GraphNode To_Edge = edge.To;
        From_Edge.Out_Edge.deleteNode(edge.getMyOutLocation());
        To_Edge.In_Edge.deleteNode(edge.getMyInLocation());
    }


    public void enque(Doubly_Linked<GraphNode> Q, GraphNode node) {
        Q.add_to_tail(node);
    }

    public void enque_reverse(Doubly_Linked<GraphNode> Q, GraphNode node) {
        Q.add_to_head(node);
    }

    public GraphNode deque(Doubly_Linked<GraphNode> Q) {
        Node<GraphNode> temp = Q.getHead();
        Q.head = temp.next;
        return temp.getData();
    }


    public RootedTree BFS(GraphNode source) {
        Doubly_Linked<GraphNode> queue = new Doubly_Linked<GraphNode>();
        BFS_Init(source, queue);
        GraphNode current = null;
        while (queue.head != null) {
            GraphNode u = deque(queue);
            Node<GraphEdge> temp = u.Out_Edge.getHead();
            if (temp != null) {
                current = temp.getData().To;
                if (current != null && current != u)
                    u.left_child = current;
            }

            while (temp != null) {
                if (current.color.equals("white") || current.color.equals("gray")) {
                    current.color = "gray";
                    current.distance = u.distance + 1;
                    current.parent = u;
                    enque(queue, current);
                    if (temp.next != null)
                        current.right_sibling = temp.next.getData().To;
                    temp = temp.next;
                    if (temp != null)
                        current = temp.getData().To;
                } else
                    break;
            }
            u.color = "black";
        }
        RootedTree tree = new RootedTree();
        tree.source = source;
        return tree;
    }

    public void find_siblings() {
        Node<GraphNode> currentNode = graph_nodes.getHead();

    }

    public void BFS_Init(GraphNode source, Doubly_Linked<GraphNode> queue) {
        Node<GraphNode> temp = graph_nodes.getHead();
        while (temp != null) {
            temp.getData().color = "white";
            temp.getData().distance = Integer.MAX_VALUE;
            temp.getData().parent = null;
            temp = temp.next;
        }
        source.color = "gray";
        source.distance = 0;
        source.parent = null;
        enque(queue, source);
    }

    public void DFS_Visit(GraphNode node, Doubly_Linked<GraphNode> vertices_dfs, boolean Discovery,boolean Reverse) {
        time++;
        if (Discovery) {
            enque(vertices_dfs, node);
        }
        node.time = time;
        node.color = "gray";
        Node<GraphEdge> adj = null;
        if (!Reverse) {
            adj = node.getOutEdges().getHead();
            while (adj != null) {
                GraphNode neighbor = adj.getData().getTo();
                if (neighbor.color == "white") {
                    neighbor.parent = node;
                    DFS_Visit(neighbor, vertices_dfs, Discovery,Reverse);
                }
                adj = adj.next;
            }
        } else {
            adj = node.getInEdges().getHead();
            while (adj != null) {
                GraphNode neighbor = adj.getData().getTo();
                if (neighbor.color == "white") {
                    neighbor.parent = node;
                    DFS_Visit(neighbor, vertices_dfs, Discovery,Reverse);
                }
                adj = adj.next;
            }
        }

        time++;
        node.fin_time = time;
        node.color = "black";
        if (!Discovery) {
            enque(vertices_dfs, node);
        }
    }

    public Doubly_Linked<GraphNode> DFS(Doubly_Linked<GraphNode> vertices, boolean Discovery,boolean Reverse) {
        Node<GraphNode> temp = vertices.getHead();
        Doubly_Linked<GraphNode> vertices_dfs = new Doubly_Linked<>();
        while (temp != null) {
            temp.getData().color = "white";
            temp.getData().parent = null;
            temp = temp.next;
        }
        time = 0;
        temp = vertices.getHead();
        for (int i = 0; i < vertices.getLength(); i++) {
            if (temp.getData().color == "white") {
                DFS_Visit(temp.getData(), vertices_dfs, Discovery,Reverse);
            }
            temp = temp.next;
        }
        return vertices_dfs;
    }

    public void transpose(Doubly_Linked vertices) {
        Node<GraphNode> source = vertices.getHead();
        while (source != null) {
            Doubly_Linked temp = source.getData().Out_Edge;
            source.getData().Out_Edge = source.getData().In_Edge;
            source.getData().In_Edge = temp;
            source = source.next;
        }
    }

    public void Clean_From_Dfs() {
        // clear attributes after DFS run
        Node<GraphNode> currentNode = graph_nodes.getHead();
        for (int i = 0; i < graph_nodes.getLength(); i++) {
            currentNode.getData().color = null;
            currentNode.getData().parent = null;
            currentNode = currentNode.next;
        }
    }



    public RootedTree scc() {


        Doubly_Linked<GraphNode> vertices_second = DFS(this.graph_nodes, false,false);
        transpose(vertices_second);
        vertices_second = DFS(vertices_second, true,true);
        RootedTree scc_forest = new RootedTree();
        scc_forest.source = new GraphNode(0);
        Node<GraphNode> iterator = vertices_second.getHead();
        GraphNode temp = iterator.getData();

        for (int i = 0; i < vertices_second.getLength(); i++) {
            temp= iterator.getData();
            GraphNode Parent = null;
            if (temp.parent == null)
                Parent = scc_forest.source;
            else
                Parent = temp.parent;
            temp.parent = Parent;
            if (Parent.left_child == null)
                Parent.left_child = temp;
            else {
                GraphNode child=Parent.left_child;
                if(child.right_sibling==null)
                    child.setRight_sibling(temp);
                else
                    child.getMostRight().setRight_sibling(temp);
                child.setMostRight(temp);
            }
            iterator=iterator.next;
        }
        return scc_forest;
    }
}

