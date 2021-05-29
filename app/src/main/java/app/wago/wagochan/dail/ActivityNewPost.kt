package app.wago.wagochan.dail

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_new_post.*
import java.time.temporal.TemporalAdjusters.next

class ActivityNewPost : AppCompatActivity() {

    companion object {
        const val EXTRA_TEXTDATA = "picture"
    }

    val readRequestCode: Int = 42
    var pic_url: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_post)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        choose_picture.setOnClickListener {
            val galleryIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            galleryIntent.addCategory(Intent.CATEGORY_OPENABLE)
            galleryIntent.type = "image/*"
            startActivityForResult(galleryIntent, readRequestCode)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.top_app_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        if(item.itemId == android.R.id.home){
            finish()
        }
        if(item.itemId == R.id.next_page){

            val toActivityUploadPostIntent = Intent(this, ActivityUploadPost::class.java)
            toActivityUploadPostIntent.putExtra(EXTRA_TEXTDATA, pic_url)
            startActivity(toActivityUploadPostIntent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)
        if (requestCode == readRequestCode && resultCode == Activity.RESULT_OK) {
            resultData?.data?.also { uri ->imageView.setImageURI(uri)
                pic_url = uri.toString()
            }
        }
    }
}
