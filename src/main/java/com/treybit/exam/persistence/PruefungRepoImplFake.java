package com.treybit.exam.persistence;

import com.treybit.exam.domain.Pruefung;
import com.treybit.exam.service.PruefungRepo;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PruefungRepoImplFake implements PruefungRepo {

    HashMap<UUID, Pruefung> pruefungMap = new HashMap<>();


    @Override
    public List<Pruefung> findeAllePruefungen() {
        ArrayList allePruefungen = new ArrayList(pruefungMap.values());
        return allePruefungen;
    }

    @Override
    public void speicherePruefung(Pruefung pruefung) {
        pruefungMap.put(pruefung.getId(), pruefung);
    }

    @Override
    public Optional<Pruefung> findePruefungMitId(UUID id) {
        return Optional.ofNullable(pruefungMap.get(id));
    }

    @Override
    public void aktualisierePruefung(Pruefung pruefung) {
        throw new UnsupportedOperationException("Diese Funktion ist noch nicht implementiert.");
    }

    @Override
    public void loeschePruefungMitId(UUID id) {
        pruefungMap.remove(id);
    }
}
