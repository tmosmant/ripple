package fr.crossroads.ripple.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import fr.crossroads.ripple.tasks.PlayTask;
import fr.crossroads.ripple.tasks.RecordTask;
import fr.crossroads.ripple.ui.graphics.drawable.Record;
import fr.crossroads.ripple.ui.graphics.drawable.Well;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by tmosmant on 26/04/2014.
 */
public class BaseView extends View
{
	private String mFilename;

	private Record mRecord;
	private RecordTask mRecordTask;
	private PlayTask mPlayTask;

	private final List<Well> mWells = new LinkedList<Well>();

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
		mWells.add(well);
	}

	@Override public boolean onTouchEvent(MotionEvent event)
	{
		switch ( event.getAction() )
		{
		case MotionEvent.ACTION_DOWN:
			down(event);
			break;
		case MotionEvent.ACTION_MOVE:
			move(event);
			break;
		case MotionEvent.ACTION_UP:
			up(event);
			break;
		default:
			break;
		}
		invalidate();
		return true;
	}

	private void up(MotionEvent event)
	{
		boolean mustPlay = true;

		for ( Well well : mWells )
		{
			well.setHovered(false);
			if ( mRecord != null && well.contains(event) )
			{
				Toast.makeText(getContext(), "Record to send : " + mFilename, Toast.LENGTH_SHORT).show();
				mustPlay = false;
			}
		}
		if ( mRecordTask != null )
		{
			mRecordTask.cancel(true);
		}
		mRecord = null;
		mRecordTask = null;
		if ( mustPlay && mFilename != null )
		{
			mPlayTask = new PlayTask();
			mPlayTask.execute(mFilename);
		}
	}

	private void move(MotionEvent event)
	{
		if ( mRecord != null )
		{
			mRecord.move(event);
			for ( Well well : mWells )
			{
				if ( well.contains(event) )
				{
					well.setHovered(true);
				}
				else
				{
					well.setHovered(false);
				}
			}
		}
	}

	private void down(MotionEvent event)
	{
		if ( mPlayTask != null )
		{
			mPlayTask.cancel(true);
			mPlayTask = null;
		}

		boolean findWell = false;
		for ( Well well : mWells )
		{
			if ( well.contains(event) )
			{
				findWell = true;
			}
		}
		if ( !findWell )
		{
			mRecord = new Record();
			mRecordTask = new RecordTask();
			mRecord.move(event);

			mFilename = Environment.getExternalStorageDirectory().getAbsolutePath();
			mFilename += "/audioToSend-" + System.currentTimeMillis() + ".3gp";

			mRecordTask.execute(mFilename);
		}
	}

	@Override protected void onDraw(Canvas canvas)
	{
		for ( Well well : mWells )
		{
			well.draw(canvas);
		}
		if ( mRecord != null )
		{
			mRecord.draw(canvas);
		}
	}
}
