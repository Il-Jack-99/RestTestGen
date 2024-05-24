package io.resttestgen.core.testing;

import com.google.gson.JsonObject;

public abstract class Coverage {

    public abstract void updateCoverage(TestInteraction testInteraction);

    public Float getCoverage(){
        if(this.getToTest() == 0){
            return (float) 0.00;
        }
        return (float) ((double)this.getNumOfTestedDocumented()*100/this.getToTest());
    }

    public abstract int getNumOfTestedDocumented();

    public abstract int getNumOfTestedNotDocumented();

    public abstract int getToTest();

    public abstract JsonObject getReportAsJsonObject();

}
