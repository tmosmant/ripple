package fr.crossroads.ripple.ui.graphics.drawable;

import android.graphics.Paint;
import fr.crossroads.ripple.ui.graphics.Colors;

/**
 * Created by tmosmant on 28/04/2014.
 */
public class Paints
{
	public final static Paint BLUE = new Paint();

	static {
		BLUE.setColor(Colors.BLUE);
		BLUE.setStyle(Paint.Style.FILL);
		BLUE.setAntiAlias(true);
	}

}
