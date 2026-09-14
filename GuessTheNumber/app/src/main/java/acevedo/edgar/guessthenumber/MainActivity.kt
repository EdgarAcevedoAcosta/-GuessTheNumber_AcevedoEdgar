package acevedo.edgar.guessthenumber

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    var minValue=0
    var maxValue=100
    var num: Int = 0
    var won = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val gussings: TextView = findViewById(R.id.guessings)
        val down: Button = findViewById(R.id.down)
        val up: Button = findViewById(R.id.up)
        val generate: Button = findViewById(R.id.generate)
        val guessed: Button = findViewById(R.id.guessed)

        generate.setOnClickListener {
            num = kotlin.random.Random.nextInt(minValue, maxValue)
            gussings.text = num.toString()
            generate.visibility = android.view.View.INVISIBLE
            guessed.visibility = android.view.View.VISIBLE
        }
        up.setOnClickListener {
            minValue = num
            if (checkingLimits()) {
                num = kotlin.random.Random.nextInt(minValue, maxValue)
                gussings.setText(num.toString())
            } else {
                num = kotlin.random.Random.nextInt(minValue, maxValue)
                gussings.setText("No puede ser:(me ganaste)")
            }
        }
        down.setOnClickListener {
            maxValue = num
            if (checkingLimits()) {
                num = kotlin.random.Random.nextInt(minValue, maxValue)
                gussings.setText(num.toString())
            } else{
                gussings.setText("No puede ser:(me ganaste)")
            }
        }

        guessed.setOnClickListener {
            if(!won) {
                gussings.text = "Adiviné, tu número es el "+num
                guessed.text = "play again"
                won = true
            }else{
                generate.visibility = android.view.View.VISIBLE
                gussings.text = "Tap on generate to start"
                guessed.text = "Guessed"
                guessed.visibility = android.view.View.GONE
                resetValues()
            }
        }

    }

    fun resetValues(){
        minValue=0
        maxValue=100
        num=0
        won=false
    }


    fun checkingLimits():Boolean{
        return minValue!=maxValue
    }
}