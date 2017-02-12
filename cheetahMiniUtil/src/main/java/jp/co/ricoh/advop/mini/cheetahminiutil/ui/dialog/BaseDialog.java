package jp.co.ricoh.advop.mini.cheetahminiutil.ui.dialog;

import jp.co.ricoh.advop.mini.cheetahminiutil.R;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.BaseOnClickListener;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.Buzzer;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class BaseDialog {

	protected Context context;
	protected Dialog dlg;



    public interface CDialogOnClickListener {
        public void onClick(BaseDialog dialog);
    }

	protected BaseDialog(Context context) {
		dlg = new Dialog(context, R.style.base_dialog);
		// dlg = new AlertDialog.Builder(context).
		dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dlg.setCancelable(false);
		this.context = context;
		
		dlg.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
				 if(keyCode == KeyEvent.KEYCODE_BACK  && event.getAction() == KeyEvent.ACTION_DOWN)
                 {
                    Buzzer.onBuzzer(Buzzer.BUZZER_NACK);
                    return true;
                 }                

				return false;
			}
		});
		dlg.setCancelable(false);
		dlg.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

	}

	public void show() {
		if (context != null && !LogC.isMT) {
			if(context instanceof Activity) { 
	            if(((Activity)context).isFinishing() || ((Activity)context).isDestroyed()) {
	               LogC.w("activity is finished or is destroyed. dialog not show");	
	                return;
	            }
	        } 
			dlg.show();
		}
	}

	public void dismiss() {
		if(context instanceof Activity) { 
            if(((Activity)context).isFinishing() || ((Activity)context).isDestroyed()) {
            	LogC.w("activity is finished or is destroyed. dialog not dismiss");	
            	return;
            }
                
        } 
		dlg.dismiss();
	}

	public boolean isShowing() {
		return dlg.isShowing();
	}
	
	// ---------------------------------hide keyboard-------------------------------------------------------
    private boolean isSetupkeyboardHideGlobal = false;
    protected void setupKeyboardHide(ViewGroup container, final EditText edText) {
        if (edText != null) {
            if (!isSetupkeyboardHideGlobal) {
                isSetupkeyboardHideGlobal = true;
                setupkeyboardHideGlobal(dlg.findViewById(android.R.id.content));
            }
            container.setOnClickListener(new BaseOnClickListener() {
                
                @Override
                public void onWork(View v) {
                    showSoftKeyboard(edText);
                }
            });
            
            // release touch guard of setupkeyboardHideGlobal()
            releaseOnTouch(container);
        }
    }
    
    private void releaseOnTouch(View view) {
        //Set up touch listener for non-text box views to hide keyboard.
        if(!(view instanceof EditText)) {
            view.setOnTouchListener(null);
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                releaseOnTouch(innerView);
            }
        }
    }
    
    // set click anywhere to hide keyboard
    protected void setupkeyboardHideGlobal(View view) {
        //Set up touch listener for non-text box views to hide keyboard.
        if(!(view instanceof EditText)) {
            view.setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard();
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                setupkeyboardHideGlobal(innerView);
            }
        }
    }
    protected void showSoftKeyboard(EditText edText) {
        edText.requestFocus();
        InputMethodManager lManager = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE); 
        lManager.showSoftInput(edText, edText.getInputType());
    }
    protected void hideSoftKeyboard() {
        if(dlg.getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager)  context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(dlg.getCurrentFocus().getWindowToken(), 0);
        }
    }
}
