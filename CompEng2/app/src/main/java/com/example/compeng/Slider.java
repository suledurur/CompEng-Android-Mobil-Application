package com.example.compeng;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class Slider extends PagerAdapter {
    private Context mcontext;
    private int[] images=new int[]{R.drawable.slider1,R.drawable.slider2,R.drawable.slider3,R.drawable.slider4,R.drawable.slider5,R.drawable.slider6};

    Slider (Context context){
        mcontext=context;
    }
    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
    public Object instantiateItem(ViewGroup container, int position){
        ImageView imageView=new ImageView(mcontext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(images[position]);
        container.addView(imageView,0);
        return imageView;
    }
    @Override
    public void destroyItem(ViewGroup container,int position,Object object){
        container.removeView((ImageView) object);
    }
}


