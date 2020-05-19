package algorithm.repository;

import algorithm.model.RouteSegment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteSegmentRepository extends JpaRepository<RouteSegment, Integer> {
}
