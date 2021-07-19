package com.example.tegeta.utils

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.FontMetricsInt
import android.text.style.ReplacementSpan

class NumberInputReplacementSpan(private val replaceString: String) : ReplacementSpan() {
    override fun getSize(
        paint: Paint,
        text: CharSequence,
        start: Int,
        end: Int,
        fm: FontMetricsInt?
    ): Int {
        val space = paint.measureText(replaceString, 0, 1)
        val textSize = paint.measureText(text, start, end)
        return (space + textSize).toInt()
    }

    override fun draw(
        canvas: Canvas, text: CharSequence, start: Int, end: Int, x: Float, top: Int, y: Int,
        bottom: Int, paint: Paint
    ) {
        canvas.drawText(
            text.subSequence(start, end).toString() + replaceString,
            x,
            y.toFloat(),
            paint
        )
    }
}