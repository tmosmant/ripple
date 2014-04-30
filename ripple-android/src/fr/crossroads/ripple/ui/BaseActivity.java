package fr.crossroads.ripple.ui;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import fr.crossroads.ripple.R;

public class BaseActivity extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final FragmentManager fm = getFragmentManager();
		final BaseFragment bf = new BaseFragment();

		Bundle bundle = new Bundle();
		bf.setArguments(bundle);

		fm.beginTransaction()
				.replace(R.id.frameLayout, bf).commit();

	}
}
