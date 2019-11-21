package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.constraintlayout.solver.widgets.Rectangle;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.HashMap;
import java.util.Map;


public class ZoneableImageView extends ConstraintLayout {

    private final Map<ProportionalRect, Runnable> mAreaEffects = new HashMap<>();
    private ImageView imageView;

    public ZoneableImageView(Context context) {
        this(context, null);
    }

    public ZoneableImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZoneableImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void init(AttributeSet attrs) {
        View view = View.inflate(getContext(), R.layout.zoneable_image_view, this);
        imageView = view.findViewById(R.id.iv_socle);
        TypedArray attr = getContext().obtainStyledAttributes(attrs, R.styleable.ZoneableImageView);
        Drawable drawable = attr.getDrawable(R.styleable.ZoneableImageView_image1);
        imageView.setImageDrawable(drawable);
        imageView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    int x = (int) event.getX();
                    int y = (int) event.getY();

                    Log.d("onTouchEvent", "x:  " + x);
                    Log.d("onTouchEvent", "y:  " + y);

                    for (Map.Entry<ProportionalRect, Runnable> entry : mAreaEffects.entrySet()) {
                        ProportionalRect key = entry.getKey();
                        Log.d("onTouchEvent", "ProportionalRect:  " + key.getX() + " " + key.getY() + " " + key.getWidth() + " " + key.getHeight());
                        Rectangle rect = key.toRectangle(imageView);
                        Log.d("onTouchEvent", "rect:  " + rect.x + " " + rect.y + " " + rect.width + " " + rect.height);
                        if (rect.contains(x, y)) {
                            entry.getValue().run();
                        }
                    }
                }

                return true;
            }
        });
        attr.recycle();
    }

    public void addAreaEffect(ProportionalRect target, Runnable event) {
        mAreaEffects.put(target, event);
    }

    public void setDrawable(Drawable drawable){
        imageView.setImageDrawable(drawable);
    }


}
