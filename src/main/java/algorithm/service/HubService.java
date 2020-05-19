package algorithm.service;

import algorithm.model.Hub;

import java.util.List;

public interface HubService {

    public Hub save(Hub hub);

    public Hub findById(Integer id);

    public boolean existsById(Integer id);

    public Hub update(Hub updatingHub);

    public List<Hub> saveAll(List<Hub> hubs);

    public List<Hub> findAll();
}
