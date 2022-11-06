package com.example.quiz

import android.os.Build
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.example.quiz.databinding.ActivityMainBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var result = 0;
        binding.submitBtn.setOnClickListener{
            val builder=AlertDialog.Builder(this)
            builder.setTitle("Results")
            val dateTimeCurrent = DateTimeFormatter.ofPattern("yyyy-MMM-dd hh:mm:ss").format(LocalDateTime.now());
            if(binding.radioButtonA.isChecked)
                result+=50
            if(binding.checkBox.isChecked)
                result+=50
            when(result){
                0 -> builder.setMessage("You submitted on $dateTimeCurrent. You got 0 answers correct, try again when you have learned more!")
                50 -> builder.setMessage("Congratulations! You submitted on $dateTimeCurrent. You got One answer correct!")
                100 -> builder.setMessage("Super Congratulations! You submitted on $dateTimeCurrent. You got Both answers correct!")
            }
            builder.setPositiveButton("Dismiss"){buildInterface,which-> buildInterface.dismiss()}
            val dialog = builder.create()
            dialog.show()
            dialog.setOnDismissListener { result=resetClicked() }
        }
        binding.resetBtn.setOnClickListener {
            result = resetClicked()
        }

    }

    fun resetClicked():Int {
        binding.radioGaga.clearCheck();
        binding.checkBox.isChecked = false;
        binding.checkBox2.isChecked = false;
        binding.checkBox3.isChecked = false;
        binding.checkBox4.isChecked = false;
        return 0;
    }
}