package com.tbc.uncagedmist.cricketupdates.liveline;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class MyTextView extends androidx.appcompat.widget.AppCompatTextView {
    public MyTextView(Context context) {
        super(context);
        m27313a();
    }

    public MyTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m27313a();
    }

    public MyTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m27313a();
    }

    /* renamed from: a */
    private void m27313a() {
        if (!isInEditMode()) {
            setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/fallingskybold.ttf"));
        }
    }
}
