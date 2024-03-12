package com.example.controller;

import com.example.services.ListService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/list")
public class ListController {

    private final ListService service;
    @Autowired
    public ListController(ListService service) {
        this.service = service;
    }

    @GetMapping("/errors")
    public ResponseEntity errors() {
        val resp = service.getErrors();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST.value())
                .contentType(MediaType.APPLICATION_JSON)
                .body(resp);
    }

    @GetMapping("/agents")
    public ResponseEntity getAgents() {
        val resp = service.getAgent();
        return ResponseEntity
                .status(HttpStatus.OK.value())
                .contentType(MediaType.APPLICATION_JSON)
                .body(resp);
    }

    @GetMapping("/banks")
    public ResponseEntity getBanks() {
        val resp = service.getBank();
        return ResponseEntity
                .status(HttpStatus.OK.value())
                .contentType(MediaType.APPLICATION_JSON)
                .body(resp);
    }

    @GetMapping("/parti-ids")
    public ResponseEntity getPartiIds() {
        val resp = service.getPartiId();
        return ResponseEntity
                .status(HttpStatus.OK.value())
                .contentType(MediaType.APPLICATION_JSON)
                .body(resp);
    }

    @GetMapping("/statuses")
    public ResponseEntity getStatuses() {
        val resp = service.getStatus();
        return ResponseEntity
                .status(HttpStatus.OK.value())
                .contentType(MediaType.APPLICATION_JSON)
                .body(resp);
    }
    @GetMapping("/bank-contracts")
    public ResponseEntity bankContracts() {
        val resp = service.getBankContracts();
        return ResponseEntity
                .status(HttpStatus.OK.value())
                .contentType(MediaType.APPLICATION_JSON)
                .body(resp);
    }

    @GetMapping("/agent-contracts")
    public ResponseEntity agentContracts() {
        val resp = service.getAgentContracts();
        return ResponseEntity
                .status(HttpStatus.OK.value())
                .contentType(MediaType.APPLICATION_JSON)
                .body(resp);
    }
}
