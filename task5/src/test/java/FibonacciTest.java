import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ebac.Fibonacci;

public class FibonacciTest {
    
    @Test
    void testFiboZero() {
        assertEquals(0, Fibonacci.fibo(0));
    }

    @Test
    void testFiboUm() {
        assertEquals(1, Fibonacci.fibo(1));
    }

    @Test
    void testFibo10Primeiros() {
        assertEquals(1, Fibonacci.fibo(1));
        assertEquals(1, Fibonacci.fibo(2));
        assertEquals(2, Fibonacci.fibo(3));
        assertEquals(3, Fibonacci.fibo(4));
        assertEquals(5, Fibonacci.fibo(5));
        assertEquals(8, Fibonacci.fibo(6));
        assertEquals(13, Fibonacci.fibo(7));
        assertEquals(21, Fibonacci.fibo(8));
        assertEquals(34, Fibonacci.fibo(9));
        assertEquals(55, Fibonacci.fibo(10));
    }

    @Test
    void testFibo30() {
        assertEquals(832040, Fibonacci.fibo(30));

    }
}
