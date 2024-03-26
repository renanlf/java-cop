package edu.br.ufpe.cin.sword.cm.sat.mapper;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import edu.br.ufpe.cin.sword.cm.mapper.exceptions.FileParserException;
import edu.br.ufpe.cin.sword.cm.sat.SATConnectionProver;

public class SATConnectionProverTest {

    @Test
    public void testFile00001() throws IOException, FileParserException {
        File file = new File("src/test/resources/sat/test_00001.cnf");
        SATConnectionProver prover = new SATConnectionProver();

        assertFalse(prover.unsat(file));
    }

    @Test
    public void testFile00002() throws IOException, FileParserException {
        File file = new File("src/test/resources/sat/test_00002.cnf");
        SATConnectionProver prover = new SATConnectionProver();

        assertTrue(prover.unsat(file));
    }

    @Test
    public void testFile00003() throws IOException, FileParserException {
        File file = new File("src/test/resources/sat/test_00003.cnf");
        SATConnectionProver prover = new SATConnectionProver();

        assertTrue(prover.unsat(file));
    }



    @Test
    public void testFileDubois20() throws IOException, FileParserException {
        File file = new File("src/test/resources/sat/dubois20.cnf");
        SATConnectionProver prover = new SATConnectionProver();

        assertTrue(prover.unsat(file));
    }

}
