package jp.co.ricoh.advop.mini.cheetahminiutil.ui.dialog;

import jp.co.ricoh.advop.mini.cheetahminiutil.R;
import android.content.Context;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TitleButtonDialog extends BaseDialog{
	private TextView txtTitle, txtMessage;
	private Button leftButton;
	private Button rightButton;
	private Button centerButton;

	Context context;
	
	public TitleButtonDialog(Context context) {
		super(context);
		this.context = context;		
		dlg.setContentView(R.layout.dialog_title_button);
		txtTitle = (TextView) dlg.findViewById(R.id.dialog_txt_guidance);
		txtMessage = (TextView) dlg.findViewById(R.id.dialog_txt_message);
		leftButton = (Button) dlg.findViewById(R.id.dialog_btn_left);
		rightButton = (Button) dlg.findViewById(R.id.dialog_btn_right);	
		centerButton = (Button) dlg.findViewById(R.id.dialog_btn_centre);
	}


	public void setLeftButtonClick(OnClickListener listener) {
		if(leftButton != null) {
			leftButton.setOnClickListener(listener);
		}
	}
	
	public void setRightButtonClick(OnClickListener listener) {
		if(rightButton != null) {
			rightButton.setOnClickListener(listener);
		}
	}
	
	public void setCenterButtonClick(OnClickListener listener) {
		if(centerButton != null) {
			centerButton.setOnClickListener(listener);
		}
	}

	
	// set content
	public void setTxtTitle(String string) {
		if(txtTitle != null) {
			txtTitle.setText(string);
		}
	}
	
	public void setTxtMsg(String string) {
		if(txtMessage != null) {
			txtMessage.setText(string);
		}
	}
	public void setButtonLeft(String string){
		if(leftButton != null) {
			leftButton.setText(string);
		}
		
	}
	public void setButtonRight(String string){
		if(rightButton != null) {
			rightButton.setText(string);
		}
		
	}
	
	public void setButtonCenter(String string){
		if(centerButton != null) {
			centerButton.setText(string);
		}
		
	}
	
	public void setLeftButtonVisible(int visibility){
		if(leftButton != null) {
			leftButton.setVisibility(visibility);
		}
		
	}
	
	public void setRightButtonVisible(int visibility){
		
		if(rightButton != null) {
			rightButton.setVisibility(visibility);
		}
	}

	//centerButton visible
	public void setCenterButtonVisible(int visibility){
		if(centerButton != null) {
			centerButton.setVisibility(visibility);
		}
	}
	
	public Button getLeftButton() {
		return leftButton;
	}
	
	public Button getCenterButton() {
		return centerButton;
	}
	
	public Button getRightButton() {
		return rightButton;
	}
 	
}
