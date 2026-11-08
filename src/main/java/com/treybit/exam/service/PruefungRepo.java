package com.treybit.exam.service;

import com.treybit.exam.domain.Pruefung;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PruefungRepo {

    List<Pruefung> findeAllePruefungen();

    void speicherePruefung(Pruefung pruefung);

    Optional<Pruefung> findePruefungMitId(UUID id);

    void aktualisierePruefung(Pruefung pruefung);

    void loeschePruefungMitId(UUID id);

}
