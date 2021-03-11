
package com.essam.starterproject.base;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class APIErrorResponse {

    @SerializedName("errors")
    private List<Error> mErrors;
    @SerializedName("status")
    private Long mStatus;

    public List<Error> getErrors() {
        return mErrors;
    }

    public void setErrors(List<Error> errors) {
        mErrors = errors;
    }

    public Long getStatus() {
        return mStatus;
    }

    public void setStatus(Long status) {
        mStatus = status;
    }

}
