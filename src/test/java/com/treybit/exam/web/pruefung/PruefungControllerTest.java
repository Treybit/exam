package com.treybit.exam.web.pruefung;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
public class PruefungControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("Das Formular um eine Prüfung zu erstellen ist erreichbar unter /pruefung/erstellen")
    void formularPruefungErstellenErreichbar() throws Exception {
        MvcResult pruefungForm = mvc.perform(get("/pruefung/erstellen"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("pruefungForm"))
                .andReturn();

        //hier schauen wir, ob die Seite tatsächlich lädt mit der richtigen Überschrift
        String contentAsString = pruefungForm.getResponse().getContentAsString();
        assertThat(contentAsString).contains("Prüfung erstellen");
    }
}
