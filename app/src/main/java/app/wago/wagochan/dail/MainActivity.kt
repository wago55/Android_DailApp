package app.wago.wagochan.dail

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.module.AppGlideModule
import com.firebase.ui.storage.images.FirebaseImageLoader
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_new_post.*
import java.io.InputStream
import com.bumptech.glide.annotation.GlideModule

import com.google.firebase.storage.ktx.component1
import com.google.firebase.storage.ktx.component2
import kotlinx.android.synthetic.main.item_picbyfirebase_data_cell.*

//import com.google.firebase.storage.StorageReference

class MainActivity : AppCompatActivity() {

    val readRequestCode: Int = 42

    val PicByFireBaseData: MutableList<PicByFireBaseData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        add_picture_button.setOnClickListener{
            val toSecondActivityIntent = Intent(this, ActivityNewPost::class.java)
            startActivity(toSecondActivityIntent)
        }



//        imageButton_right.setOnClickListener{
//            val toSecondActivityIntent = Intent(this, ActivityNewPost::class.java)
//            startActivity(toSecondActivityIntent)
//        }
//
//        imageButton_left.setOnClickListener{
//            val toSecondActivityIntent = Intent(this, ActivityNewPost::class.java)
//            startActivity(toSecondActivityIntent)
//        }

        //val storage = FirebaseStorage.getInstance()
        //val storageRef = storage.reference
        //val dir = storageRef.child("document/image:19934")

        //get_pic_out.setImageURI(dir.)

        //if(dir != null){
         //   Toast.makeText(applicationContext,"読み込んでるよ ${dir}", Toast.LENGTH_SHORT).show()
        //}





        val storage = Firebase.storage
        //var PicByFireBaseData: List<PicByFireBaseData> = listOf(
         //       PicByFireBaseData()
        //)
//        var storageRef: StorageReference? = null
//        while (storageRef != null){
        //   storageRef = storage.getReferenceFromUrl("gs://dail-bb98e.appspot.com/document/image:\\*")
        //}
        val ONE_MEGABYTE: Long = 1024 * 1024
        val adapter = RecyclerViewAdapter(this)
        recycle_pic_view.layoutManager = LinearLayoutManager(this)
        recycle_pic_view.adapter = adapter

//        val PicByFireBaseData: MutableList<PicByFireBaseData> = mutableListOf()

//       val storageRef = storage.getReferenceFromUrl("gs://dail-bb98e.appspot.com/document/image:20251")
        val listRef = storage.getReferenceFromUrl("gs://dail-bb98e.appspot.com/document/")
        listRef.listAll()
                .addOnSuccessListener { (items, prefixes) ->
                    prefixes.forEach { prefix ->
//                        PicByFireBaseData.add(prefix)
//                        textView.text = PicByFireBaseData.toString()

//                         All the prefixes under listRef.
//                         You may call listAll() recursively on them.
                    }

                    items.forEach { item ->
                        //textView.text = item.path
                        PicByFireBaseData.add(PicByFireBaseData(item,item))
                       // textView.text = PicByFireBaseData.toString()
//                         All the items under listRef.
//                        textView.text = PicByFireBaseData.toString()
//                            GlideApp.with(this)
//                                    .load("gs://dail-bb98e.appspot.com/document/image:20251")
//                                    .centerCrop()
//                                    .into(imageView4)
//                        adapter.addAll(PicByFireBaseData)

                    }
                    adapter.addAll(PicByFireBaseData)
                  //  textView.text =
                }
                .addOnFailureListener {
                    // Uh-oh, an error occurred!

                }
//        textView.text = PicByFireBaseData.toString()


//        adapter.addAll(PicByFireBaseData)



        //val dir = storageRef.child("document/image:19934")

       // val ONE_MEGABYTE: Long = 1024 * 1024
        //storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener {
           // val imageView = findViewById<ImageView>(R.id.get_pic_out)
           // GlideApp.with(this)
            //    .load(storageRef)
              //  .centerCrop()
                //.into(get_pic_out)
        //}.addOnFailureListener {
            // Handle any errors
            //Log.e("TAG", it)
        //}
    }
}





