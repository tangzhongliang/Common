package jp.co.ricoh.advop.mini.cheetahminiutil.logic.job;

import java.io.File;

import jp.co.ricoh.advop.mini.cheetahminiutil.logic.CHolder;
import jp.co.ricoh.advop.mini.cheetahminiutil.logic.JobManager.EXE_RESULT;
import jp.co.ricoh.advop.mini.cheetahminiutil.logic.JobManager.ExeCallbackScan;
import jp.co.ricoh.advop.mini.cheetahminiutil.scan.ScanStateMachine.ScanEvent;

public class JobScan extends BaseJob {

	public JobScan(ExeCallbackScan exeCallback) {
		super(exeCallback);
	}

	@Override
	public void onStart() {
		CHolder.instance().getScanManager().getStateMachine()
				.procScanEvent(ScanEvent.REQUEST_JOB_START);
	}

	public void GetPathFinished(final String path, final int imgRotate) {
		if (exeCallback != null) {
			((ExeCallbackScan) exeCallback).onGetFilePath(path, imgRotate);
		}
		complete(EXE_RESULT.SUCCESSED);

	}
}

