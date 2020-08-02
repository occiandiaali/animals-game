package com.twenty2gig.myanimals

import android.content.Context
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*

// https://www.youtube.com/watch?v=x80D7c9KcjI

class MainActivity : AppCompatActivity() {
    var animals: IntArray = intArrayOf(
        R.drawable.donkey,
        R.drawable.monkey_tail,
        R.drawable.cock,
        R.drawable.pig_pink,
        R.drawable.cow)

    var mp = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView.setImageResource(animals[0])
        gridview.numColumns = animals.size
        gridview.adapter = ImageAdapter(applicationContext)
        
        gridview.setOnItemClickListener { adapterView, view, i, l ->
            imageView.setImageResource(animals[i])
            try {
                var mid = resources.getIdentifier("g" + i, "raw", packageName)
                if (mp.isPlaying)
                    mp.stop()
                mp = MediaPlayer.create(applicationContext, mid)
                mp.start()
            } catch (e: Exception) {}
        }
    } // on create

    inner class ImageAdapter : BaseAdapter {
        var context: Context
        constructor(context: Context) {
            this.context = context
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var iv = ImageView(context)
            iv.setImageResource(animals[p0])
            iv.setLayoutParams(AbsListView.LayoutParams(200, 200))
            return iv
        }

        override fun getItem(p0: Int): Any {
            return p0
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return animals.size
        }
    }
} // class
