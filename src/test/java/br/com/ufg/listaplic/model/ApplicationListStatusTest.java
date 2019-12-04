package br.com.ufg.listaplic.model;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ApplicationListStatusTest {

    @Test
    public void testStatusIsEmAndamento() {
        assertTrue(ApplicationListStatus.EM_ANDAMENTO.isEmAndamento());
    }

    @Test
    public void testStatusIsEncerrada() {
        assertTrue(ApplicationListStatus.ENCERRADA.isEncerrada());
    }

    @Test
    public void testStatusNaoIniciadaIsNotEncerrada() {
        assertFalse(ApplicationListStatus.NAO_INICIADA.isEncerrada());
    }

    @Test
    public void testStatusNaoIniciadaIsNotEmAndamento() {
        assertFalse(ApplicationListStatus.NAO_INICIADA.isEmAndamento());
    }
}
