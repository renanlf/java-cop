package edu.br.ufpe.cin.sword.cm.sat.strategies;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.br.ufpe.cin.sword.cm.mapper.listeners.ClauseListener;
import edu.br.ufpe.cin.sword.cm.mapper.listeners.MatrixListener;
import edu.br.ufpe.cin.sword.cm.strategies.LiteralHelperStrategy;

public class SATLiteralHelperStrategy implements LiteralHelperStrategy<Integer>, ClauseListener<Integer>, MatrixListener<Integer> {

    private Map<Integer, Set<Collection<Integer>>> clausesComplementaryLiterals;

    public SATLiteralHelperStrategy() {
        this.clausesComplementaryLiterals = new HashMap<>();
    }

    @Override
    public boolean complementaryOf(Integer literal, Integer other) {
        return literal + other == 0;
    }

    @Override
    public Set<Collection<Integer>> clausesComplementaryOf(Integer literal, Collection<Collection<Integer>> matrix) {
        return clausesComplementaryLiterals.getOrDefault(literal, Set.of());
    }

    @Override
    public void onClauseMap(Collection<Integer> clause) {
        for (Integer literal : clause) {
            Integer negLiteral = -literal;
            Set<Collection<Integer>> clausesComplementary = clausesComplementaryLiterals.getOrDefault(negLiteral, new HashSet<>());
            clausesComplementary.add(clause);
            clausesComplementaryLiterals.put(negLiteral, clausesComplementary);
        }
    }

    @Override
    public void onMatrixMap(Collection<Collection<Integer>> matrix) {
        clausesComplementaryLiterals = Map.copyOf(clausesComplementaryLiterals);
    }

    @Override
    public void clear() {
        clausesComplementaryLiterals = new HashMap<>();
    }

}
