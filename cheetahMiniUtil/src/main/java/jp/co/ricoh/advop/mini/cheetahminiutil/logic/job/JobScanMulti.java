package jp.co.ricoh.advop.mini.cheetahminiutil.logic.job;

import java.util.ArrayList;
import java.util.List;

import jp.co.ricoh.advop.mini.cheetahminiutil.logic.CHolder;
import jp.co.ricoh.advop.mini.cheetahminiutil.logic.JobManager.EXE_RESULT;
import jp.co.ricoh.advop.mini.cheetahminiutil.scan.ScanStateMachine.ScanEvent;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner.FilePathResponseBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.logic.JobManager.ExeCallbackScanMulti;
import jp.co.ricoh.advop.mini.cheetahminiutil.logic.JobManager.ExeCallbackScanMulti.ScanedFileInfo;

public class JobScanMulti extends BaseJob {

	public JobScanMulti(ExeCallbackScanMulti exeCallback) {
		super(exeCallback);
	}

	@Override
	public void onStart() {
		CHolder.instance().getScanManager().getStateMachine()
				.procScanEvent(ScanEvent.REQUEST_JOB_START);
	}

	public void GetPathFinished(List<FilePathResponseBody> result) {
		if (exeCallback != null && result != null) {
			List<ScanedFileInfo> fileInfos = new ArrayList<ScanedFileInfo>();
			for (FilePathResponseBody body : result) {
				fileInfos.add(new ScanedFileInfo(body.getFilePath(), body.getRotate()));
			}

			((ExeCallbackScanMulti) exeCallback).onGetFilePath(fileInfos);
		}
		complete(EXE_RESULT.SUCCESSED);

	}
}

