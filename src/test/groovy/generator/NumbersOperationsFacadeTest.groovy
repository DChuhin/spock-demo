package generator

import example.BinaryOperator
import example.NumberGenerator
import example.UnaryOperator
import example.generator.NumbersOperationsService
import spock.lang.Specification
import spock.lang.Subject

class NumbersOperationsFacadeTest extends Specification {

    NumberGenerator firstOperandGenerator = Mock()
    NumberGenerator secondOperandGenerator = Mock()
    BinaryOperator operator = Mock()

    @Subject
    def service = new NumbersOperationsService(
            firstOperandGenerator, secondOperandGenerator, operator
    )

    def 'it should apply operator to generated numbers'() {
        given:
        def transformer = Mock(UnaryOperator)

        when:
        def result = service.doOperation(transformer)

        then:
        result == 21
        1 * firstOperandGenerator.generate() >> 1
        1 * secondOperandGenerator.generate() >> 2
        1 * operator.apply(1, 2) >> 5
        1 * transformer.apply(5) >> 21
        0 * _
    }

    def 'demo multiple return generator values'() {
        when:
        def result1 = service.doOperation(null)
        def result2 = service.doOperation(null)
        def result3 = service.doOperation(null)

        then:
        result1 == 1
        result2 == 2
        result3 == 3
        3 * firstOperandGenerator.generate() >> 1
        3 * secondOperandGenerator.generate() >>> [1, 2, 3]
        1 * operator.apply(1, 1) >> 1
        1 * operator.apply(1, 2) >> 2
        1 * operator.apply(1, 3) >> 3
/*        3 * operator.apply(1, {
            it -> [1, 2, 3].contains(it)
        }) >>> [1, 2, 3]*/
        0 * _
    }

    def 'show interaction params usage'() {
        when:
        def result = service.doOperation(null)

        then:
        result == 2
        1 * firstOperandGenerator.generate() >> 1
        1 * secondOperandGenerator.generate() >> 2
        _ * operator.apply(_, _) >> { args -> args[0] * args[1] }
        0 * _
    }

}
