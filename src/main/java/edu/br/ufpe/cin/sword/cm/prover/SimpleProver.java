package edu.br.ufpe.cin.sword.cm.prover;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import edu.br.ufpe.cin.sword.cm.strategies.BlockingStrategy;
import edu.br.ufpe.cin.sword.cm.strategies.ConnectionStrategy;
import edu.br.ufpe.cin.sword.cm.strategies.CopyStrategy;
import edu.br.ufpe.cin.sword.cm.strategies.LiteralHelperStrategy;
import edu.br.ufpe.cin.sword.cm.tree.FailProofTree;
import edu.br.ufpe.cin.sword.cm.tree.ProofTree;
import edu.br.ufpe.cin.sword.cm.tree.ProofTreeFactory;

public class SimpleProver<Literal, ConnectionState, CopyState> {

	private final ConnectionStrategy<Literal, ConnectionState> connStrategy;
	private final CopyStrategy<Literal, CopyState> copyStrategy;
	private final LiteralHelperStrategy<Literal> litHelperStrategy;
	private final BlockingStrategy<Literal, ConnectionState, CopyState> blockingStrategy;
	private final ProofTreeFactory<Literal> proofFactory;

	public SimpleProver(LiteralHelperStrategy<Literal> litHelperStrategy,
			ConnectionStrategy<Literal, ConnectionState> connStrategy, 
			CopyStrategy<Literal, CopyState> copyStrategy,
			BlockingStrategy<Literal, ConnectionState, CopyState> blockingStrategy) {
		super();
		this.connStrategy = connStrategy;
		this.copyStrategy = copyStrategy;
		this.litHelperStrategy = litHelperStrategy;
		this.blockingStrategy = blockingStrategy;
		this.proofFactory = new ProofTreeFactory<Literal>();
	}

	public ProofTree<Literal> prove(Collection<Collection<Literal>> matrix) {
		if (matrix == null || matrix.isEmpty()) {
			return proofFactory.ax(Set.of());
		}

		connStrategy.clear();
		copyStrategy.clear();

		CopyState copyState = copyStrategy.getState();
		for (Collection<Literal> clause : matrix) {
			Optional<Collection<Literal>> copyClause = copyStrategy.copy(clause);

			if (copyClause.isPresent()) {

				ProofTree<Literal> proof = proveClause(copyClause.get(), matrix, Collections.emptySet());

				if (!(proof instanceof FailProofTree)) {
					return proofFactory.st(copyClause.get(), Collections.emptySet(), proof);
				}
					
				copyStrategy.setState(copyState);
			}
		}
		
		return proofFactory.fail(null, null);
	}

	private ProofTree<Literal> proveClause(Collection<Literal> clause, Collection<Collection<Literal>> matrix, Set<Literal> path) {		
		if (clause.isEmpty())
			return proofFactory.ax(path);

		// TODO: to implement a sort strategy to get next literal
		Literal literal = clause.stream().findAny().get();

		ConnectionState connState = connStrategy.getState();
		for (Literal negLiteral : litHelperStrategy.complementaryOf(literal, path)) {
			if (connStrategy.connect(literal, negLiteral)) {	

				ProofTree<Literal> subProof = proveClause(minus(clause, literal), matrix, path);

				if (!(subProof instanceof FailProofTree)) {
					return proofFactory.red(clause, path, subProof);
				}
			}
			connStrategy.setState(connState);
		}

		CopyState copyState = copyStrategy.getState();

		for (Collection<Literal> matrixClause : matrix) {
			Optional<Collection<Literal>> copyClause = copyStrategy.copy(matrixClause);

			if (copyClause.isPresent()) {
				for (Literal negLiteral : litHelperStrategy.complementaryOf(literal, copyClause.get())) {
					
					if (connStrategy.connect(literal, negLiteral) 
							&& !blockingStrategy.isBlocked(negLiteral, path, connStrategy.getState(), copyStrategy.getState())) {
					
						ProofTree<Literal> subProofLeft = proveClause(minus(copyClause.get(), negLiteral), matrix,
								add(path, literal));
	
						if (!(subProofLeft instanceof FailProofTree)) {
							ProofTree<Literal> subProofRight = proveClause(minus(clause, literal), matrix, path);
	
							if (!(subProofRight instanceof FailProofTree)) {
								return proofFactory.ext(clause, path, subProofLeft, subProofRight);
							}
						}
					}
					// if comes here, then some subProof is failed.
					connStrategy.setState(connState);
				}

				// if comes here, then this copy is failed
				copyStrategy.setState(copyState);
			}
		}

		return proofFactory.fail(clause, path);
	}

	private Set<Literal> add(Collection<Literal> previousSet, Literal toAdd) {
		Set<Literal> newSet = new HashSet<>();
		newSet.addAll(previousSet);
		newSet.add(toAdd);
		return newSet;
	}

	private Set<Literal> minus(Collection<Literal> previousSet, Literal toRemove) {
		Set<Literal> newSet = new HashSet<>();
		newSet.addAll(previousSet);
		newSet.remove(toRemove);
		return newSet;
	}

}
