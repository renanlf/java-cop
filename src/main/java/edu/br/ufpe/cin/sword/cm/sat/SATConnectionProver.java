package edu.br.ufpe.cin.sword.cm.sat;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import edu.br.ufpe.cin.sword.cm.mapper.exceptions.FileParserException;
import edu.br.ufpe.cin.sword.cm.prover.SimpleProver;
import edu.br.ufpe.cin.sword.cm.sat.mapper.DimacsCNFMatrixMapper;
import edu.br.ufpe.cin.sword.cm.sat.strategies.SATBlockingStrategy;
import edu.br.ufpe.cin.sword.cm.sat.strategies.SATConnectionStrategy;
import edu.br.ufpe.cin.sword.cm.sat.strategies.SATCopyStrategy;
import edu.br.ufpe.cin.sword.cm.sat.strategies.SATLiteralHelperStrategy;
import edu.br.ufpe.cin.sword.cm.tree.FailProofTree;

public class SATConnectionProver {
    private SATLiteralHelperStrategy helperStrategy;

    private SimpleProver<Integer, Void, List<Collection<Integer>>> prover;

    public SATConnectionProver() {
        this.helperStrategy = new SATLiteralHelperStrategy();

        this.prover = new SimpleProver<>(this.helperStrategy, new SATConnectionStrategy(), new SATCopyStrategy(),
                new SATBlockingStrategy());
    }

    public boolean unsat(File inputFile) throws IOException, FileParserException {
        helperStrategy.clear();

        var mapper = new DimacsCNFMatrixMapper();
        mapper.addClauseListener(helperStrategy);
        mapper.addMatrixListener(helperStrategy);

        var matrix = mapper.map(inputFile);

        return !(prover.prove(matrix) instanceof FailProofTree<Integer>);
    }

}
