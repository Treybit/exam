package com.treybit.exam.service;

import com.treybit.exam.domain.Pruefung;
import com.treybit.exam.web.frage.FrageForm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class PruefungService {

    private final PruefungRepo pruefungRepo;

    public PruefungService(PruefungRepo pruefungRepo) {
        this.pruefungRepo = pruefungRepo;
    }

    public ArrayList<Pruefung> findeAllePruefungen(){
        return (ArrayList<Pruefung>) pruefungRepo.findeAllePruefungen();
    }

    public void speicherePruefung(Pruefung pruefung){
        pruefungRepo.speicherePruefung(pruefung);
    }

    public void loeschePruefungMitId(UUID id) {

        pruefungRepo.loeschePruefungMitId(id);
    }

    public Optional<Pruefung> findePruefungMitId(UUID id){
        return pruefungRepo.findePruefungMitId(id);
    }

    public void frageHinzufuegen(FrageForm frageForm, UUID id){
        Optional<Pruefung> pruefung = findePruefungMitId(id);
        if(pruefung.isEmpty()){
            //Wenn keine Pürfung dazu existiert erstmal ignorieren. Hier muss später irgendwie eine Exception fliegen
            return;
        }
        pruefung.get().addFrage(UUID.randomUUID(),
                frageForm.getUeberschrift(),
                frageForm.getAufgabenstellung(),
                frageForm.getLoesung(),
                frageForm.getPunkte());
    }

}
