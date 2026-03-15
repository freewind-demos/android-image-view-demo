package demos

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val imageView = findViewById<ImageView>(R.id.imageView)
        imageView.setImageResource(android.R.drawable.ic_menu_gallery)
    }
}
