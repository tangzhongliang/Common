package jp.co.ricoh.advop.mini.cheetahminiutil.util;

import jp.co.ricoh.advop.mini.cheetahminiutil.ui.dialog.BaseDialog;
import jp.co.ricoh.advop.mini.cheetahminiutil.ui.dialog.BaseDialog.CDialogOnClickListener;

public abstract class BaseDialogOnClickListener implements CDialogOnClickListener {
	
	abstract public void onWork(BaseDialog dialog); 		
	@Override
	public void onClick(BaseDialog dialog) {
//		if(CUtil.isCountinue()){			
//			Buzzer.play();
//			onWork(dialog);
//		} else{   
//			LogC.d("The interval time of click is smaller than " + CUtil.INTERVAL_TIME);
//			return;
//		  }
		onWork(dialog);
		
	}

}
