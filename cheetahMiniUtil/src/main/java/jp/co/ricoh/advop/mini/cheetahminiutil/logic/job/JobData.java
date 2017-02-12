package jp.co.ricoh.advop.mini.cheetahminiutil.logic.job;


public class JobData {
    private BaseJob currentJob = null;
    
    public BaseJob getCurrentJob() {
        return currentJob;
    }
    public void setCurrentJob(BaseJob currentJob) {
        this.currentJob = currentJob;
    }
    
}
