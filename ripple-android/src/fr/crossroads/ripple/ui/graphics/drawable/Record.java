package fr.crossroads.ripple.ui.graphics.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;

/**
 * Created by tmosmant on 28/04/2014.
 */
public class Record extends Drawable
{
	private final PointF position = new PointF();

	@Override public void draw(Canvas canvas)
	{
		canvas.drawCircle(position.x, position.y, 70, Paints.BLUE);
	}

	@Override public void setAlpha(int i)
	{
		// nothing to do
	}

	@Override public void setColorFilter(ColorFilter colorFilter)
	{
		// nothing to do
	}

	@Override public int getOpacity()
	{
		return 0;
	}

	public void move(MotionEvent event)
	{
		this.position.set(event.getX(), event.getY());
	}
}
