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

public class MultiButtonDialog extends BaseDialog {
	private TextView text;
	private Button leftButton;
	private Button rightButton;
	private Button centerButton;

	Context context;
	
	public MultiButtonDialog(Context context) {
		super(context);
		this.context = context;
		dlg.getWindow().setContentView(R.layout.dialog_three_button);
		text = (TextView) dlg.findViewById(R.id.tbd_blue_dialog_guidance30dot);
		leftButton = (Button) dlg.findViewById(R.id.tbd_blue_dialog_button1);
		rightButton = (Button) dlg.findViewById(R.id.tbd_blue_dialog_button3);
		centerButton = (Button) dlg.findViewById(R.id.tbd_blue_dialog_button2);				
	}
	
	public static MultiButtonDialog createMsgDialog(Context context, int msgID) {
		return MultiButtonDialog.createMsgDialog(context, context.getResources().getString(msgID));
	}
	
	public static MultiButtonDialog createMsgDialog(Context context, String msgID) {
		final MultiButtonDialog ret = new MultiButtonDialog(context);
		
		ret.text.setText(msgID);
		ret.leftButton.setVisibility(View.GONE);
		ret.centerButton.setVisibility(View.GONE);
		ret.rightButton.setVisibility(View.VISIBLE);
		ret.rightButton.setText(R.string.bt_ok);
		
		ret.rightButton.setOnClickListener(new BaseOnClickListener() {
			@Override
			public void onWork(View v) {
				ret.dismiss();
			}
		});
		
		return ret;
	}
	
	public static MultiButtonDialog createMsgDialog(Context context, String msgID, final BaseDialogOnClickListener okOnClickListener) {
		final MultiButtonDialog ret = new MultiButtonDialog(context);
		
		ret.text.setText(msgID);
		ret.leftButton.setVisibility(View.GONE);
		ret.centerButton.setVisibility(View.GONE);
		ret.rightButton.setVisibility(View.VISIBLE);
		ret.rightButton.setText(R.string.bt_ok);		

		ret.rightButton.setOnClickListener(new BaseOnClickListener() {
			@Override
			public void onWork(View v) {				
				okOnClickListener.onClick(ret);
			}
		});
		return ret;
	}

	public static MultiButtonDialog createMustInputCheckDialog(Context context, int itemID, final EditText editText) {
		Resources res = context.getResources();
		String msg = String.format(res.getString(R.string.err_input), res.getString(itemID));
		return createInputCheckDialog(context, msg, editText);
	}

	public static MultiButtonDialog createInputCheckDialog(Context context, int msgID, final EditText editText) {
		Resources res = context.getResources();
		String msg = res.getString(msgID);
		return createInputCheckDialog(context, msg, editText);
	}
	
	public static MultiButtonDialog createInputCheckDialog(Context context, String msg, final EditText editText) {
		final MultiButtonDialog ret = new MultiButtonDialog(context);

		ret.text.setText(msg);
		
		ret.leftButton.setVisibility(View.GONE);
		ret.centerButton.setVisibility(View.GONE);
		ret.rightButton.setVisibility(View.VISIBLE);
		ret.rightButton.setText(R.string.bt_ok);
		
		ret.rightButton.setOnClickListener(new BaseOnClickListener() {
			@Override
			public void onWork(View v) {
				ret.dismiss();
				editText.requestFocus();
			}
		});
		
		return ret;
	}
	
	public static MultiButtonDialog createOKCancelDialog2Button1String(Context context, String msg,
			final BaseDialogOnClickListener cancelOnClickListener,
			final BaseDialogOnClickListener okOnClickListener) {
//		Resources res = context.getResources();		
		final MultiButtonDialog ret = new MultiButtonDialog(context);
		
		ret.text.setText(msg);
		ret.leftButton.setVisibility(View.VISIBLE);
		ret.centerButton.setVisibility(View.GONE);
		ret.rightButton.setVisibility(View.VISIBLE);
		ret.leftButton.setText(R.string.bt_cancel);
		ret.rightButton.setText(R.string.bt_ok);		
		ret.leftButton.setOnClickListener(new BaseOnClickListener() {
			@Override
			public void onWork(View v) {
//				ret.dismiss();
				cancelOnClickListener.onClick(ret);
			}
		});
		
		ret.rightButton.setOnClickListener(new BaseOnClickListener() {
			@Override
			public void onWork(View v) {				
				okOnClickListener.onClick(ret);
			}
		});
		
		return ret;
	}
	
	public static MultiButtonDialog createOKCancelDialogResIdAndString(Context context, int msgID, String val, final BaseDialogOnClickListener okOnClickListener) {
		Resources res = context.getResources();
		String msg = String.format(res.getString(msgID), val);
		
		final MultiButtonDialog ret = new MultiButtonDialog(context);
		
		ret.text.setText(msg);
		ret.leftButton.setVisibility(View.VISIBLE);
		ret.centerButton.setVisibility(View.GONE);
		ret.rightButton.setVisibility(View.VISIBLE);
		ret.leftButton.setText(R.string.bt_cancel);
		ret.rightButton.setText(R.string.bt_ok);
		
		ret.leftButton.setOnClickListener(new BaseOnClickListener() {
			@Override
			public void onWork(View v) {
				ret.dismiss();
			}
		});
		
		ret.rightButton.setOnClickListener(new BaseOnClickListener() {
			@Override
			public void onWork(View v) {				
				okOnClickListener.onClick(ret);
			}
		});
		
		return ret;
	}

	
	public static MultiButtonDialog createOKCancelDialog(Context context, int msgID, final BaseDialogOnClickListener okOnClickListener) {
		final MultiButtonDialog ret = new MultiButtonDialog(context);
		
		ret.text.setText(msgID);
		ret.leftButton.setVisibility(View.VISIBLE);
		ret.centerButton.setVisibility(View.GONE);
		ret.rightButton.setVisibility(View.VISIBLE);
		ret.leftButton.setText(R.string.bt_cancel);
		ret.rightButton.setText(R.string.bt_ok);
		
		ret.leftButton.setOnClickListener(new BaseOnClickListener() {
			@Override
			public void onWork(View v) {
				ret.dismiss();
			}
		});
		
		ret.rightButton.setOnClickListener(new BaseOnClickListener() {
			@Override
			public void onWork(View v) {				
				okOnClickListener.onClick(ret);
			}
		});
		
		return ret;
	}
	
	public static MultiButtonDialog createCancelDialog(Context context, String msgID, final BaseDialogOnClickListener cancelOnClickListener) {
		final MultiButtonDialog ret = new MultiButtonDialog(context);
		
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
	
	public static MultiButtonDialog createNoButtonDialog(Context context, String msg) {
		final MultiButtonDialog ret = new MultiButtonDialog(context);
		
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
	public void setTextSize(float size){
		text.setTextSize(size);
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
	
}
