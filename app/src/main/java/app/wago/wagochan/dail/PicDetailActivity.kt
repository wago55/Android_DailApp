package app.wago.wagochan.dail
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_pic_detail.*
import kotlin.math.log
import com.google.firebase.storage.ktx.component1
import com.google.firebase.storage.ktx.component2

class PicDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pic_detail)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        val get_pre_imageView: ImageView = findViewById(R.id.get_pre_imageView)

        val get_pic_uri: String? = intent.getStringExtra(ActivityNewPost.EXTRA_TEXTDATA)
        var picRefernce: StorageReference?
//        textView.text = get_pic_uri
        val storageRef = Firebase.storage
        val listRef = storageRef.getReferenceFromUrl("gs://dail-bb98e.appspot.com/document/")
//        val imageRef = storageRef.getReferenceFromUrl("gs://dail-bb98e.appspot.com/document/image:20251")
        //val imageRef = storageRef.getReferenceFromUrl(get_pic_uri!!)

        listRef.listAll()
            .addOnSuccessListener { (items, prefixes) ->
                prefixes.forEach { prefix ->
                }

                items.forEach { item ->
                    if(item.toString() == get_pic_uri){
                        picRefernce = item
                        GlideApp.with(this)
                            .load(picRefernce)
                            .centerCrop()
                            .into(get_pre_imageView)
                    }

                }

            }
            .addOnFailureListener {

            }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
