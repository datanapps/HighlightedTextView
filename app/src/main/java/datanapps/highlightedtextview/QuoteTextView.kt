package com.app.customtextviewcomponent

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import datanapps.highlightedtextview.R


@SuppressLint("CustomViewStyleable", "Recycle")
class QuoteTextView constructor(
    context: Context,
    readArrayAttributes: AttributeSet?,
    defStyleAttr: Int
) : AppCompatTextView(context, readArrayAttributes, defStyleAttr) {


    constructor(context: Context) : this(context, null)

    constructor(context: Context, readArrayAttributes: AttributeSet?) : this(
        context,
        readArrayAttributes,
        0
    )

    var highLightColor: Int = Color.TRANSPARENT

    init {
        readArrayAttributes?.let {
            val attrs = context.obtainStyledAttributes(
                readArrayAttributes,
                R.styleable.QuoteTextView
            )

            highLightColor =
                attrs.getColor(

                    R.styleable.QuoteTextView_highLightColor,
                    Color.TRANSPARENT
                )
        }
    }

    fun setHightLightedColor(color:Int){
        highLightColor = ContextCompat.getColor(context, color)
        invalidate()

    }

   fun setText(quoteText:String){
       text = quoteText
       invalidate()

   }

    override fun draw(canvas: Canvas) {
        val lineCount = layout.lineCount
        val rect = Rect()
        val paint = Paint()
        paint.color = highLightColor

        for (i in 0 until lineCount) {
            rect.top = layout.getLineTop(i)
            rect.left = layout.getLineLeft(i).toInt()
            rect.right = layout.getLineRight(i).toInt()

            val data = (if (i + 1 == lineCount) 0 else layout.spacingAdd)
            rect.bottom =
                (layout.getLineBottom(i).minus(data.toInt()) - 5)
            canvas.drawRect(rect, paint)
        }
        super.draw(canvas)
    }
}
