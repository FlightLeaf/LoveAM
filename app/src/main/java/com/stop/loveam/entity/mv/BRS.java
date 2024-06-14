package com.stop.loveam.entity.mv;

import com.alibaba.fastjson2.annotation.JSONField;

public class BRS implements java.io.Serializable {
    @JSONField(name = "240")
    private String the240;
    @JSONField(name = "480")
    private String the480;
    @JSONField(name = "720")
    private String the720;
    @JSONField(name = "1080")
    private String the1080;

    public String getThe240() { return the240; }
    public void setThe240(String value) { this.the240 = value; }

    public String getThe480() { return the480; }
    public void setThe480(String value) { this.the480 = value; }

    public String getThe720() { return the720; }
    public void setThe720(String value) { this.the720 = value; }

    public String getThe1080() { return the1080; }
    public void setThe1080(String value) { this.the1080 = value; }
}


