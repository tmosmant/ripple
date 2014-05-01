package fr.crossroads.ripple.tasks;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by tmosmant on 01/05/2014.
 */
public class RecordTask extends AsyncTask<String, Void, Void>
{
	private MediaRecorder mRecorder;

	@Override protected Void doInBackground(String... strings)
	{
		final String filename = strings[0];

		mRecorder = new MediaRecorder();
		mRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
		mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		mRecorder.setOutputFile(filename);
		mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

		try
		{
			mRecorder.prepare();
		}
		catch ( IOException e )
		{
			Log.e("deadhal", "RecordTask prepare failed");
		}

		mRecorder.start();
		while ( !isCancelled() )
			;
		mRecorder.stop();
		mRecorder.release();
		return null;
	}
}
