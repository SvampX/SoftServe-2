package algorithm.service.impl;

import algorithm.model.Hub;
import algorithm.repository.HubRepository;
import algorithm.service.HubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class HubServiceImpl implements HubService {

    private final HubRepository hubRepository;

    @Autowired
    public HubServiceImpl(HubRepository hubRepository) {
        this.hubRepository = hubRepository;
    }

    @Override
    public Hub save(Hub hub) {
        return hubRepository.save(hub);
    }

    @Override
    public Hub findById(Integer id) {
        Optional<Hub> result = hubRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new EntityNotFoundException("No hubs found in findById");
    }

    @Override
    public boolean existsById(Integer id) {
        return hubRepository.existsById(id);
    }

    @Override
    public Hub update(Hub updatingHub) {
        Integer id = updatingHub.getId();
        if(id != null){
            if(hubRepository.existsById(id)){
                return hubRepository.save(updatingHub);
            }
            throw new IllegalStateException("Updating hub should have valid id");
        }
        throw new IllegalStateException("Updating hub should have initialized id field");
    }

    @Override
    public List<Hub> saveAll(List<Hub> hubs) {
        return hubRepository.saveAll(hubs);
    }

    @Override
    public List<Hub> findAll() {
        return hubRepository.findAll();
    }
}
