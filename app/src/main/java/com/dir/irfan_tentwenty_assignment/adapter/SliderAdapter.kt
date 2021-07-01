package com.dir.irfan_tentwenty_assignment.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.dir.irfan_tentwenty_assignment.R
import com.dir.irfan_tentwenty_assignment.model.images.Poster
import com.dir.irfan_tentwenty_assignment.util.Constants
import kotlinx.android.synthetic.main.image_slider_layout.view.*

class SliderAdapter(var context:Context, val posters: List<Poster>?) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val item = LayoutInflater.from(container.context).inflate(
            R.layout.image_slider_layout, container,
            false)

        var  imgUrl : String = Constants.BASE_URL+ posters!!.get(position).filePath
        Log.e("imgURl ","ImageUrl = " + imgUrl)

        Glide.with(context)
            .load(imgUrl)
            .placeholder(R.drawable.img_poster)
            .into(item.imgBanner);

        container.addView(item)
        return item
    }

    override fun getCount(): Int {
        if(posters!!.size > 5){
            return 5
        }
        return posters!!.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {

        return view === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }


}