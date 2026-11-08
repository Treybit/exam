package com.treybit.exam.service;

import org.springframework.stereotype.Service;

@Service
public class PruefungService {

    private final PruefungRepo pruefungRepo;


    public PruefungService(PruefungRepo pruefungRepo) {
        this.pruefungRepo = pruefungRepo;
    }
}
