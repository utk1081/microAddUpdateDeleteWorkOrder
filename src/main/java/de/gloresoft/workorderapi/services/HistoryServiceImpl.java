package de.gloresoft.workorderapi.services;

import de.gloresoft.workorderapi.entities.History;
import de.gloresoft.workorderapi.exceptions.ResourceAlreadyExistsException;
import de.gloresoft.workorderapi.exceptions.ResourceNotFoundException;
import de.gloresoft.workorderapi.repositories.HistoryRepository;
import org.springframework.stereotype.Service;

@Service
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;

    public HistoryServiceImpl(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @Override
    public Iterable<History> findAllHistories() {
        return historyRepository.findAll();
    }

    @Override
    public History findHistoryById(Long id) {
        return historyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("This history id:"+id+" does not exist."));
    }

    @Override
    public void addHistory(History history) {
//        if(!historyRepository.existsById((long) history.getId())) {
//            throw new ResourceAlreadyExistsException("This id:" + history.getId() + " already exists");
//        }
        historyRepository.save(history);
    }

    @Override
    public void updateHistory(History history, Long id) {
        if(!historyRepository.existsById(id)) {
            throw new ResourceNotFoundException("This id:"+id+" already exists");
        }
        historyRepository.save(history);
    }

    @Override
    public void removeHistoryById(Long id) {
        if(!historyRepository.existsById(id)) {
            throw new ResourceNotFoundException("This id:"+id+" already exists");
        }
        historyRepository.deleteById(id);
    }
}
