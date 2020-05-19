package algorithm.service;

import algorithm.model.Hub;
import algorithm.model.RouteSegment;

import java.util.List;

public interface RouteSegmentService {

    public RouteSegment save(RouteSegment routeSegment);

    public RouteSegment findById(Integer id);

    public boolean existsById(Integer id);

    public RouteSegment update(RouteSegment routeSegment);

    public List<RouteSegment> saveAll(List<RouteSegment> routeSegments);
}
