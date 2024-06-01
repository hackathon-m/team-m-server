package hackerthon.demo.apiPayload.exception.handler;

import hackerthon.demo.apiPayload.code.BaseErrorCode;
import hackerthon.demo.apiPayload.exception.GeneralException;

public class MemberHandler extends GeneralException {

    public MemberHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
