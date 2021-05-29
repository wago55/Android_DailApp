package app.wago.wagochan.dail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.net.toUri
import androidx.core.view.isVisible
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_upload_post.*
import java.io.File

class ActivityUploadPost : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_post)

        loading.setVisibility(View.INVISIBLE)

        val get_pic_uri: String? = intent.getStringExtra(ActivityNewPost.EXTRA_TEXTDATA)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if(get_pic_uri != null){
            val pic_uri: Uri = get_pic_uri.toUri()
            get_imageView.setImageURI(pic_uri)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.top_app_bar2, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        if(item.itemId == android.R.id.home){
            finish()
        }
        if(item.itemId == R.id.upload){

            val get_pic_uri: String? = intent.getStringExtra(ActivityNewPost.EXTRA_TEXTDATA)
            if(get_pic_uri != null){
            //textView.text = get_pic_uri


                get_imageView.setVisibility(View.INVISIBLE)
                loading.setVisibility(View.VISIBLE)

                val pic_uri: Uri = get_pic_uri.toUri()
                val path = pic_uri.getPath()
                val file:File = File(path)
                val storage = FirebaseStorage.getInstance()
                val storageRef = storage.getReferenceFromUrl("gs://dail-bb98e.appspot.com")
                val dir = storageRef.child(file.toString())
                val task = dir.putFile(pic_uri)
                task.addOnSuccessListener {
                    val toMainActivityIntent = Intent(this, MainActivity::class.java)
                    startActivity(toMainActivityIntent)
                    Toast.makeText(applicationContext,"アップロード成功", Toast.LENGTH_SHORT).show()
                }
                task.addOnFailureListener{
                    Toast.makeText(applicationContext,"アップロード失敗", Toast.LENGTH_SHORT).show()
                }
          }



            //val toMainActivityIntent = Intent(this, MainActivity::class.java)
            //startActivity(toMainActivityIntent)
        }
        return super.onOptionsItemSelected(item)
    }
}