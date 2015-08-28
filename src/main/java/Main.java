import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;

        Evaluator evaluator = new Evaluator();
        while ((line = readInput(reader)) != null) {
            try {
                System.out.println(execute(evaluator, line));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private static Object execute(Evaluator evaluator, String line) {
        QConParser parser = new QConParser(new CommonTokenStream(new QConLexer(new ANTLRInputStream(line))));
        QConParser.ProgContext tree = parser.prog();
        return evaluator.visit(tree);
    }

    private static String readInput(BufferedReader reader) throws IOException {
        System.out.print("> ");
        return reader.readLine();
    }
}
