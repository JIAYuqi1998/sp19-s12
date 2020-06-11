import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */

/**
 * Use two priority queue to store the flight. One is ordered by starting time,
 *  the other is by landing time. Firstly, we record the starting time of the
 *  flight which takes off first, and the first landing time. In the specified
 *  timeline, if the next flight took off before the landing time, they are in
 *  the same span, add the passengers number, and store the current sum to maxSum,
 *  instead, if the flight took off after the first flight landing, which means there
 *  is a flight landing in the time span, we remove the passengers of landing plane.
 *  Iteratively, we could get the time span with the maximum passengers in air.
 */
public class FlightSolver {
    int maxSum = 0;
    Comparator<Flight> startTimeComparator = (i, j) -> i.startTime - j.startTime;
    Comparator<Flight> endTimeComparator = (i, j) -> i.endTime - j.endTime;
    PriorityQueue<Flight> minStart = new PriorityQueue<Flight>(startTimeComparator);
    PriorityQueue<Flight> minEnd = new PriorityQueue<Flight>(endTimeComparator);
    public FlightSolver(ArrayList<Flight> flights) {
        for (Flight i : flights) {
            minStart.add(i);
            minEnd.add(i);
        }
        int sum = 0;
        while(!minStart.isEmpty()){
            Flight takeoff = minStart.peek();
            Flight landing = minEnd.peek();
            if (takeoff.startTime <= landing.endTime) {
                sum += takeoff.passengers;
                minStart.poll();
            }
            else  {
                sum -= landing.passengers;
                minEnd.poll();
            }
            if (maxSum < sum) {
                maxSum = sum;
            }
        }
    }

    public int solve() {
        return maxSum;
    }

}