package edu.br.ufpe.cin.sword.cm.propositional;

import java.io.File;
import java.io.IOException;
import java.util.List;

import edu.br.ufpe.cin.sword.cm.mapper.exceptions.FileParserException;
import edu.br.ufpe.cin.sword.cm.node.LinkedNode;
import edu.br.ufpe.cin.sword.cm.propositional.mapper.DimacsCNFMatrixMapper;
import edu.br.ufpe.cin.sword.cm.propositional.strategies.PropositionalBlockingStrategy;
import edu.br.ufpe.cin.sword.cm.propositional.strategies.PropositionalConnectionStrategy;
import edu.br.ufpe.cin.sword.cm.propositional.strategies.PropositionalCopyStrategy;
import edu.br.ufpe.cin.sword.cm.propositional.strategies.PropositionalLiteralHelperStrategy;
import edu.br.ufpe.cin.sword.cm.prover.SimpleProver;
import edu.br.ufpe.cin.sword.cm.tree.FailProofTree;
import edu.br.ufpe.cin.sword.cm.tree.ProofTree;

public class PropositionalConnectionProverDecorator {
    private PropositionalLiteralHelperStrategy helperStrategy;

    private SimpleProver<Integer, Void, LinkedNode<List<Integer>>> prover;

    public PropositionalConnectionProverDecorator() {
        this.helperStrategy = new PropositionalLiteralHelperStrategy();

        this.prover = new SimpleProver<>(this.helperStrategy, new PropositionalConnectionStrategy(), new PropositionalCopyStrategy(),
                new PropositionalBlockingStrategy());
    }

    public ProofTree<Integer> prove(File inputFile) throws IOException, FileParserException {
        helperStrategy.clear();

        var mapper = new DimacsCNFMatrixMapper();
        mapper.addClauseListener(helperStrategy);
        mapper.addMatrixListener(helperStrategy);

        var matrix = mapper.map(inputFile);

        return prover.prove(matrix);
    }

    public boolean unsat(File inputFile) throws IOException, FileParserException {
        return !(prove(inputFile) instanceof FailProofTree<Integer>);
    }

}
