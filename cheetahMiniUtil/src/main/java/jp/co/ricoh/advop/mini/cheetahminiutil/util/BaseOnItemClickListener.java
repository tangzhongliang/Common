package jp.co.ricoh.advop.mini.cheetahminiutil.util;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public abstract class BaseOnItemClickListener implements OnItemClickListener {
	
	abstract public void onWork(AdapterView<?> parent, View view, int position, long id); 		
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if(CUtil.isContinue()){			
			Buzzer.play();
			onWork(parent, view, position, id);
		} else{   
			LogC.d("The interval time of click is smaller than " + CUtil.INTERVAL_TIME);
			return;
		  }
		
	}

}