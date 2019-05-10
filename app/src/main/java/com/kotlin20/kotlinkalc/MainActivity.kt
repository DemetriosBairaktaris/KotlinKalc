package com.kotlin20.kotlinkalc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.kotlin20.kotlinkalc.model.Calculator
import java.lang.ArithmeticException
import java.lang.IllegalArgumentException


open class State
class ErrorState : State()
class ReadyState : State()
class SuccessState : State()

class MainActivity : AppCompatActivity() {
    private val calculator: Calculator = Calculator()
    private lateinit var textField: TextView
    private val errorState = ErrorState()
    private val readyState = ReadyState()
    private val successState = SuccessState()
    private var currentState: State = this.successState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.textField = findViewById(R.id.txtInput)
    }

    private fun getTextField() = this.textField
    private fun handleCurrentState() {
        when (this.currentState) {
            this.errorState -> {
                this.clearInput(); this.setState(this.successState)
            }
            this.readyState -> {
                this.clearInput(); this.setState(this.successState)
            }
        }
    }

    private fun displayErrorText() {
        clearInput()
        addInput("Error")
    }

    private fun addInput(input: String) {
        val textField: TextView = getTextField()
        textField.append(input)
    }

    private fun clearInput() {
        val input: TextView = getTextField()
        input.setText("")
    }

    private fun setState(state: State) {
        this.currentState = state
    }

    fun onDigit(view: View) {
        handleCurrentState()
        val button = view as Button
        this.addInput(button.text as String)
    }

    fun onOperator(view: View) {
        handleCurrentState()
        val button = view as Button
        this.addInput(button.text as String)
    }

    fun onDecimalPoint(view: View) {
        handleCurrentState()
        val button = view as Button
        this.addInput(button.text as String)
    }

    fun onEqual(view: View) {
        val textview = getTextField()
        val text = textview.text.toString()
        val result: Double

        try {
            result = calculator.evaluate(text)
            clearInput()
            addInput(result.toString())
            this.setState(this.readyState)
        } catch (e: IllegalArgumentException) {
            this.setState(this.errorState)
            displayErrorText()
        }catch (e: ArithmeticException){
            this.setState(this.errorState)
            displayErrorText()
        }
    }

    fun onClear(view: View) {
        this.clearInput()
    }
}
