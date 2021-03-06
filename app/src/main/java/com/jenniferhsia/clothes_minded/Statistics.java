package com.jenniferhsia.clothes_minded;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.view.View;
import android.graphics.*;
import android.content.Context;

import java.util.*;

public class Statistics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        LinearLayout linear = (LinearLayout) findViewById(R.id.linear);
        linear.addView(new MyGraphview(this, calculateData()));
    }

    private float[] calculateData() {
        double unknowns = 360/100*33*Math.random();
        double nonbiodegradables = 360/100*33*Math.random();
        double biodegradables = 360 - unknowns - nonbiodegradables;
        float[] numbers = {(float)biodegradables, (float)nonbiodegradables, (float)unknowns};
        return numbers;
    }

    public class MyGraphview extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private float[] value_degree;
        private int[] COLORS = {Color.GREEN, Color.RED, Color.BLUE};
        RectF rectf = new RectF(50, 60, 820, 800);
        int temp = 0;

        public MyGraphview(Context context, float[] values) {

            super(context);
            value_degree = new float[values.length];
            for (int i = 0; i < values.length; i++) {
                value_degree[i] = values[i];
            }
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            for (int i = 0; i < value_degree.length; i++) {//values2.length; i++) {
                if (i == 0) {
                    paint.setColor(COLORS[i]);
                    canvas.drawArc(rectf, 0, value_degree[i], true, paint);
                } else {
                    temp += (int) value_degree[i - 1];
                    paint.setColor(COLORS[i]);
                    canvas.drawArc(rectf, temp, value_degree[i], true, paint);
                }
            }
        }
    }
}
