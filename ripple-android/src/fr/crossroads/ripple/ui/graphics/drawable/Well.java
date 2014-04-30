package fr.crossroads.ripple.ui.graphics.drawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;
import fr.crossroads.ripple.ui.graphics.Colors;

/**
 * Created by tmosmant on 27/04/2014.
 */
public class Well extends Drawable
{

	private final Context context;
	private final Point position;
	private final int radius;

	private final Paint paint;

	private Well(Context context, Point center, int radius, Paint paint)
	{
		this.context = context;
		this.position = center;
		this.radius = radius;
		this.paint = paint;
	}

	public static Well create(Context context)
	{
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();

		final Point center = new Point();
		display.getSize(center);
		center.x /= 2;
		center.y /= 2;

		Paint paint = new Paint();
		int radius = 150;
		final RadialGradient gradient = new RadialGradient(
				center.x,
				center.y,
				radius,
				Colors.BLUE,
				Colors.BLUE_DARK,
				Shader.TileMode.CLAMP);
		paint.setDither(true);
		paint.setShader(gradient);
		paint.setAntiAlias(true);

		return new Well(context, center, radius, paint);
	}

	public boolean contains(MotionEvent event) {
		return Math.pow(event.getX() - position.x, 2) + Math.pow(event.getY() - position.y, 2) < Math.pow(radius, 2);
	}

	@Override public void draw(Canvas canvas)
	{
		canvas.drawCircle(position.x, position.y, radius, paint);
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
}
