package me.jiniworld.demo.domain.dto.response;

import lombok.Getter;
import lombok.Setter;
import me.jiniworld.demo.util.MessageUtils;

@Getter
@Setter
public class BaseResponse {
    private String result;
    private String reason;

    public BaseResponse(){
        this.result = MessageUtils.SUCCESS;
        this.reason  = "";
    }
    public BaseResponse(String result){
        this.reason = MessageUtils.FAIL;
        this.result = result;
    }

}
