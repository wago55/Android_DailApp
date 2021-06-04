package app.wago.wagochan.dail

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
//import com.google.firebase.storage.StorageReference
//import com.google.firebase.storage.ktx.storage

class RecyclerViewAdapter(private val context: Context): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){
    companion object {
        const val EXTRA_TEXTDATA = "picture"
    }

    val items: MutableList<PicByFireBaseData> = mutableListOf()
//    val itemsRight: MutableList<PicByFireBaseDataRight> = mutableListOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var picLeft: ImageButton = view.findViewById(R.id.imageButton_left)
        var picRight: ImageButton = view.findViewById(R.id.imageButton_right)

        private var view: View = view
        fun bind(listener: View.OnClickListener,item: PicByFireBaseData ){

            view.setOnClickListener(listener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
                LayoutInflater.from(context).inflate(R.layout.item_picbyfirebase_data_cell, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
//        val itemRight = itemsRight[position]
        GlideApp.with(context).load(item.picUriLeft).override(280,500).into(holder.picLeft)
        GlideApp.with(context).load(item.picUriRight).override(280,500).into(holder.picRight)

        holder.picRight.setOnClickListener{
            val intent = Intent(context, PicDetailActivity::class.java)
            intent.putExtra(EXTRA_TEXTDATA, item.picUriRight.toString())
            context.startActivity(intent)
            Toast.makeText(it.context, "Clicked ${item.picUriRight}", Toast.LENGTH_SHORT).show()
        }

        holder.picLeft.setOnClickListener{
            val intent = Intent(context, PicDetailActivity::class.java)
            intent.putExtra(EXTRA_TEXTDATA, item.picUriLeft.toString())
            context.startActivity(intent)
            Toast.makeText(it.context, "Clicked ${item.picUriLeft}", Toast.LENGTH_SHORT).show()
        }
//        holder.picRight = item.picUriRight
//        holder.picLeft.setImageResource(item.picUriLeft)
//        holder.picRight.setImageResource(item.picUriRight)

    }

    fun addAll(items: MutableList<PicByFireBaseData>){
        this.items.addAll(items)
        notifyDataSetChanged()
    }



//    fun addRight(items: MutableList<PicByFireBaseDataRight>){
//        this.itemsRight.addAll(items)
//        notifyDataSetChanged()
//    }


    override fun getItemCount(): Int {
        return items.size
    }
}