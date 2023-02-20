package org.qianq.qlox

class Fn (
    private val declaration: Function,
    private val closure: Environment,
): Callable {
    override val arity: Int = declaration.params.size

    override fun call(interpreter: Interpreter, arguments: List<Any?>): Any? {
        val environment = Environment(closure)
        for (i in declaration.params.indices) {
            environment.define(declaration.params[i].lexeme, arguments[i])
        }
        interpreter.executeBlock(declaration.body, environment)
        return null
    }

    override fun toString(): String {
        return "<fn ${declaration.name.lexeme}>"
    }
}