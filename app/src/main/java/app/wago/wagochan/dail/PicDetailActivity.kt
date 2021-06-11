package app.wago.wagochan.dail
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import app.wago.wagochan.dail.BuildConfig.DEBUG
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
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
//                            .fitCenter()
//                            .override(400,300)
                            .centerCrop()
                            .into(get_pre_imageView)
//                       textView2.text = get_pic_uri
                        val uriList: MutableList<String> = mutableListOf()
                        val db = FirebaseFirestore.getInstance()
                        db.collection("caption")
                            .get()
                            .addOnSuccessListener { result ->
                                for(document in result){
//                                    Log.d(TAG, "${document.id} => ${document.data.get("uri")}")
                                    var check: String = document.data.get("uri").toString().removePrefix("content://com.android.providers.media.documents/document/image%3A")
                                    var check2: String = item.toString().removePrefix("gs://dail-bb98e.appspot.com/document/image%3A")
//                                    uriList.add(document.data.get("uri").toString()
                                    if(check == check2){
                                        textView2.text = document.data.get("caption").toString()
                                    }
//                                    textView2.text = check2
//                                    Log.d(TAG, check2)
                                }
//                                textView2.text = uriList.toString()
                            }

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
