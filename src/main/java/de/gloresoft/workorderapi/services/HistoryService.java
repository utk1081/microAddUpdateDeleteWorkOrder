package de.gloresoft.workorderapi.services;

import de.gloresoft.workorderapi.entities.History;

public interface HistoryService {

    Iterable<History> findAllHistories();

    History findHistoryById(Long id);

    void addHistory(History history);

    void updateHistory(History history, Long id);

    void removeHistoryById(Long id);
}
