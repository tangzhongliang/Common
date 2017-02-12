package jp.co.ricoh.advop.mini.cheetahminiutil.ui.dialog;

import jp.co.ricoh.advop.mini.cheetahminiutil.R;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.BaseOnClickListener;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SettingDialog extends BaseDialog{

	

	Context context;
	private Button cancel;
	private EditText count;

	@SuppressLint("SetJavaScriptEnabled")
	public SettingDialog(Context context) { 
		super(context);
		this.context = context;
		dlg.setContentView(R.layout.dialog_password);
		cancel = (Button) dlg.findViewById(R.id.bt_cancel);		
		cancel.setOnClickListener(new BaseOnClickListener() {
			
			@Override
			public void onWork(View v) {
				dlg.dismiss();
				
			}
		});
	}
	
	
	public void show(){
		dlg.show();
	}
	
	public void dismiss(){
		dlg.dismiss();
	}
		
}
