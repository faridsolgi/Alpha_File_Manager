package ir.glorysys.alphafilemanager

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class Adapter(var list: MutableList<String>,var onItemClickListener: FileAdapterViewHolder.onItemClickListener) :
    RecyclerView.Adapter<Adapter.FileAdapterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        var view: View = layoutInflater.inflate(R.layout.item_adapter, parent, false)
        return FileAdapterViewHolder(view,onItemClickListener)
    }

    override fun onBindViewHolder(holder: FileAdapterViewHolder, position: Int) {
      holder.bindData(list.get(position))
    }

    override fun getItemCount(): Int = list.size


    class  FileAdapterViewHolder(itemView: View,var onItemClick: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        var textView: TextView? = null
        var button: Button? = null
        init {
            textView=itemView.findViewById(R.id.textView_item_name)
            button=itemView.findViewById(R.id.Folder)
        }

        fun bindData(name:String){
            var fix=name
            var fix1 =fix.substringAfterLast("/")
            textView?.setText(fix1)
            val file1= File(name)

            val existDir:Boolean=file1.isDirectory
            val existfile:Boolean=file1.isFile
            if (existDir){
                button?.background=itemView.resources.getDrawable(R.drawable.ic_baseline_folder_24)
            }else if(existfile){
                button?.background=itemView.resources.getDrawable(R.drawable.ic_baseline_wallpaper_24)
            }else{
                button?.background=itemView.resources.getDrawable(R.drawable.ic_baseline_spa_24)

            }

            button?.setOnClickListener{
                onItemClick.onItemClick(name)
            }
        }

   interface onItemClickListener{
            fun onItemClick(fullDirectory: String)
        }
    }


}

