package app.wago.wagochan.dail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.net.toUri
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_pic_detail.*

class PicDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pic_detail)

        val get_pic_uri: String? = intent.getStringExtra(ActivityNewPost.EXTRA_TEXTDATA)
        textView.text = get_pic_uri
        val storage = Firebase.storage
//        val listRef = storage.getReferenceFromUrl("gs://dail-bb98e.appspot.com/document/image:20251")
        val listRef = storage.getReferenceFromUrl(get_pic_uri!!)
        GlideApp.with(this)
                .load(listRef)
                .centerCrop()
                .into(get_pre_imageView)
    }
}