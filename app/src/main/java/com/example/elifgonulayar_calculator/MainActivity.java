package com.example.elifgonulayar_calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity
{

    TextView calculationsTV;
    TextView resultsTV;

    String calculation = "";
    String formula = "";
    String tempFormula = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTextViews();
    }

    private void initTextViews()
    {
        calculationsTV = (TextView)findViewById(R.id.workingsTextView);
        resultsTV = (TextView)findViewById(R.id.resultTextView);
    }

    private void setWorkings(String givenValue)
    {
        calculation = calculation + givenValue;
        calculationsTV.setText(calculation);
    }


    public void equal(View view)
    {
        Double result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        checkForPowerOf();

        try {
            result = (double)engine.eval(formula);
        } catch (ScriptException e)
        {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }

        if(result != null)
            resultsTV.setText(String.valueOf(result.doubleValue()));

    }

    private void checkForPowerOf()
    {
        ArrayList<Integer> indexOfPowers = new ArrayList<>();
        for(int i = 0; i < calculation.length(); i++)
        {
            if (calculation.charAt(i) == '^')
                indexOfPowers.add(i);
        }

        formula = calculation;
        tempFormula = calculation;
        for(Integer index: indexOfPowers)
        {
            changeFormula(index);
        }
        formula = tempFormula;
    }

    private void changeFormula(Integer index)
    {
        String numberLeft = "";
        String numberRight = "";

        for(int i = index + 1; i< calculation.length(); i++)
        {
            if(isNumeric(calculation.charAt(i)))
                numberRight = numberRight + calculation.charAt(i);
            else
                break;
        }

        for(int i = index - 1; i >= 0; i--)
        {
            if(isNumeric(calculation.charAt(i)))
                numberLeft = numberLeft + calculation.charAt(i);
            else
                break;
        }

        String original = numberLeft + "^" + numberRight;
        String changed = "Math.pow("+numberLeft+","+numberRight+")";
        tempFormula = tempFormula.replace(original,changed);
    }

    private boolean isNumeric(char c)
    {
        if((c <= '9' && c >= '0') || c == '.')
            return true;

        return false;
    }


    public void clear(View view)
    {
        calculationsTV.setText("");
        calculation = "";
        resultsTV.setText("");
        leftBracket = true;
    }

    boolean leftBracket = true;

    public void bracket(View view)
    {
        if(leftBracket)
        {
            setWorkings("(");
            leftBracket = false;
        }
        else
        {
            setWorkings(")");
            leftBracket = true;
        }
    }

    public void power_Of(View view)
    {
        setWorkings("^");
    }

    public void divided(View view)
    {
        setWorkings("/");
    }

    public void seven(View view)
    {
        setWorkings("7");
    }

    public void eight(View view)
    {
        setWorkings("8");
    }

    public void nine(View view)
    {
        setWorkings("9");
    }

    public void multiply(View view)
    {
        setWorkings("*");
    }

    public void four(View view)
    {
        setWorkings("4");
    }

    public void five(View view)
    {
        setWorkings("5");
    }

    public void six(View view)
    {
        setWorkings("6");
    }

    public void subtract(View view)
    {
        setWorkings("-");
    }

    public void one(View view)
    {
        setWorkings("1");
    }

    public void two(View view)
    {
        setWorkings("2");
    }

    public void three(View view)
    {
        setWorkings("3");
    }

    public void add(View view)
    {
        setWorkings("+");
    }

    public void decimal(View view)
    {
        setWorkings(".");
    }

    public void zero(View view)
    {
        setWorkings("0");
    }

}