package jp.co.ricoh.advop.mini.cheetahminiutil.util;

import android.view.View;
import android.view.View.OnClickListener;

public abstract class BaseOnClickListener implements OnClickListener {
	
	abstract public void onWork(View v); 		
	@Override
	public void onClick(View v) {
		if(CUtil.isContinue()){			
			Buzzer.play();
			onWork(v);
		} else{   
			LogC.d("The interval time of click is smaller than " + CUtil.INTERVAL_TIME);
			return;
		  }
		
	}

}
