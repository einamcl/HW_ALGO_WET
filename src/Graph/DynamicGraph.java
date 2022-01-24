package Graph;

public class DynamicGraph {
    Doubly_Linked<GraphNode> graph_nodes;
    static int time;

    public DynamicGraph() {
        this.graph_nodes = new Doubly_Linked<GraphNode>();
    }

    public GraphNode insertNode(int nodeKey) {
        GraphNode node = new GraphNode(nodeKey);
        node.set_node(graph_nodes.add_to_head(node));
        return node;
    }

    public void deleteNode(GraphNode node) {
        if (node.getInDegree() > 0 || node.getOutDegree() > 0) //If the head or tail are not null, then there is outgoing or incoming edge to the node
            return;
        else {
            graph_nodes.deleteNode(node.get_node());
        }

    }

    public GraphEdge insertEdge(GraphNode From, GraphNode To) {

        GraphEdge Edge = new GraphEdge(From, To);
        Edge.setMyInLocation(From.getOutEdges().add_to_head(Edge));
        Edge.setMyOutLocation(To.getInEdges().add_to_head(Edge));
        return Edge;
    }
    public void deleteEdge(GraphEdge edge) {

        edge.getTo().getInEdges().deleteNode(edge.getMyOutLocation());
        edge.getFrom().getOutEdges().deleteNode(edge.getMyInLocation());
    }


    public void enque(Doubly_Linked<GraphNode> Q, GraphNode node) {
        Q.add_to_head(node);
    }

    public GraphNode deque(Doubly_Linked<GraphNode> Q) {
        Node<GraphNode> temp = Q.getHead();
        Q.head = temp.next;
        return temp.getData();
    }
    public void Build_Tree(RootedTree tree){
        SCC_NODE<GraphNode>root= tree.getRoot();
        GraphNode temp=root.getValue();
        SCC_NODE<GraphNode> Parent = null;
        while(temp!=null)
        {
            SCC_NODE<GraphNode> currentSCC=new SCC_NODE<>(temp);
            temp.setLoc(currentSCC);
        }
            temp=root.getValue();
        while(temp!=null)
        {
            SCC_NODE<GraphNode> New_SCC= temp.getLoc();
            if(temp.parent!=null)
                Parent=temp.parent.getLoc();
            New_SCC.setParent(Parent);
            

        }
    }

    public RootedTree bfs(GraphNode source) {
        Doubly_Linked<GraphNode> queue = new Doubly_Linked<GraphNode>();
        BFS_Init(source, queue);
        GraphNode current = null;
        while (queue.head != null) {
            GraphNode u = deque(queue);
            Node<GraphEdge> temp = u.getOutEdges().getHead();
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
        SCC_NODE<GraphNode> root = new SCC_NODE<>(source);
        tree.setRoot(root);
        Build_Tree(tree);
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

    public void DFS_Visit(GraphNode node, Doubly_Linked<GraphNode> vertices_dfs, boolean Discovery) {
        time++;
        if (Discovery) {
            enque(vertices_dfs, node);
        }
        node.time = time;
        node.color = "gray";
        Node<GraphEdge> adj = null;

            adj = node.getOutEdges().getHead();
            while (adj != null) {
                GraphNode neighbor = adj.getData().getTo();
                if (neighbor.color == "white") {
                    neighbor.parent = node;
                    DFS_Visit(neighbor, vertices_dfs, Discovery);
                }
                adj = adj.next;
            }
        time++;
        node.fin_time = time;
        node.color = "black";
        if (!Discovery) {
            enque(vertices_dfs, node);
        }
    }

    public Doubly_Linked<GraphNode> DFS(Doubly_Linked<GraphNode> vertices, boolean Discovery) {
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
                DFS_Visit(temp.getData(), vertices_dfs, Discovery);
            }

            temp = temp.next;
        }
        return vertices_dfs;
    }

    public void transpose(Doubly_Linked vertices) {
        Node<GraphNode> source = vertices.getHead();
        Doubly_Linked<GraphEdge>temp=null;
        while (source != null) {
            temp = source.getData().getOutEdges();
            source.getData().Out_Edge = source.getData().getInEdges();
            source.getData().In_Edge = temp;
            Node<GraphEdge> First= temp.getHead();
            for(int i=0;i<temp.getLength();i++)
            {
                if(First!=null&&First.getData()!=null) {
                    GraphNode from = First.getData().From;
                    GraphNode to = First.getData().To;
                    First.getData().To = from;
                    First.getData().From = to;
                    First = First.next;
                }
            }
            source=source.next;
        }
    }

    public void Clean_From_SCC() {
        // clear attributes after SCC run
        Node<GraphNode> currentNode = graph_nodes.getHead();

        for (int i = 0; i < graph_nodes.getLength(); i++) {
            if(currentNode.getData().getLoc()!=null) {
                if (currentNode.getData().getLoc().getRight_sibling() != null)
                    currentNode.getData().getLoc().setRight_sibling(null);
                if (currentNode.getData().getLoc().getMostRight() != null)
                    currentNode.getData().getLoc().setMostRight(null);
                if (currentNode.getData().getLoc().getParent() != null)
                    currentNode.getData().getLoc().setParent(null);
                if (currentNode.getData().getLoc().getLeft_child() != null)
                    currentNode.getData().getLoc().setLeft_child(null);
                currentNode = currentNode.next;
            }
            else
                break;
        }
    }



    public RootedTree scc() {
        Doubly_Linked<GraphNode> vertices_second = DFS(this.graph_nodes, false);
        transpose(vertices_second);
        vertices_second = DFS(vertices_second, true);
        RootedTree scc_forest = new RootedTree();
        GraphNode source = new GraphNode(0);
        Node<GraphNode> iterator = vertices_second.getTail();
        SCC_NODE<GraphNode> root = new SCC_NODE<>(source);
        scc_forest.setRoot(root);
        GraphNode temp = iterator.getData();
        Doubly_Linked<GraphNode> Orphans= new Doubly_Linked<GraphNode>();
        while(iterator!=null)
        {

            temp= iterator.getData();
            if(temp.parent==null){
            enque(Orphans,temp);
            }
            iterator=iterator.prev;
        }
        iterator= vertices_second.getTail();
        int j=0;
        for (int i = 0; i < vertices_second.getLength(); i++) {
            temp= iterator.getData();
            SCC_NODE<GraphNode> currentSCC=new SCC_NODE<>(temp);
            temp.setLoc(currentSCC);
            SCC_NODE<GraphNode> Parent = null;
            if(temp.getKey()==402734)
               j++;
            if (temp.parent == null)
                Parent = root;
            else
                Parent = temp.parent.getLoc();
            currentSCC.setParent(Parent);
                if (Parent.getLeft_child() == null)
                    Parent.setLeft_child(currentSCC);
                else {
                    SCC_NODE<GraphNode> child = Parent.getLeft_child();
                    if (child.getRight_sibling() == null)
                        child.setRight_sibling(currentSCC);
                    else
                        child.getMostRight().setRight_sibling(currentSCC);
                    child.setMostRight(currentSCC);
                }
            iterator=iterator.prev;
        }
        this.transpose(vertices_second);
        return scc_forest;
    }
}

