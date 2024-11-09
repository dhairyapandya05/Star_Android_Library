package com.example.ratinglibrary

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class RatingView: CustomViewScaffold {
    interface OnTouchInsideListener {
        fun onDragInside(x: Float, y: Float)
        fun onClickInside(x: Float, y: Float)
    }

    var onTouchInsideListener: OnTouchInsideListener? = null

    private val starPaintActive = Paint().apply {
        color = Color.argb(255, 255,184, 28)
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    private val starPaintInActive = Paint().apply {
        color = Color.argb(255, 255, 224, 156)
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    private var path = Path()
    private var path2 = Path()
    private var path3 = Path()
    private var path4 = Path()
    private var path5 = Path()
    private val paths = listOf(path, path2, path3, path4, path5)

    private var isDragged=false;
    private var lastMotionEventX=0f;

    val starXCoordinates = mutableListOf<Float>()
    var selectedStar:Int=0;
    private val starPath = Path()
    private val starCount = 5 // Total stars
    private val outerRadius = 80f // Outer radius of the star
    private val innerRadius = 40f // Inner radius of the star
    private val spacing = 40f // Space between stars
    private var startX = 0f // Starting X position for drawing stars

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        // Calculate initial position to center stars horizontally
        val totalWidth = starCount * (2 * outerRadius) + (starCount - 1) * spacing
        startX = (width - totalWidth) / 2f // Center-align the stars horizontally
        invalidate() // Redraw the view
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        var currentX = startX

        // Draw each star at the calculated position
        for (i in 0 until starCount) {
            drawStarPath(paths[i], currentX + outerRadius, height / 2f, outerRadius, innerRadius)

            drawingStars(canvas,selectedStar)
//            canvas.drawPath(paths[i], starPaintInActive)
            currentX += 2 * outerRadius + spacing // Move to the next star position
        }
        for (coordinate in starXCoordinates) {
            Log.d("Dhairya", "X coordinate: $coordinate");
        }
    }

    fun drawingStars(canvas: Canvas, selectedStar:Int){
        if(selectedStar==0){
            canvas.drawPath(paths[0], starPaintInActive)
            canvas.drawPath(paths[1], starPaintInActive)
            canvas.drawPath(paths[2], starPaintInActive)
            canvas.drawPath(paths[3], starPaintInActive)
            canvas.drawPath(paths[4], starPaintInActive)
        }
        else if(selectedStar==1){
            canvas.drawPath(paths[0], starPaintActive)
            canvas.drawPath(paths[1], starPaintInActive)
            canvas.drawPath(paths[2], starPaintInActive)
            canvas.drawPath(paths[3], starPaintInActive)
            canvas.drawPath(paths[4], starPaintInActive)
        }
        else if(selectedStar==2){
            canvas.drawPath(paths[0], starPaintActive)
            canvas.drawPath(paths[1], starPaintActive)
            canvas.drawPath(paths[2], starPaintInActive)
            canvas.drawPath(paths[3], starPaintInActive)
            canvas.drawPath(paths[4], starPaintInActive)
        }
        else if(selectedStar==3){
            canvas.drawPath(paths[0], starPaintActive)
            canvas.drawPath(paths[1], starPaintActive)
            canvas.drawPath(paths[2], starPaintActive)
            canvas.drawPath(paths[3], starPaintInActive)
            canvas.drawPath(paths[4], starPaintInActive)
        }
        else if(selectedStar==4){
            canvas.drawPath(paths[0], starPaintActive)
            canvas.drawPath(paths[1], starPaintActive)
            canvas.drawPath(paths[2], starPaintActive)
            canvas.drawPath(paths[3], starPaintActive)
            canvas.drawPath(paths[4], starPaintInActive)
        }
        else if(selectedStar==5){
            canvas.drawPath(paths[0], starPaintActive)
            canvas.drawPath(paths[1], starPaintActive)
            canvas.drawPath(paths[2], starPaintActive)
            canvas.drawPath(paths[3], starPaintActive)
            canvas.drawPath(paths[4], starPaintActive)
        }
    }
    // Draws a star shape with the given radii, centered at (cx, cy)
    private fun drawStarPath(path: Path, cx: Float, cy: Float, outerRadius: Float, innerRadius: Float) {
        starXCoordinates.add(cx)
        path.reset()
        val points = 5
        val angle = Math.PI / points
        val rotationOffset = -Math.PI / 2 // Start pointing upward

        for (i in 0 until 2 * points) {
            val radius = if (i % 2 == 0) outerRadius else innerRadius
            val x = (cx + radius * Math.cos(i * angle + rotationOffset)).toFloat()
            val y = (cy + radius * Math.sin(i * angle + rotationOffset)).toFloat()
            if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
        }
        path.close()
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) return super.onTouchEvent(event)
        val view = this

        return when {
            event.action == MotionEvent.ACTION_DOWN && isTouchInsideView(view, event) -> {
                isDragged = true
                lastMotionEventX = event.x
                onTouchInsideListener?.onClickInside(event.x, event.y)
                Log.d("Dhairya TouchDown called", "${event.x} and ${event.y}")
                getStarsStatus(event.x)
                invalidate()
                true
            }
            isDragged && event.action == MotionEvent.ACTION_MOVE -> {
                lastMotionEventX = event.x
                Log.d("Dhairya Move called", "${event.x} and ${event.y}")
                onTouchInsideListener?.onDragInside(event.x, event.y)
                getStarsStatus(event.x)
                invalidate()
                true
            }
            else -> {
                isDragged = false
                false
            }
        }
    }


    fun isTouchInsideView(view: View, event: MotionEvent): Boolean {
        val location = IntArray(2)
        view.getLocationOnScreen(location)
        val viewLeft = location[0]
        val viewTop = location[1]
        val viewRight = viewLeft + view.width
        val viewBottom = viewTop + view.height

        val touchX = event.rawX.toInt()
        val touchY = event.rawY.toInt()

        return touchX in viewLeft..viewRight && touchY in viewTop..viewBottom
    }


    fun getStarsStatus(xCor:Float){
        if(xCor<starXCoordinates[0]){
            Log.d("Dhairya","0 Star");
            selectedStar=0
        }
        else if (xCor in starXCoordinates[0]..starXCoordinates[1]) {
            Log.d("Dhairya","1 Star");
            selectedStar=1
        }
        else if(xCor in starXCoordinates[1]..starXCoordinates[2]){
            Log.d("Dhairya","2 Star");
            selectedStar=2
        }
        else if(xCor in starXCoordinates[2]..starXCoordinates[3]){
            Log.d("Dhairya","3 Star");
            selectedStar=3
        }
        else if(xCor in starXCoordinates[3]..starXCoordinates[4]){
            Log.d("Dhairya","4 Star");
            selectedStar=4
        }
        else if(xCor>starXCoordinates[4]){
            Log.d("Dhairya","5 Star");
            selectedStar=5
        }
    }
}