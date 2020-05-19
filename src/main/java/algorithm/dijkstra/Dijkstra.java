package algorithm.dijkstra;

import algorithm.model.Hub;
import algorithm.model.RouteSegment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra
{
    public static void computePaths(Hub source)
    {
        source.setMinDistance(0.);
        PriorityQueue<Hub> hubQueue = new PriorityQueue<>();
        hubQueue.add(source);

        while (!hubQueue.isEmpty()) {
            Hub hub = hubQueue.poll();

            // Visit each edge exiting u
            for (RouteSegment edge : hub.getClosestRelations())
            {
                Hub neighbour = edge.getTarget();
                double distance = edge.getDistance();
                double distanceThroughHub = hub.getMinDistance() + distance;
                if (distanceThroughHub < neighbour.getMinDistance()) {
                    hubQueue.remove(neighbour);

                    neighbour.setMinDistance(distanceThroughHub);
                    neighbour.setPrevious(hub);
                    hubQueue.add(neighbour);
                }
            }
        }
    }

    public static List<Hub> getShortestPathTo(Hub target)
    {
        List<Hub> route = new ArrayList<>();
        for (Hub hub = target; hub != null; hub = hub.getPrevious())
            route.add(hub);

        Collections.reverse(route);
        return route;
    }

}
