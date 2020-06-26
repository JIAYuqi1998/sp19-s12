package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import bearmaps.proj2ab.DoubleMapPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.*;

import static bearmaps.hw4.SolverOutcome.*;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private SolverOutcome outcome;
    private LinkedList<Vertex> solution;
    private double solutionWeight;
    private ArrayHeapMinPQ<Vertex> minPQ;
    private HashMap<Vertex, Double> dis;
    private HashMap<Vertex, Vertex> edgeTo;
    private double timeSpent;



    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch sw = new Stopwatch();
        minPQ = new ArrayHeapMinPQ<>();
        dis = new HashMap<Vertex, Double>();
        edgeTo = new HashMap<Vertex, Vertex>();
        solution = new LinkedList<Vertex>();
        minPQ.add(start, 0);
        dis.put(start, 0.0);
        while (minPQ.size() != 0 && !minPQ.getSmallest().equals(end)) {
            Vertex smallest = minPQ.removeSmallest();
            List<WeightedEdge<Vertex>> neighbors = input.neighbors(smallest);
            for (WeightedEdge<Vertex> i : neighbors) {
                if (!dis.containsKey(i.to())) {
                    dis.put(i.to(), Double.POSITIVE_INFINITY);
                }
                double estimatedDis = input.estimatedDistanceToGoal(i.to(), end);
                relax(i, estimatedDis);
            }
        }
        timeSpent = sw.elapsedTime();
        if (timeSpent > timeout) {
            outcome = TIMEOUT;
            return;
        }
        if (minPQ.size() == 0 && !minPQ.getSmallest().equals(end)) {
            outcome = UNSOLVABLE;
        } else {
            outcome = SOLVED;
        }
        solutionWeight = dis.get(end);
        for (Vertex i = end; i != start; i = edgeTo.get(i)) {
            solution.addFirst(i);
        }
        solution.addFirst(start);
    }
    private void relax(WeightedEdge<Vertex> edge, double estDis) {
        Vertex p = edge.from();
        Vertex q = edge.to();
        Double weight = edge.weight();
        double disToP = dis.get(p);
        double disToQ = dis.get(q);
        if (disToP + weight < disToQ) {
            dis.replace(q, disToP + weight);
            edgeTo.put(q, p);
            if (minPQ.contains(q)) {
                minPQ.changePriority(q, dis.get(q) + estDis);
            } else {
                minPQ.add(q, dis.get(q) + estDis);
            }
        }
    }
    public SolverOutcome outcome() {
        return outcome;
    }
    public List<Vertex> solution() {
        return solution;
    }
    public double solutionWeight() {
        return solutionWeight;
    }
    public int numStatesExplored() {
        return solution.size();
    }
    public double explorationTime() {
        return timeSpent;
    }
}