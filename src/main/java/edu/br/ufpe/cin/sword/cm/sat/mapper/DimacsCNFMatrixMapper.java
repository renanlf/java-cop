package edu.br.ufpe.cin.sword.cm.sat.mapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.br.ufpe.cin.sword.cm.mapper.MatrixMapper;
import edu.br.ufpe.cin.sword.cm.mapper.exceptions.FileParserException;
import edu.br.ufpe.cin.sword.cm.mapper.listeners.ClauseListener;
import edu.br.ufpe.cin.sword.cm.mapper.listeners.MatrixListener;

public class DimacsCNFMatrixMapper implements MatrixMapper<Integer> {

    private List<ClauseListener<Integer>> clauseListeners;

    public DimacsCNFMatrixMapper() {
        this.clauseListeners = new ArrayList<>();
    }

    @Override
    public Collection<Collection<Integer>> map(File file) throws IOException, FileParserException {
        Pattern pLinePattern = Pattern.compile("p cnf (\\d+) (\\d+)");
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int numberLiterals;
            int numerClauses;
            boolean readingClauses = false;
            Collection<Collection<Integer>> matrix = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                if (!readingClauses) {
                    Matcher matcher = pLinePattern.matcher(line);
                    if (matcher.matches()) {
                        numberLiterals = Integer.valueOf(matcher.group(1));
                        numerClauses = Integer.valueOf(matcher.group(2));
                        readingClauses = true;
                    }
                } else {
                    List<Integer> clause = new ArrayList<>();
                    try (Scanner scanner = new Scanner(line)) {
                        while(scanner.hasNextInt()) {
                            int number = scanner.nextInt();

                            if(number == 0) {
                                break;
                            }

                            clause.add(number);
                        }
                    }   

                    clauseMapped(clause);
                    matrix.add(clause);
                }
            }
            return matrix;
        }
    }

    @Override
    public void addClauseListener(ClauseListener<Integer> clauseListener) {
        clauseListeners.add(clauseListener);
    }

    @Override
    public void addMatrixListener(MatrixListener<Integer> matrixListener) { }

    private void clauseMapped(List<Integer> clause) {
        clauseListeners.forEach(clauseListener -> {
            clauseListener.onClauseMap(clause);
        });
    }

}
