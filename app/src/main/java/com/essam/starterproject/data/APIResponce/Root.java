
package com.essam.starterproject.data.APIResponce;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Root {

    @SerializedName("data")
    private OperatorsResponce mOperatorsResponce;
    @SerializedName("status")
    private Long mStatus;

    public OperatorsResponce getData() {
        return mOperatorsResponce;
    }

    public void setData(OperatorsResponce operatorsResponce) {
        mOperatorsResponce = operatorsResponce;
    }

    public Long getStatus() {
        return mStatus;
    }

    public void setStatus(Long status) {
        mStatus = status;
    }

}
