package edu.br.ufpe.cin.sword.cm.sat.strategies;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import edu.br.ufpe.cin.sword.cm.strategies.BlockingStrategy;

public class SATBlockingStrategy implements BlockingStrategy<Integer, Void, List<Collection<Integer>>> {

    @Override
    public boolean isBlocked(Integer literal, Set<Integer> path, Void connState, List<Collection<Integer>> copyState) {
        return false;
    }

}
