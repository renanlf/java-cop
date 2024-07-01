package edu.br.ufpe.cin.sword.cm.alchb.strategies;

import java.util.Set;

import edu.br.ufpe.cin.sword.cm.alchb.model.ALCHbLiteral;
import edu.br.ufpe.cin.sword.cm.alchb.model.term.ALCHbIndividual;
import edu.br.ufpe.cin.sword.cm.alchb.model.term.ALCHbVariable;
import edu.br.ufpe.cin.sword.cm.node.LinkedNode;
import edu.br.ufpe.cin.sword.cm.strategies.ConnectionStrategy;
import edu.br.ufpe.cin.sword.cm.util.Pair;

public class ALCHbConnectionStrategy implements ConnectionStrategy<ALCHbLiteral, LinkedNode<Set<Pair<ALCHbVariable, ALCHbIndividual>>>> {

    private LinkedNode<Set<Pair<ALCHbVariable, ALCHbIndividual>>> state;


    @Override
    public boolean connect(ALCHbLiteral literal, ALCHbLiteral other) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'connect'");
    }

    @Override
    public LinkedNode<Set<Pair<ALCHbVariable, ALCHbIndividual>>> getState() {
        return state;
    }

    @Override
    public void setState(LinkedNode<Set<Pair<ALCHbVariable, ALCHbIndividual>>> state) {
        this.state = state;
    }

    @Override
    public void clear() {
        this.state = null;
    }

}
