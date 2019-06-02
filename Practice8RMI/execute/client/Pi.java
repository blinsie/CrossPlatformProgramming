package client;


import compute.Task;

import java.math.BigDecimal;

public class Pi implements Task<BigDecimal> {
    /** константы, используемые в вычислении пи */
    private static final BigDecimal ZERO =
            BigDecimal.valueOf(0);
    private static final BigDecimal  ONE =
            BigDecimal.valueOf(1);
    private static final BigDecimal FOUR =
            BigDecimal.valueOf(4);

    /** режим округления при вычислении */
    private static final int roundingMode =
            BigDecimal.ROUND_HALF_EVEN;

    /** количество цифр после десятичной точки */
    private int digits;

    /**
     * Конструирование задачи для вычисления пи
     * с заданной точностью
     */
    public Pi(int digits) {
        this.digits = digits;
    }

    /**
     * Вычисление пи.
     */
    public BigDecimal execute() {
        return computePi(digits);
    }

    /**
     * Вычисление пи с указанным количеством
     * цифр после десятичной точки.  это значение
     * вычисляется по формулк Machin'а:
     *
     *          pi/4 = 4*arctan(1/5) - arctan(1/239)
     *
     */
    public static BigDecimal computePi(int digits) {
        int scale = digits + 5;
        BigDecimal arctan1_5 = arctan(5, scale);
        BigDecimal arctan1_239 = arctan(239, scale);
        BigDecimal pi = arctan1_5.multiply(FOUR).subtract(
                arctan1_239).multiply(FOUR);
        return pi.setScale(digits,
                BigDecimal.ROUND_HALF_UP);
    }
    /**
     * Вычисление значения арктангенса в радианах
     * the inverse of the supplied integer to the speficied
     * number of digits after the decimal point.  The value
     * is computed using the power series expansion for the
     * arc tangent:
     *
     * arctan(x) = x - (x^3)/3 + (x^5)/5 - (x^7)/7 +
     *     (x^9)/9 ...
     */
    public static BigDecimal arctan(int inverseX, int scale)
    {
        BigDecimal result, numer, term;
        BigDecimal invX = BigDecimal.valueOf(inverseX);
        BigDecimal invX2 =
                BigDecimal.valueOf(inverseX * inverseX);

        numer = ONE.divide(invX, scale, roundingMode);

        result = numer;
        int i = 1;
        do {
            numer =
                    numer.divide(invX2, scale, roundingMode);
            int denom = 2 * i + 1;
            term =
                    numer.divide(BigDecimal.valueOf(denom),
                            scale, roundingMode);
            if ((i % 2) != 0) {
                result = result.subtract(term);
            } else {
                result = result.add(term);
            }
            i++;
        } while (term.compareTo(ZERO) != 0);
        return result;
    }
}
