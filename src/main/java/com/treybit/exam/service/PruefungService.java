package com.treybit.exam.service;

import com.treybit.exam.domain.Pruefung;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PruefungService {

    private final PruefungRepo pruefungRepo;

    public PruefungService(PruefungRepo pruefungRepo) {
        this.pruefungRepo = pruefungRepo;
    }

    public void speicherePruefung(Pruefung pruefung){
        pruefungRepo.speicherePruefung(pruefung);
    }

    public ArrayList<Pruefung> findeAllePruefungen(){
        return (ArrayList<Pruefung>) pruefungRepo.findeAllePruefungen();
    }
}
