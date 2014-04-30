package fr.crossroads.ripple.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import fr.crossroads.ripple.R;

/**
 * Created by tmosmant on 26/04/2014.
 */
public class BaseFragment extends Fragment
{

	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		RelativeLayout relativeLayout = (RelativeLayout) getActivity()
				.findViewById(R.id.mainLayout);
		relativeLayout.addView(BaseView.create(getActivity()));
		return super.onCreateView(inflater, container, savedInstanceState);
	}
}
