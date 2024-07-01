package edu.br.ufpe.cin.sword.cm.alchb.model.term;

public final class ALCHbIndividual extends ALCHbTerm {

    private final boolean newIndividual;

    public ALCHbIndividual(String name, boolean newIndividual) {
        super(name, false);
        this.newIndividual = newIndividual;
    }

    public boolean isNewIndividual() {
        return newIndividual;
    }

}
