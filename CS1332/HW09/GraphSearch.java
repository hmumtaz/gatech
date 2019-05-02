import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Your implementations of various graph search algorithms.
 *
 * @author Hussain Mumtaz
 * @version 1.0
 */
public class GraphSearch {

    /**
     * Searches the Graph passed in as an adjacency list(adjList) to find if a
     * path exists from the start node to the goal node using General Graph
     * Search.
     *
     * Assume the adjacency list contains adjacent nodes of each node in the
     * order they should be added to the Structure. If there are no adjacent
     * nodes, then an empty list is present.
     *
     * The structure(struct) passed in is an empty structure that may behave as
     * a Stack or Queue and this function should execute DFS or BFS on the
     * graph, respectively.
     *
     * DO NOT use {@code instanceof} to determine the type of the Structure!
     *
     * @param start the object representing the node you are starting at.
     * @param struct the Structure you should use to implement the search.
     * @param adjList the adjacency list that represents the graph we are
     *        searching.
     * @param goal the object representing the node we are trying to reach.
     * @param <T> the data type representing the nodes in the graph.
     * @return true if path exists, false otherwise.
     */
    private static <T> boolean generalGraphSearch(T start, Structure<T> struct,
            Map<T, List<T>> adjList, T goal) {
        ArrayList<T> visited = new ArrayList<>();
        struct.add(start);
        visited.add(start);
        while (!(struct.isEmpty()) && !(visited.contains(goal))) {
            T node = struct.remove();
            for (T neighbor: adjList.get(node)) {
                if (!(visited.contains(neighbor))) {
                    visited.add(neighbor);
                    struct.add(neighbor);
                }
            }
        }
        return visited.contains(goal);
    }

    /**
     * Searches the Graph passed in as an adjacency list(adjList) to find if a
     * path exists from the start node to the goal node using Breadth First
     * Search.
     *
     * Assume the adjacency list contains adjacent nodes of each node in the
     * order they should be added to the Structure. If there are no adjacent
     * nodes, then an empty list is present.
     *
     * This method should be written in one line.
     *
     * @throws IllegalArgumentException if any input is null, or if
     * {@code start} or {@code goal} doesn't exist in the graph
     * @param start the object representing the node you are starting at.
     * @param adjList the adjacency list that represents the graph we are
     *        searching.
     * @param goal the object representing the node we are trying to reach.
     * @param <T> the data type representing the nodes in the graph.
     * @return true if path exists false otherwise
     */
    public static <T> boolean breadthFirstSearch(T start,
            Map<T, List<T>> adjList, T goal) throws IllegalArgumentException {
        if (start == null || adjList == null || goal == null) {
            throw new IllegalArgumentException("One or more of the inputs in"
                    + " null");
        } else if (!(adjList.containsKey(start)) || !(adjList
                .containsKey(goal))) {
            throw new IllegalArgumentException("The start or goal vetrex is"
                    + " not contained in the graph");
        } else {
            StructureQueue<T> queue = new StructureQueue<>();
            return generalGraphSearch(start, queue, adjList, goal);
        }
    }

    /**
     * Searches the Graph passed in as an adjacency list(adjList) to find if a
     * path exists from the start node to the goal node using Depth First
     * Search.
     *
     * Assume the adjacency list contains adjacent nodes of each node in the
     * order they should be added to the Structure. If there are no adjacent
     * nodes, then an empty list is present.
     *
     * This method should be written in one line.
     *
     * @throws IllegalArgumentException if any input is null, or if
     * {@code start} or {@code goal} doesn't exist in the graph
     * @param start the object representing the node you are starting at.
     * @param adjList the adjacency list that represents the graph we are
     *        searching.
     * @param goal the object representing the node we are trying to reach.
     * @param <T> the data type representing the nodes in the graph.
     * @return true if path exists false otherwise
     */
    public static <T> boolean depthFirstSearch(T start,
            Map<T, List<T>> adjList, T goal) throws IllegalArgumentException {
        if (start == null || adjList == null || goal == null) {
            throw new IllegalArgumentException("One or more of the inputs in"
                    + " null");
        } else if (!(adjList.containsKey(start)) || !(adjList
                .containsKey(goal))) {
            throw new IllegalArgumentException("The start or goal vetrex is"
                    + " not contained in the graph");
        } else {
            StructureStack<T> stack = new StructureStack<>();
            return generalGraphSearch(start, stack, adjList, goal);
        }
    }

    /**
     * Find the shortest distance between the start node and the goal node
     * given a weighted graph in the form of an adjacency list where the
     * edges only have positive weights. If there are no adjacent nodes for
     * a node, then an empty list is present.
     *
     * Return the aforementioned shortest distance if there exists a path
     * between the start and goal, -1 otherwise.
     *
     * There are guaranteed to be no negative edge weights in the graph.
     *
     * You may import/use {@code java.util.PriorityQueue}.
     *
     * @throws IllegalArgumentException if any input is null, or if
     * {@code start} or {@code goal} doesn't exist in the graph
     * @param start the object representing the node you are starting at.
     * @param adjList the adjacency list that represents the graph we are
     *        searching.
     * @param goal the object representing the node we are trying to reach.
     * @param <T> the data type representing the nodes in the graph.
     * @return the shortest distance between the start and the goal node
     */
    public static <T> int dijkstraShortestPathAlgorithm(T start,
            Map<T, List<VertexDistancePair<T>>> adjList, T goal)
            throws IllegalArgumentException {
        if (start == null || adjList == null || goal == null) {
            throw new IllegalArgumentException("One or more of the inputs in"
                    + " null");
        } else if (!(adjList.containsKey(start)) || !(adjList
                .containsKey(goal))) {
            throw new IllegalArgumentException("The start or goal vertex is"
                    + " not contained in the graph");
        } else {
            //map containing shortest distances
            Map<T, Integer> dMap = new HashMap<>();
            //priority queue of Vertex Distance Pairs
            PriorityQueue<VertexDistancePair<T>> pQueue = new PriorityQueue<>();
            //array list of visited nodes
            ArrayList<T> visited = new ArrayList<>();

            for (T vertex: adjList.keySet()) {
                dMap.put(vertex, Integer.MAX_VALUE);
            }

            pQueue.add(new VertexDistancePair<>(start, 0));
            int dist = 0;
            while (!pQueue.isEmpty() && !(visited.contains(goal))) {
                T node = pQueue.peek().getVertex();
                if (!(visited.contains(node))) {
                    visited.add(node);
                    dist += pQueue.poll().getDistance();
                    dMap.replace(node, dMap.get(node), dist);
                    VertexDistancePair<T> lowestPair =
                            new VertexDistancePair<>(node, Integer.MAX_VALUE);
                    for (VertexDistancePair<T> pair : adjList.get(node)) {
                        pQueue.add(pair);
                        if (pair.getDistance() < lowestPair.getDistance()) {
                            lowestPair = pair;
                        }
                    }
                    if (pQueue.peek() != null && !(pQueue.peek()
                            .equals(lowestPair))) {
                        dist = 0;
                    }
                } else {
                    pQueue.poll();
                }
            }
            if (dMap.get(goal) != Integer.MAX_VALUE) {
                return dMap.get(goal);
            } else {
                return -1;
            }
        }
    }

}
