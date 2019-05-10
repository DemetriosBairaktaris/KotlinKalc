package com.kotlin20.kotlinkalc.model

import net.objecthunter.exp4j.ExpressionBuilder
import net.objecthunter.exp4j.tokenizer.UnknownFunctionOrVariableException
import java.lang.IllegalArgumentException

class Calculator() {

    fun evaluate(input: String): Double {
        try {
            return ExpressionBuilder(input)
                .build()
                .evaluate()
        }catch (exception: UnknownFunctionOrVariableException){
            throw IllegalArgumentException()
        }
    }
}

