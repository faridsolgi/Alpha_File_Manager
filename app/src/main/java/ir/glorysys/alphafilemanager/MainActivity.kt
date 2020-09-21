package ir.glorysys.alphafilemanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.lang.NullPointerException

class MainActivity : AppCompatActivity(),Adapter.FileAdapterViewHolder.onItemClickListener {
    var lastdir:String? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var i:Int=0
         var sd = Environment.getExternalStorageDirectory().path.toString()
        //var sd=getFilesDir().toString()
        var directory=File(sd)
        var file: Array<File>? = directory.listFiles()

        Log.d("debugfile", "Size: ${file?.size}")
        Log.i("debugfile","${sd}")
        var list:MutableList<String> = mutableListOf()
        while (i< file!!.size){
            list.add(file.get(i).toString())
            Log.i("debugfile","${file.get(i)}")
            i++
        }
        val adapter=Adapter(list,this)
        recyclerView_main_files.layoutManager=GridLayoutManager(applicationContext,2)
        //recyclerView_main_files.layoutManager=LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
        recyclerView_main_files.adapter=adapter

        textView_main_path.text=sd
       button_main_back.setOnClickListener{
            var i=0
          lastdir= textView_main_path.text.toString().substringBeforeLast("/")
            var directory=File(lastdir)
            var file: Array<File>? = directory.listFiles()
            Log.i("debugfile","${file?.size}")
            var list:MutableList<String> = mutableListOf()
            try {
                while (i < file!!.size) {
                    list.add(file.get(i).toString())
                    Log.i("debugfile", "${file.get(i)}")
                    i++
                }
                val adapter = Adapter(list, this)
                recyclerView_main_files.adapter = adapter
                adapter.notifyDataSetChanged()
            }catch (e:NullPointerException){e.message}

           val file1=File(lastdir)

           val existDir:Boolean=file1.isDirectory
           if (existDir){
               textView_main_path.text=lastdir
           }

        }

    }

    override fun onItemClick(name: String) {
        val dir ="$name/"
        val lastDir=File(name)

        val existDir:Boolean=lastDir.isDirectory
        if (existDir){
            textView_main_path.text=name
        }
        var i=0
        var directory=File(dir)
        var file: Array<File>? = directory.listFiles()
        Log.i("debugfile","${file?.size}")
        var list:MutableList<String> = mutableListOf()
        try {
            while (i < file!!.size) {
                list.add(file.get(i).toString())
                Log.i("debugfile", "${file.get(i)}")
                i++
            }
            val adapter = Adapter(list, this)
            recyclerView_main_files.adapter = adapter
            adapter.notifyDataSetChanged()
        }catch (e:NullPointerException){e.message}

    }

    fun back(){

    }

}