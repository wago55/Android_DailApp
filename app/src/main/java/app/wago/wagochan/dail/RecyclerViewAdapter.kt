package app.wago.wagochan.dail

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.core.view.PointerIconCompat.load
import androidx.recyclerview.widget.RecyclerView
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
        GlideApp.with(context).load(item.picUriLeft).override(280,500).into(holder.picLeft)
        GlideApp.with(context).load(item.picUriRight).override(280,500).into(holder.picRight)
//        holder.picRight = item.picUriRight
//        holder.picLeft.setImageResource(item.picUriLeft)
//        holder.picRight.setImageResource(item.picUriRight)
    }

    fun addAll(items: MutableList<PicByFireBaseData>){
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }
}