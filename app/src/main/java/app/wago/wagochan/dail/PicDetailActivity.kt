package app.wago.wagochan.dail
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_pic_detail.*
import kotlin.math.log
import com.google.firebase.storage.ktx.component1
import com.google.firebase.storage.ktx.component2

class PicDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pic_detail)

        val get_pre_imageView: ImageView = findViewById(R.id.get_pre_imageView)

        val get_pic_uri: String? = intent.getStringExtra(ActivityNewPost.EXTRA_TEXTDATA)
        textView.text = get_pic_uri
        val storageRef = Firebase.storage
        val imageRef = storageRef.getReferenceFromUrl("gs://dail-bb98e.appspot.com/document/image:19934")
//        val listRef = storage.getReferenceFromUrl(get_pic_uri!!)
        GlideApp.with(this)
            .load(imageRef)
            .centerCrop()
            .into(get_pre_imageView)
    }
}
