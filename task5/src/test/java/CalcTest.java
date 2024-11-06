import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import ebac.Calc;

public class CalcTest {
    
    Calc calc = new Calc();

    @Test
    void testSoma() {
        assertEquals(6.0, calc.soma(1, 5), "A soma deve ser 15.0");
        assertEquals(5, calc.soma(0, 5), "A soma deve ser 15.0");
        assertEquals(-4.0, calc.soma(1, (-5)), "A soma deve ser 15.0");
        assertEquals(4.0, calc.soma(-1, 5), "A soma deve ser 15.0");
    }

    @Test
    void testSubtrai() {
        assertEquals(-4.0, calc.subtrai(1, 5), "A soma deve ser 15.0");
        assertEquals(4, calc.subtrai(5, 1), "A soma deve ser 15.0");
        assertEquals(6.0, calc.subtrai(1, (-5)), "A soma deve ser 15.0");
        assertEquals(-6.0, calc.subtrai(-1, 5), "A soma deve ser 15.0");
    }

    @Test
    void testMultiplica() {
        assertEquals(5.0, calc.multiplica(1, 5), "A soma deve ser 15.0");
        assertEquals(0, calc.multiplica(0, 5), "A soma deve ser 15.0");
        assertEquals(-5, calc.multiplica(1, (-5)), "A soma deve ser 15.0");
        assertEquals(-5.0, calc.multiplica(-1, 5), "A soma deve ser 15.0");
    }

    @Test
    void testDivide() {
        assertEquals(0.2, calc.divide(1, 5), "A soma deve ser 15.0");
        assertEquals(0, calc.divide(0, 5), "A soma deve ser 15.0");
        assertEquals(-0.2, calc.divide(1, (-5)), "A soma deve ser 15.0");
        assertEquals(-0.2, calc.divide(-1, 5), "A soma deve ser 15.0");
        assertThrows(IllegalArgumentException.class, () -> calc.divide(1, 0));
    }
}
