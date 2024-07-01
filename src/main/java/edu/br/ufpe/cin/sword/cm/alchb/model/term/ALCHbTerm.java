package edu.br.ufpe.cin.sword.cm.alchb.model.term;

public abstract class ALCHbTerm {

    private final String name;
    private final boolean variable;

    public ALCHbTerm(String name, boolean variable) {
        this.name = name;
        this.variable = variable;
    }

    public String getName() {
        return name;
    }

    public boolean isVariable() {
        return variable;
    }

}
