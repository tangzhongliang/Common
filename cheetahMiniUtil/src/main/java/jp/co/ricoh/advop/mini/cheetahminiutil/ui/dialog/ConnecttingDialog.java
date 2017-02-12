package jp.co.ricoh.advop.mini.cheetahminiutil.ui.dialog;

import jp.co.ricoh.advop.mini.cheetahminiutil.R;
import android.content.Context;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ConnecttingDialog extends BaseDialog{

	
	public Button button;
	public TextView text;

	Context context;

	public ConnecttingDialog(Context context) { 
		super(context);
		this.context = context;
		dlg.setContentView(R.layout.dialog_connection);
		button = (Button) dlg.findViewById(R.id.cd_blue_dialog_button3); 
		text = (TextView)  dlg.findViewById(R.id.cd_blue_dialog_guidance30dot);					
	}


	public void SetButtonListener (OnClickListener listener){
		if(button != null) {
			button.setOnClickListener(listener);
		}
	}


	// set content
	public void setText(String string) {
		if (text != null) {
			text.setText(string);
		}
		
	}
	
	public void setButtonText(String string){
		if (button != null) {
			button.setText(string);
		}		
	}

	public void setButtonVisable(int visibility) {
		button.setVisibility(visibility);
	}
	
	public void setDissable(){
		button.setEnabled(false);
		button.setAlpha((float) 0.5);
	}

}
