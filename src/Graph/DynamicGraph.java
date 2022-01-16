package Graph;

public class DynamicGraph {
    public Doubly_Linked Node_List = new Doubly_Linked();
    public Doubly_Linked_Fin Fin_Times = new Doubly_Linked_Fin();

    public DynamicGraph() {

    }

    public GraphNode insertNode(int nodeKey) {
        return (Node_List.addNode(nodeKey));
    }

    public void deleteNode(GraphNode node) {
        if (node.In_Edge.head != null || node.Out_Edge.tail != null) //If the head or tail are not null, then there is outgoing or incoming edge to the node
            return;
        else {
            if (node.Prev != null) {
                GraphNode node_prev = node.Prev;
                node_prev.Next = node.Next;
            }
            if (node.Next != null) {
                GraphNode node_next = node.Next;
                node_next.Prev = node.Prev;
            }

        }

    }

    public GraphEdge insertEdge(GraphNode From, GraphNode To) {
        if(From.Out_Edge==null){
            From.Out_Edge = new Doubly_Linked_Edge();
        }
        if(To.In_Edge==null){
            To.In_Edge = new Doubly_Linked_Edge();
        }
        From.Out_Edge.addNode(To);
        To.In_Edge.addNode(From);
        GraphEdge Edge = new GraphEdge(From, To);
        return Edge;
    }

    public void deleteEdge(GraphEdge edge) {
        GraphNode From_Edge = edge.From;
        GraphNode To_Edge = edge.To;
        From_Edge.Out_Edge.deleteNode(To_Edge);
        To_Edge.In_Edge.deleteNode(From_Edge);
    }


    public void enque(Doubly_Linked Q, GraphNode node) {
        Q.tail = node;
        node.Next = null;
    }

    public GraphNode deque(Doubly_Linked Q) {
        GraphNode temp = Q.head;
        Q.head = temp.Next;
        return temp;
    }


    public RootedTree BFS(GraphNode source) {
        Doubly_Linked queue = new Doubly_Linked();
        BFS_Init(source, queue);
        while (queue.head != null) {
            GraphNode u = deque(queue);
            GraphNode temp = u.Out_Edge.head;
            u.left_child = temp;
            while (temp != null) {
                if (temp.color.equals("white")) {
                    temp.color = "gray";
                    temp.time = u.time + 1;
                    temp.parent = u;
                    enque(queue, temp);
                    temp.right_sibling = temp.Next;
                    temp = temp.Next_Edge;
                }
                u.color = "black";
            }
        }
        RootedTree tree = new RootedTree();
        tree.source = source;
        return tree;
    }

    public void BFS_Init(GraphNode source, Doubly_Linked Queue) {
        GraphNode temp = source.Next;
        while (temp != null) {
            temp.color = "white";
            temp.time = Integer.MAX_VALUE;
            temp.parent = null;
            temp = temp.Next;
        }
        source.color = "gray";
        source.time = 0;
        source.parent = null;
        Queue.head = null;
        Queue.addNode(0);
    }

    public void DFS_Visit(GraphNode node, int time) {
        time += 1;
        node.time = time;
        node.color = "gray";
        GraphNode temp = node.Out_Edge.head;
        node.left_child = temp;
        while (temp != null) {
            if (temp.color == "white") {
                temp.parent = node;
                DFS_Visit(temp, time);
            }
            temp.right_sibling = temp.Next_Edge;
            temp = temp.Next_Edge;
        }
        node.color = "black";
        time += 1;
        node.fin_time = time;
        Fin_Times.addNode(node.nodeKey);

    }

    public RootedTree DFS(GraphNode source) {
        source.color = "white";
        source.parent = null;
        GraphNode temp = source.Next;
        while (temp != null) {
            temp.color = "white";
            temp.parent = null;
            temp = temp.Next;
        }
        int time = 0;
        temp = source;
        while (temp != null) {
            if (temp.color == "white")
                DFS_Visit(temp, time);
            temp = temp.Next;
        }
        RootedTree tree = new RootedTree();
        tree.source = source;
        return tree;
    }

    public void transpose(GraphNode source) {
        while (source != null) {
            Doubly_Linked_Edge temp = source.Out_Edge;
            source.Out_Edge = source.In_Edge;
            source.In_Edge = temp;
            source = source.Next;
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
            temp = temp.Next;
        }
        temp = vertices_final_dfs.source;
        while (temp != null) {
            if (temp.parent == null)
                new GraphEdge(scc_for.source, helper);
            else
                new GraphEdge(temp.parent, helper);

            temp = temp.Next;
            helper = helper.Next;
        }
        return scc_for;
    }
}

