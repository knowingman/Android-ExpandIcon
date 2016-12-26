package com.github.zagum.expandiconview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.github.zagum.expandicon.ExpandIconView;

public class SampleActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

  private ExpandIconView expandIconView1;
  private ExpandIconView expandIconView2;
  private ExpandIconView expandIconView3;
  private View container;
  private GestureDetector gestureDetector;
  private View swipeDetectionView;
  private View click;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sample);

    expandIconView1 = (ExpandIconView) findViewById(R.id.expand_icon1);
    expandIconView2 = (ExpandIconView) findViewById(R.id.expand_icon2);
    expandIconView3 = (ExpandIconView) findViewById(R.id.expand_icon3);

    expandIconView1.setFraction(.5f, false);
    expandIconView2.setFraction(.5f, false);

    container = findViewById(R.id.container);
    container.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        expandIconView1.switchState();
      }
    });

    click = findViewById(R.id.click);
    click.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        expandIconView3.switchState();
      }
    });

    setUpSlidingContainer();
  }

  private void setUpSlidingContainer() {
    gestureDetector = new GestureDetector(this, this);
    gestureDetector.setIsLongpressEnabled(false);

    swipeDetectionView = findViewById(R.id.swipe_detector);
    swipeDetectionView.setClickable(true);
    swipeDetectionView.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
          expandIconView1.setFraction(.5f, true);
          expandIconView2.setFraction(.5f, true);
        }
        return gestureDetector.onTouchEvent(event);
      }
    });
  }

  @Override
  public boolean onDown(MotionEvent e) {
    return false;
  }

  @Override
  public void onShowPress(MotionEvent e) {

  }

  @Override
  public boolean onSingleTapUp(MotionEvent e) {
    return false;
  }

  @Override
  public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
    Log.d("speed", distanceY + "");
    float fraction;
    if (Math.signum(distanceY) > 0) {
      fraction = 1f;
    } else {
      fraction = 0f;
    }
    expandIconView1.setFraction(fraction, true);
    expandIconView2.setFraction(fraction, true);
    return false;
  }

  @Override
  public void onLongPress(MotionEvent e) {

  }

  @Override
  public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
    return false;
  }
}
