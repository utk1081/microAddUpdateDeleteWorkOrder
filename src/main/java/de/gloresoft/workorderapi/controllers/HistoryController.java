package de.gloresoft.workorderapi.controllers;

import de.gloresoft.workorderapi.entities.History;
import de.gloresoft.workorderapi.services.HistoryService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "${workorders.gui.application.url}")
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("/histories")
    public Iterable<History> getAllHistories() {
        return historyService.findAllHistories();
    }

    @PostMapping("/histories")
    public void addHistory(@RequestBody History history) {
        historyService.addHistory(history);
    }

    @DeleteMapping("/histories/{id}")
    public void deleteHistory(@PathVariable String id) {
        long longId;
        try {
            longId = Long.parseLong(id);
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException(nfe.getMessage());
        }
        historyService.removeHistoryById(longId);
    }
}
