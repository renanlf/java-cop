package edu.br.ufpe.cin.sword.cm.sat.strategies;

import edu.br.ufpe.cin.sword.cm.strategies.ConnectionStrategy;

public class SATConnectionStrategy implements ConnectionStrategy<Integer, Void> {

    @Override
    public boolean connect(Integer literal, Integer other) {
        return literal + other == 0;
    }

    @Override
    public void clear() { }

    @Override
    public Void getState() {
        return null;
    }

    @Override
    public void setState(Void state) { }

}
