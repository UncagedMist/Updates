package com.tbc.uncagedmist.cricketupdates.liveline;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class DigitalTextView extends androidx.appcompat.widget.AppCompatTextView {
    public DigitalTextView(Context context) {
        super(context);
        m27312a();
    }

    public DigitalTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m27312a();
    }

    public DigitalTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m27312a();
    }

    /* renamed from: a */
    private void m27312a() {
        if (!isInEditMode()) {
            setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/fallingsky.ttf"));
        }
    }
}
