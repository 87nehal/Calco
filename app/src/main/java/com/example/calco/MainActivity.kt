package com.example.calco

import android.app.AlertDialog
import android.app.Dialog
import android.content.*
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.calco.databinding.ActivityMainBinding
import com.github.keelar.exprk.Expressions


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // API 21
            binding.input.setShowSoftInputOnFocus(false);
        } else { // API 11-20
            binding.input.setTextIsSelectable(true);
        }
        binding.back.text="<";
        binding.details.setOnClickListener{
            showDialog("Calco");
        }
        binding.num0.setOnClickListener {
            binding.input.setText(binding.input.text.toString()+"0");
        }
        binding.num1.setOnClickListener {
            binding.input.setText(binding.input.text.toString()+"1");
        }
        binding.num2.setOnClickListener {
            binding.input.setText(binding.input.text.toString()+"2");
        }
        binding.num3.setOnClickListener {
            binding.input.setText(binding.input.text.toString()+"3");
        }
        binding.num4.setOnClickListener {
            binding.input.setText(binding.input.text.toString()+"4");
        }
        binding.num5.setOnClickListener {
            binding.input.setText(binding.input.text.toString()+"5");
        }
        binding.num6.setOnClickListener {
            binding.input.setText(binding.input.text.toString()+"6");
        }
        binding.num7.setOnClickListener {
            binding.input.setText(binding.input.text.toString()+"7");
        }
        binding.num8.setOnClickListener {
            binding.input.setText(binding.input.text.toString()+"8");
        }
        binding.num9.setOnClickListener {
            binding.input.setText(binding.input.text.toString()+"9");
        }
        binding.cls.setOnClickListener {
            binding.input.setText("");
            binding.output.text="";
        }
        binding.back.setOnClickListener {
            try {
                var str = binding.input.text.toString();
                str = str.substring(0, str.length - 1);
                binding.input.setText(str);
            }catch (e:Exception){
                binding.input.setText("");
            }
        }
        binding.copy.setOnClickListener{
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Calco",binding.output.text.toString())
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "Answer Copied", Toast.LENGTH_SHORT).show()
        }
        binding.div.setOnClickListener {
            binding.input.setText(binding.input.text.toString()+"/");
        }
        binding.multi.setOnClickListener {
            binding.input.setText(binding.input.text.toString()+"*");
        }
        binding.plus.setOnClickListener {
            binding.input.setText(binding.input.text.toString()+"+");
        }
        binding.minus.setOnClickListener {
            binding.input.setText(binding.input.text.toString()+"-");
        }
        binding.dot.setOnClickListener {
            binding.input.setText(binding.input.text.toString()+".");
        }
        binding.per.setOnClickListener {
            binding.input.setText(binding.input.text.toString()+"%");
        }
        binding.equal.setOnClickListener {
            binding.output.text = calcexp(binding.input.text.toString());
        }
        binding.pow.setOnClickListener {
            binding.input.setText(binding.input.text.toString()+"^");
        }
        binding.openb.setOnClickListener {
            binding.input.setText(binding.input.text.toString()+"(");
        }
        binding.closeb.setOnClickListener {
            binding.input.setText(binding.input.text.toString()+")");
        }
    }

    //credits https://github.com/Keelar/ExprK
    private fun calcexp(str: String): String {
        try {
            return Expressions().eval(str).toString()
        }catch (e:java.lang.ArithmeticException){
            return "Arithmetic Error"
        }catch (z:Exception){
            return "Syntax Error"
        }
    }
    private fun showDialog(title: String) {
        val openURL = Intent(Intent.ACTION_VIEW)
        val builder = AlertDialog.Builder(this)
        //set title for alert dialog
        builder.setTitle(title)
        //set message for alert dialog
        builder.setMessage("Developed By @87nehal \nCredits : https://github.com/Keelar")
        builder.setIcon(R.drawable.calc)
        builder.setPositiveButton("Github"){dialogInterface, which ->
            openURL.data = Uri.parse("https://www.github.com/87nehal")
            startActivity(openURL)
        }
        builder.setNegativeButton("Instagram"){dialogInterface, which ->
            openURL.data = Uri.parse("https://www.instagram.com/87nehal")
            startActivity(openURL)
        }
        builder.setNeutralButton("Elabs"){dialogInterface, which ->
            openURL.data = Uri.parse("https://www.instagram.com/elabs.kiit")
            startActivity(openURL)
        }
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(true)
        alertDialog.show()
    }
}