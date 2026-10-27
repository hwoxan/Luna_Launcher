package dev.luna

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.text.TextDirectionHeuristics
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.TextView

/** How far a name that is too wide for its box has to move, in pixels; 0 when it fits. */
internal fun labelOverflow(textWidth: Float, boxWidth: Float) = maxOf(0f, textWidth - boxWidth)

/** One round of a long name: it waits at the start, scrolls to the end, waits, and scrolls back. */
internal class MarqueeCycle(val pauseMs: Int, val scrollMs: Int) {
    val totalMs get() = 2 * (pauseMs + scrollMs)
}

/** A slow, steady speed: [overflowPx] at [pxPerSecond], but never faster than [MIN_SCROLL_MS] for the whole way. */
internal fun marqueeCycle(overflowPx: Float, pxPerSecond: Float, pauseMs: Int = 1000) =
    MarqueeCycle(pauseMs, maxOf(MIN_SCROLL_MS, (overflowPx / pxPerSecond * 1000).toInt()))

private const val MIN_SCROLL_MS = 900

/** How far the name has moved [timeMs] into its round: eased at both ends, so it starts and stops softly. */
internal fun marqueeShift(timeMs: Int, overflowPx: Float, cycle: MarqueeCycle): Float {
    val t = timeMs % cycle.totalMs
    val leg = cycle.pauseMs + cycle.scrollMs
    val back = t >= leg
    val x = ((if (back) t - leg else t) - cycle.pauseMs).coerceAtLeast(0).toFloat() / cycle.scrollMs
    val eased = x.coerceIn(0f, 1f).let { it * it * (3 - 2 * it) }
    return overflowPx * (if (back) 1f - eased else eased)
}

/**
 * An app name that stays inside its box. It is centred when it fits. When it doesn't, it is cut with "…" until its
 * tile is selected ([setActive]), then it scrolls slowly to its end and back, with a pause at each end.
 * The box is [box] pixels wide, centred in the view (0: the view's width without its padding).
 */
internal class FittingLabel @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : TextView(context, attrs) {
    var box = 0
        set(value) {
            if (field != value) {
                field = value
                restart()
            }
        }
    private var active = false
    private var shift = 0f
    private var animator: ValueAnimator? = null

    fun setActive(on: Boolean) {
        if (active != on) {
            active = on
            restart()
        }
    }

    private fun available() = (if (box > 0) box else width - paddingLeft - paddingRight).toFloat()

    private fun restart() {
        animator?.cancel()
        animator = null
        shift = 0f
        invalidate()
        val avail = available()
        val overflow = labelOverflow(paint.measureText(text, 0, text.length), avail)
        if (!active || overflow <= 0f || avail <= 0f || !isAttachedToWindow || windowVisibility != View.VISIBLE) return
        val cycle = marqueeCycle(overflow, SPEED_DP * resources.displayMetrics.density)
        animator = ValueAnimator.ofInt(0, cycle.totalMs).apply {
            duration = cycle.totalMs.toLong()
            interpolator = LinearInterpolator()
            repeatCount = ValueAnimator.INFINITE
            addUpdateListener {
                shift = marqueeShift(it.animatedValue as Int, overflow, cycle)
                invalidate()
            }
            start()
        }
    }

    override fun onTextChanged(text: CharSequence?, start: Int, lengthBefore: Int, lengthAfter: Int) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        restart()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        restart()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        restart()
    }

    /** Opening an app hides the window but leaves the view attached: the animation must not run for nobody. */
    override fun onWindowVisibilityChanged(visibility: Int) {
        super.onWindowVisibilityChanged(visibility)
        if (visibility == View.VISIBLE) restart() else {
            animator?.cancel()
            animator = null
        }
    }

    override fun onDetachedFromWindow() {
        animator?.cancel()
        animator = null
        super.onDetachedFromWindow()
    }

    override fun onDraw(c: Canvas) {
        val avail = available()
        if (text.isEmpty() || avail <= 0f) return
        val p = paint
        p.color = currentTextColor
        val left = if (box > 0) (width - box) / 2f else paddingLeft.toFloat()
        val rtl = TextDirectionHeuristics.FIRSTSTRONG_LTR.isRtl(text, 0, text.length) // the text's own direction: the tiles are always left to right
        val full = p.measureText(text, 0, text.length)
        val shown = if (full <= avail || animator != null) text else TextUtils.ellipsize(text, p, avail, TextUtils.TruncateAt.END)
        val w = p.measureText(shown, 0, shown.length)
        val x = when {
            full <= avail -> left + (avail - w) / 2f // fits: centred, still
            rtl -> left + avail - w + shift
            else -> left - shift
        }
        c.save()
        c.clipRect(left, 0f, left + avail, height.toFloat())
        c.drawText(shown, 0, shown.length, x, baseline.toFloat(), p)
        c.restore()
    }

    private companion object {
        const val SPEED_DP = 28f // dp per second: slow enough to read
    }
}
