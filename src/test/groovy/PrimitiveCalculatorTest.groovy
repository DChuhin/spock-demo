import primitive.PrimitiveCalculator
import spock.lang.Specification

class PrimitiveCalculatorTest extends Specification {
    def 'It should multiply'() {
        given:
        def x = 1
        def y = 2
        def multiplier = new PrimitiveCalculator()

        when:
        def result = multiplier.multiply(x, y)

        then:
        result == 2
    }

    def 'show expect'() {
        expect:
        new PrimitiveCalculator().multiply(1, 2) == 2
    }

    def 'show given expect'() {
        given:
        def x = 1
        def y = 2
        def multiplier = new PrimitiveCalculator()

        expect:
        multiplier.multiply(x, y) == 2
    }

    def 'multiply 0 by #x'() {
        given:
        def y = 0
        def multiplier = new PrimitiveCalculator()

        when:
        def result = multiplier.multiply(x, y)

        then:
        result == 0

        where:
        x << [1, 2, 3]
    }

    def 'It should multiply #x and #y to get #expectedResult'() {
        given:
        def multiplier = new PrimitiveCalculator()

        when:
        def actualResult = multiplier.multiply(x, y)

        then:
        expectedResult == actualResult

        where:
        x | y | expectedResult
        2 | 2 | 4
        3 | 4 | 12
        6 | 6 | 35
        7 | 6 | 42
    }

    def 'exception'() {
        given:
        def calc = new PrimitiveCalculator()

        when:
        calc.divide(1, 0)

        then:
        def e = thrown(ArithmeticException)
        e.getMessage() == '/ by zero'
    }
}
