package edu.br.ufpe.cin.sword.cm.propositonal.strategies;

import edu.br.ufpe.cin.sword.cm.strategies.ConnectionStrategy;

public class PropositionalConnectionStrategy implements ConnectionStrategy<String, Void> {

	@Override
	public boolean connect(String literal, String other) {
		return true;
	}

	@Override
	public void clear() {
	}

	@Override
	public Void getState() {
		return null;
	}

	@Override
	public void setState(Void state) {
	}

}
