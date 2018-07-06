package in.equipshare.escms.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class SignupResult {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("msg")
    @Expose
    private String msg;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    @Override
    public String toString() {
        return "SignupResult{" +
                "success=" + success +
                ", msg='" + msg + '\'' +
                '}';
    }


}
