package com.example.demo.broker;

import com.example.demo.entity.BrokerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BrokerService {

    @Autowired
    private BrokerRepository repo;

    public void getBroker() {
        repo.findAll();
    }
}
