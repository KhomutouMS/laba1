package demo.parallel;

import demo.parallel.Complex;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class ComplexTest {

    private Complex complex;

    @BeforeEach
    void setUp() {
        complex = new Complex(2, 3);
    }

    @Test
    void testPowZero() {
        // Любое число в степени 0 равно 1
        Complex result = complex.pow(0);
        assertEquals(new Complex(1, 0), result);
    }

    @Test
    void testPowOne() {
        // Число в степени 1 равно самому себе
        Complex result = complex.pow(1);
        assertEquals(complex, result);
    }

    @Test
    void testPowTwo() {
        // Проверяем возведение в квадрат
        Complex result = complex.pow(2);
        Complex expected = complex.times(complex);
        assertEquals(expected, result);
    }

    @Test
    void testPowThree() {
        // Проверяем возведение в куб
        Complex result = complex.pow(3);
        Complex expected = complex.times(complex).times(complex);
        assertEquals(expected, result);
    }

    @Test
    void testPowWithRealNumber() {
        // Тест с вещественным числом
        Complex realNum = new Complex(3, 0);
        Complex result = realNum.pow(4);
        assertEquals(new Complex(81, 0), result); // 3^4 = 81
    }

    @Test
    void testPowWithImaginaryNumber() {
        // Тест с мнимым числом
        Complex imagNum = new Complex(0, 2);
        Complex result = imagNum.pow(2);
        assertEquals(new Complex(-4, 0), result); // (2i)^2 = -4
    }

    @Test
    void testPowWithImaginaryUnit() {
        // Тест с мнимой единицей i
        Complex i = new Complex(0, 1);

        assertEquals(new Complex(-1, 0), i.pow(2)); // i² = -1
        assertEquals(new Complex(0, -1), i.pow(3)); // i³ = -i
        assertEquals(new Complex(1, 0), i.pow(4));  // i⁴ = 1
        assertEquals(new Complex(0, 1), i.pow(5));  // i⁵ = i
    }

    @Test
    void testPowWithZero() {
        // Тест с нулем
        Complex zero = new Complex(0, 0);
        Complex result = zero.pow(5);
        assertEquals(new Complex(0, 0), result); // 0 в любой положительной степени = 0
    }

    @Test
    void testPowWithOne() {
        // Тест с единицей
        Complex one = new Complex(1, 0);
        Complex result = one.pow(10);
        assertEquals(new Complex(1, 0), result); // 1 в любой степени = 1
    }

    @Test
    void testPowWithNegativeReal() {
        // Тест с отрицательным вещественным числом
        Complex negative = new Complex(-2, 0);

        assertEquals(new Complex(4, 0), negative.pow(2));  // (-2)² = 4
        assertEquals(new Complex(-8, 0), negative.pow(3)); // (-2)³ = -8
    }

    @Test
    void testPowLargeExponent() {
        // Тест с большой степенью
        Complex smallNum = new Complex(1.1, 0.1);
        Complex result = smallNum.pow(5);

        // Проверяем через последовательное умножение
        Complex expected = smallNum;
        for (int i = 0; i < 4; i++) {
            expected = expected.times(smallNum);
        }

        assertEquals(expected.real, result.real, 1e-10);
        assertEquals(expected.imag, result.imag, 1e-10);
    }

    @Test
    void testPowConsistency() {
        // Проверяем согласованность: z⁵ = z² * z³
        Complex z = new Complex(1.5, -2.5);

        Complex pow5 = z.pow(5);
        Complex pow2 = z.pow(2);
        Complex pow3 = z.pow(3);
        Complex multiplied = pow2.times(pow3);

        assertEquals(multiplied.real, pow5.real, 1e-10);
        assertEquals(multiplied.imag, pow5.imag, 1e-10);
    }

    @Test
    void testPowWithFractions() {
        // Тест с дробными числами
        Complex fraction = new Complex(0.5, 0.5);
        Complex result = fraction.pow(3);

        // (0.5 + 0.5i)³ = -0.125 + 0.125i
        assertEquals(-0.125, result.real, 1e-10);
        assertEquals(0.125, result.imag, 1e-10);
    }

    @Test
    void testPowEdgeCaseNegativeOne() {
        // Тест с -1
        Complex negOne = new Complex(-1, 0);

        assertEquals(new Complex(1, 0), negOne.pow(2));  // (-1)² = 1
        assertEquals(new Complex(-1, 0), negOne.pow(3)); // (-1)³ = -1
        assertEquals(new Complex(1, 0), negOne.pow(4));  // (-1)⁴ = 1
    }

    @Test
    void testPowShouldNotModifyOriginal() {
        // Проверяем, что оригинальный объект не изменяется
        Complex original = new Complex(2, 3);
        Complex copy = new Complex(2, 3);

        original.pow(4);
        assertEquals(copy, original); // Оригинал не должен измениться
    }
}