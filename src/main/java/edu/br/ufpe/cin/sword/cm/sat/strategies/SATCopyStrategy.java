package edu.br.ufpe.cin.sword.cm.sat.strategies;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import edu.br.ufpe.cin.sword.cm.strategies.CopyStrategy;

public class SATCopyStrategy implements CopyStrategy<Integer, List<Collection<Integer>>> {

    private List<Collection<Integer>> clausesUsed;

    @Override
    public Optional<Collection<Integer>> copy(Collection<Integer> clause) {
        if (clausesUsed.contains(clause)) 
            return Optional.empty();

        var modifiableList = new ArrayList<>(clausesUsed);
        modifiableList.add(clause);
        setState(List.copyOf(modifiableList));

        return Optional.of(clause);
    }

    @Override
    public void clear() { 
        clausesUsed = Collections.emptyList();
    }

    @Override
    public List<Collection<Integer>> getState() {
        return clausesUsed;
    }

    @Override
    public void setState(List<Collection<Integer>> state) {
        this.clausesUsed = state;
    }

}
