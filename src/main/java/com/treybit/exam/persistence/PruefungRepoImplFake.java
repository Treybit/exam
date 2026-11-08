package com.treybit.exam.persistence;

import com.treybit.exam.domain.Pruefung;
import com.treybit.exam.service.PruefungRepo;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public class PruefungRepoImplFake implements PruefungRepo {

    HashMap<UUID, Pruefung> pruefungMap = new HashMap<>();

    public PruefungRepoImplFake() {

        UUID id = UUID.randomUUID();
        Pruefung testPruefung = new Pruefung(
                id,
                "Discord Moderator – Grundlagen & Community Management",
                "Diese Prüfung testet dein Wissen über die Moderation eines Discord-Servers. "
                        + "Behandelt werden unter anderem Serverregeln, Umgang mit Regelverstößen, "
                        + "Timeouts, Bans, Konfliktmanagement, Community-Pflege und der verantwortungsvolle "
                        + "Umgang mit Moderationsrechten.",
                LocalDateTime.now(),
                "Treybit"
        );

        pruefungMap.put(id, testPruefung);
    }

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
        System.out.println("Crasht es hier?");
        pruefungMap.remove(id);
    }
}
