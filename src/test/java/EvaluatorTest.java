import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

public class EvaluatorTest {

    @Test
    public void testAdd() throws Exception {
        assertThat(evaluate("5 + 4 + 9"), equalTo(18.0));

    }
    @Test
    public void testSub() throws Exception {
        assertThat(evaluate("18 - 5 - 4"), equalTo(9.0));

    }
    @Test
    public void testMult() throws Exception {
        assertThat(evaluate("5 * 4 * 9"), equalTo(180.0));

    }
    @Test
    public void testDiv() throws Exception {
        assertThat(evaluate("180 / 9"), equalTo(20.0));

    }

    @Test
    public void testMultAdd() throws Exception {
        assertThat(evaluate("2 + 2 * 2"), equalTo(6.0));

    }
    @Test
    public void testDivSub() throws Exception {
        assertThat(evaluate("4 - 2 / 2"), equalTo(3.0));
    }

    @Test
    public void testSubAdd() throws Exception {
        assertThat(evaluate("2 + 2 - 2"), equalTo(2.0));

    }
    @Test
    public void testDivMult() throws Exception {
        assertThat(evaluate("4 * 4 / 2"), equalTo(8.0));
    }

    @Test
    public void testDefaultProgrBehavior() throws Exception {
        assertThat(evaluate("1;2;3;4"), equalTo(4.0));
    }

    @Test
    public void testExpComplete() throws Exception {
        assertThat(evaluate("(4 * 4) / (2 + 2)"), equalTo(4.0));
    }

    /**ANTlr4 NÃO LANÇA EXCESSÃO DE ERROS LEXICOS **/
    @Test
    public void testExpNotInGramarReturnError() throws Exception {
        assertNotEquals(evaluate("{4 * 4} / (2 + 2)"), equalTo(4.0));
        assertNotEquals(evaluate("[4 * 4] / (2 + 2)"), equalTo(4.0));
    }


    private Double evaluate(String operation) {
        QConParser parser = new QConParser(new CommonTokenStream(new QConLexer(new ANTLRInputStream(operation))));
        QConParser.ProgContext tree = parser.prog();
        Evaluator e = new Evaluator();
        return e.visit(tree);
    }
}
