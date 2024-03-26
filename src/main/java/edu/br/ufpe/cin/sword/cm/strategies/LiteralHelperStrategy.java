package edu.br.ufpe.cin.sword.cm.strategies;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public interface LiteralHelperStrategy<Literal> {
	boolean complementaryOf(Literal literal, Literal other);
	default void clear() { }
	
	default Set<Literal> literalsComplementaryOf(Literal literal, Collection<Literal> set) {
		return set.stream()
				.filter(other -> complementaryOf(literal, other))
				.collect(Collectors.toSet());
	}

	default Set<Collection<Literal>> clausesComplementaryOf(Literal literal, Collection<Collection<Literal>> matrix) {
		return matrix.stream()
			.filter(clause -> !literalsComplementaryOf(literal, clause).isEmpty())
			.collect(Collectors.toSet());
	}
}
