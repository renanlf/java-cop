# Java-CoP

Implementing an Automated Theorem Prover (ATP) for classical or new logic is a hard task, especially for researchers who want to test or present their theoretical results in a simple approach.
One solution for developing provers is the Connection Method (CM), a goal-oriented direct proof search algorithm that looks for connections that unify a formula, allowing CM to perform competitively against the most prominent methods in ATP's field without low-level optimizations.
Another advantage of CM is its uniformity, allowing it to apply to a range of logical languages with the same proof technique.
Besides that, CM's uniformity provides a simple sharing of high-level optimization implementations between CMs.
This repo contains the Java-CoP: a connection method prover framework to abstract the proof-related algorithm for the user. It helps to focus only on literal representation, unification, and blocking strategies.
 
