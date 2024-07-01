package edu.br.ufpe.cin.sword.cm.alchb.strategies;

import java.util.Objects;

import edu.br.ufpe.cin.sword.cm.alchb.model.ALCHbLiteral;
import edu.br.ufpe.cin.sword.cm.strategies.LiteralHelperStrategy;

public class ALCHbLiteralHelperStrategy implements LiteralHelperStrategy<ALCHbLiteral> {

    @Override
    public boolean complementaryOf(ALCHbLiteral literal, ALCHbLiteral other) {
        return Objects.equals(literal.getName(), other.getName())
            && literal.isPositive() != other.isPositive();
    }

}
