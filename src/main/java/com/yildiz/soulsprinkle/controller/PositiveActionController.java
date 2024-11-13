package com.yildiz.soulsprinkle.controller;

import com.yildiz.soulsprinkle.model.PositiveAction;
import com.yildiz.soulsprinkle.service.PositiveActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/positiveaction")
public class PositiveActionController {

    private final PositiveActionService positiveActionService;

    @Autowired
    public PositiveActionController(PositiveActionService positiveActionService) {
        this.positiveActionService = positiveActionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PositiveAction> getPositiveActionByTaskId(@PathVariable Long taskId) {
        return positiveActionService.getPositiveActionByTaskId(taskId)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<PositiveAction> createPositiveAction(@RequestBody PositiveAction positiveAction) {
        PositiveAction savedPositiveAction = positiveActionService.savePositiveAction(positiveAction);
        return new ResponseEntity<>(savedPositiveAction, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PositiveAction> updatePositiveAction(@PathVariable Long id, @RequestBody PositiveAction updatedPositiveAction) {
        try {
            PositiveAction positiveAction = positiveActionService.updatePositiveActionById(id, updatedPositiveAction);
            return new ResponseEntity<>(positiveAction, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePositiveActionById(@PathVariable Long id) {
        try {
            positiveActionService.deletePositiveAction(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}