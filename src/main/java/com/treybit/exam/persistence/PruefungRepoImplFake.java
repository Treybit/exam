package com.treybit.exam.persistence;

import com.treybit.exam.domain.Pruefung;
import com.treybit.exam.service.PruefungRepo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PruefungRepoImplFake implements PruefungRepo {

    HashMap<UUID, Pruefung> pruefungMap = new HashMap<>();


    @Override
    public List<Pruefung> findeAllePruefungen() {
        return List.of();
    }

    @Override
    public void speicherePruefung(Pruefung pruefung) {

    }

    @Override
    public Optional<Pruefung> findePruefungMitId(UUID id) {
        return Optional.empty();
    }

    @Override
    public void aktualisierePruefung(Pruefung pruefung) {

    }

    @Override
    public void loeschePruefungMitId(UUID id) {

    }
}
