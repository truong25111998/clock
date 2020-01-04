package com.phuong.customview1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.SystemClock
import android.util.AttributeSet
import android.view.View
import java.text.SimpleDateFormat
import java.util.*

class btvn1 : View {
    private var  isRunningTime = false
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onDraw(canvas: Canvas){
        super.onDraw(canvas)
        var p = Paint()
        p.style = Paint.Style.STROKE
        p.isAntiAlias = true
        p.color = Color.parseColor("#FFCA28")
        p.strokeWidth=30f
        canvas.drawCircle(width.toFloat()/2 ,height.toFloat()/2 ,width.toFloat()/2 -15f,p)
        p.style = Paint.Style.FILL
        p.color = Color.parseColor("#212121")
        canvas.drawCircle(width.toFloat()/2 ,height.toFloat()/2 ,width.toFloat()/2-25f,p)
        p.textSize= 50f
        p.strokeWidth= 4f
        p.color = Color.parseColor("#D50000")
        canvas.drawText("Rolex",340f,100f,p)
        p.color = Color.parseColor("#00E5FF")
        val curenDate = Date() // lấy thời gian hiện tại của điện thoại
        ///
        p.textSize = 50f
        p.color =  Color.parseColor("#FAFAFA")
        p.strokeWidth=3.5f
        val formatDate = SimpleDateFormat("dd/MM/yyyy")
        val textDate = formatDate.format(curenDate)
        canvas?.drawText(textDate,width.toFloat()/3,600.0f,p)
///
        val formatTime = SimpleDateFormat("mm")
        val Time = formatTime.format(curenDate).toFloat()
        val t = Time/60 * 360
        p.style = Paint.Style.STROKE
        p.color = Color.parseColor("#00E5FF")
        p.strokeWidth=10f
        canvas.rotate(t,width.toFloat()/2,height.toFloat()/2-15f)
        canvas.drawRect(width.toFloat()/2,70f,width.toFloat()/2,370f,p)
        p.style = Paint.Style.STROKE
        p.isAntiAlias = true
        p.color = Color.parseColor("#0D47A1")
        p.strokeWidth=10f
        canvas.rotate(-t,width.toFloat()/2,height.toFloat()/2-15f)
        val a = Date()
        val formatTime2 = SimpleDateFormat("HH")
        val Time2 = (formatTime2.format(a)).toFloat()

//        ///
        canvas.rotate(Time2*30,width.toFloat()/2,height.toFloat()/2-15f)
        canvas.drawRect(width.toFloat()/2,150f,width.toFloat()/2,370f,p)

        ////
        canvas.rotate(-(Time2*30),width.toFloat()/2,height.toFloat()/2-15f)
        val formatTime3 = SimpleDateFormat("ss")
        val Time3 = (formatTime3.format(a)).toFloat()
        val t2 = Time3/60 * 360
        p.style = Paint.Style.STROKE
        p.color = Color.parseColor("#D50000")
        p.strokeWidth=10f
        canvas.rotate(t2,width.toFloat()/2,height.toFloat()/2-15f)
        canvas.drawRect(width.toFloat()/2,30f,width.toFloat()/2,370f,p)


    }

    override fun onAttachedToWindow() { //khi đc hiển thị trên giao diện
        super.onAttachedToWindow()
        createThread()
    }

    override fun onDetachedFromWindow() {//khi không đc hiển thị trên giao diện
        super.onDetachedFromWindow()
        isRunningTime=false
    }
    // cập nhật giao diện phải cập nhật trên main thread
    private fun createThread(){
        isRunningTime= true
        val run = Runnable {
            while (isRunningTime){
                invalidate()// tương tự repaint trong java
                postInvalidate()
                SystemClock.sleep(100)
            }
        }
        val th = Thread(run)
        th.start()
    }
}