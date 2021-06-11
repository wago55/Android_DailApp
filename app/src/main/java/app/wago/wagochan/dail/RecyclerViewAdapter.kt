package app.wago.wagochan.dail

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.core.view.PointerIconCompat.load
import androidx.recyclerview.widget.RecyclerView
import app.wago.wagochan.dail.ActivityNewPost.Companion.EXTRA_TEXTDATA
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_main.*
//import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
//import com.google.firebase.storage.ktx.storage
import java.lang.System.load

class RecyclerViewAdapter(private val context: Context): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){

    val items: MutableList<PicByFireBaseData> = mutableListOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var picLeft: ImageButton = view.findViewById(R.id.imageButton_left)
        var picRight: ImageButton = view.findViewById(R.id.imageButton_right)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
                LayoutInflater.from(context).inflate(R.layout.item_picbyfirebase_data_cell, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        GlideApp.with(context).load(item.picUriLeft).override(500,400).centerCrop().into(holder.picLeft)
        GlideApp.with(context).load(item.picUriRight).override(500,400).centerCrop().into(holder.picRight)
//        GlideApp.with(context).load(item.picUriLeft).into(holder.picLeft)
//        GlideApp.with(context).load(item.picUriRight).into(holder.picRight)
//        holder.picRight = item.picUriRight
//        holder.picLeft.setImageResource(item.picUriLeft)
//        holder.picRight.setImageResource(item.picUriRight)
        holder.picRight.setOnClickListener{
            val intent = Intent(context, PicDetailActivity::class.java)
            intent.putExtra(EXTRA_TEXTDATA, item.picUriRight.toString())
            context.startActivity(intent)
//            Toast.makeText(it.context, "Clicked ${item.picUriRight}, ${position}", Toast.LENGTH_SHORT).show()
        }

        holder.picLeft.setOnClickListener{
            val intent = Intent(context, PicDetailActivity::class.java)
            intent.putExtra(EXTRA_TEXTDATA, item.picUriLeft.toString())
            context.startActivity(intent)
//            Toast.makeText(it.context, "Clicked ${item.picUriLeft}, ${position}", Toast.LENGTH_SHORT).show()
        }

    }

    fun addAll(items: MutableList<PicByFireBaseData>){
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }
}