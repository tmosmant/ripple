package fr.crossroads.ripple.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import fr.crossroads.ripple.ui.graphics.drawable.Record;
import fr.crossroads.ripple.ui.graphics.drawable.Well;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by tmosmant on 26/04/2014.
 */
public class BaseView extends View
{

	private final List<Well> wells = new LinkedList<Well>();
	private Record activeRecord;

	private BaseView(Context context)
	{
		super(context);
	}

	public static BaseView create(Context context)
	{
		final BaseView baseView = new BaseView(context);
		final Well well = Well.create(context);
		baseView.addWell(well);
		baseView.invalidate();
		return baseView;
	}

	public void addWell(Well well)
	{
		wells.add(well);
	}

	@Override public boolean onTouchEvent(MotionEvent event)
	{
		switch ( event.getAction() )
		{
		case MotionEvent.ACTION_DOWN:
			boolean findWell = false;
			for ( Well well : wells )
			{
				if ( well.contains(event) )
				{
					findWell = true;
				}
			}
			if ( !findWell )
			{
				activeRecord = new Record();
				activeRecord.move(event);
			}
			break;
		case MotionEvent.ACTION_MOVE:
			if ( activeRecord != null )
			{
				activeRecord.move(event);
			}
			break;
		case MotionEvent.ACTION_UP:
			activeRecord = null;
		default:
			break;
		}
		invalidate();
		return true;
	}

	@Override protected void onDraw(Canvas canvas)
	{
		for ( Well well : wells )
		{
			well.draw(canvas);
		}
		if ( activeRecord != null )
		{
			activeRecord.draw(canvas);
		}
	}
}
