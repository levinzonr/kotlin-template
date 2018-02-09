package dk.eboks.app.presentation.ui.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;

import dk.eboks.app.R;

public class MaxHeightScrollView extends NestedScrollView {

private int maxHeight;
private final int defaultHeight = 300;

public MaxHeightScrollView(Context context) {
    super(context);
}

public MaxHeightScrollView(Context context, AttributeSet attrs) {
    super(context, attrs);
    if (!isInEditMode()) {
        init(context, attrs);
    }
}

public MaxHeightScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    if (!isInEditMode()) {
        init(context, attrs);
    }
}

private void init(Context context, AttributeSet attrs) {
    if (attrs != null) {
        TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.MaxHeightScrollView);
        //200 is a defualt value
        int defMaxHeight = context.getResources().getDisplayMetrics().heightPixels;
        defMaxHeight = defMaxHeight - (defMaxHeight/4);
        maxHeight = styledAttrs.getDimensionPixelSize(R.styleable.MaxHeightScrollView_maxHeight, defMaxHeight);
        styledAttrs.recycle();
    }
}

@Override
protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    heightMeasureSpec = MeasureSpec.makeMeasureSpec(maxHeight, MeasureSpec.AT_MOST);
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
}
}