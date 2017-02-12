package jp.co.ricoh.advop.mini.cheetahminiutil.ui.dialog;

import jp.co.ricoh.advop.mini.cheetahminiutil.R;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.BaseDialogOnClickListener;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.BaseOnClickListener;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MultiButtonDialogProgress extends BaseDialog {
	private TextView text;
	private Button leftButton;
	private Button rightButton;
	private Button centerButton;

	Context context;
	private ProgressBar bar;
	
	public MultiButtonDialogProgress(Context context) {
		super(context);
		this.context = context;
		dlg.getWindow().setContentView(R.layout.dialog_prograss);
		text = (TextView) dlg.findViewById(R.id.tbd_blue_dialog_guidance30dot);
		leftButton = (Button) dlg.findViewById(R.id.tbd_blue_dialog_button1);
		rightButton = (Button) dlg.findViewById(R.id.tbd_blue_dialog_button3);
		centerButton = (Button) dlg.findViewById(R.id.tbd_blue_dialog_button2);		
		bar = (ProgressBar) dlg.findViewById(R.id.layout_progress);
	}


	
	public static MultiButtonDialogProgress createCancelDialog(Context context, String msgID, final BaseDialogOnClickListener cancelOnClickListener) {
		final MultiButtonDialogProgress ret = new MultiButtonDialogProgress(context);
		
		ret.text.setText(msgID);
		ret.leftButton.setVisibility(View.GONE);
		ret.centerButton.setVisibility(View.GONE);
		ret.rightButton.setVisibility(View.VISIBLE);
		//ret.leftButton.setText(R.string.bt_cancel);
		ret.rightButton.setText(R.string.bt_cancel);
		
//		ret.leftButton.setOnClickListener(new BaseOnClickListener() {
//			@Override
//			public void onWork(View v) {
//				Buzzer.play();
//				ret.dismiss();
//			}
//		});
		
		ret.rightButton.setOnClickListener(new BaseOnClickListener() {
			@Override
			public void onWork(View v) {				
				cancelOnClickListener.onClick(ret);
			}
		});
		
		return ret;
	}
	
	public static MultiButtonDialogProgress createNoButtonDialog(Context context, String msg) {
		final MultiButtonDialogProgress ret = new MultiButtonDialogProgress(context);
		
		ret.text.setText(msg);
		ret.leftButton.setVisibility(View.GONE);
		ret.centerButton.setVisibility(View.GONE);
		ret.rightButton.setVisibility(View.GONE);
		return ret;
	}

	// set content
	public void setMsg(String string) {
		text.setText(string);
	}

	public Button getLeftButton() {
		return leftButton;
	}

	public Button getRightButton() {
		return rightButton;
	}

	public Button getCenterButton() {
		return centerButton;
	}
	
	public void setPrograssDismiss(){
		bar.setVisibility(View.INVISIBLE);
	}
	
}

