package fr.crossroads.ripple.tasks;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

/**
 * Created by tmosmant on 01/05/2014.
 */
public class PlayTask extends AsyncTask<String, Void, Void>
{
	private MediaPlayer mPlayer;

	@Override protected Void doInBackground(String... strings)
	{
		String filename = strings[0];

		mPlayer = new MediaPlayer();
		try
		{
			mPlayer.setDataSource(filename);
			mPlayer.prepare();
			mPlayer.start();
		}
		catch ( IOException e )
		{
			Log.e("deadhal", "PlayTask prepare failed");
		}

		while ( !isCancelled() )
			;
		mPlayer.release();
		return null;
	}
}
