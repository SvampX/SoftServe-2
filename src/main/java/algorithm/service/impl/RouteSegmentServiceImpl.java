package algorithm.service.impl;

import algorithm.model.RouteSegment;
import algorithm.repository.RouteSegmentRepository;
import algorithm.service.RouteSegmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class RouteSegmentServiceImpl implements RouteSegmentService {

    private final RouteSegmentRepository routeSegmentRepository;

    @Autowired
    public RouteSegmentServiceImpl(RouteSegmentRepository routeSegmentRepository) {
        this.routeSegmentRepository = routeSegmentRepository;
    }

    @Override
    public RouteSegment save(RouteSegment routeSegment) {
        return routeSegmentRepository.save(routeSegment);
    }

    @Override
    public RouteSegment findById(Integer id) {
        Optional<RouteSegment> result = routeSegmentRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new EntityNotFoundException("No routeSegments found in findById");
    }

    @Override
    public boolean existsById(Integer id) {
        return routeSegmentRepository.existsById(id);
    }

    @Override
    public RouteSegment update(RouteSegment routeSegment) {
        Integer id = routeSegment.getId();
        if (id != null) {
            if (routeSegmentRepository.existsById(id)) {
                return routeSegmentRepository.save(routeSegment);
            }
            throw new IllegalStateException("Updating hub should have valid id");
        }
        throw new IllegalStateException("Updating hub should have initialized id field");
    }

    @Override
    public List<RouteSegment> saveAll(List<RouteSegment> routeSegments) {
        return routeSegmentRepository.saveAll(routeSegments);
    }
}
