package com.cff.springwork.network.tcp.future;


import java.util.concurrent.Future;

import com.cff.springwork.network.tcp.data.TransactionMapData;

/**
 * Created by fuzhengwei1 on 2016/10/20.
 */
public interface WriteFuture<T> extends Future<T> {

    Throwable cause();

    void setCause(Throwable cause);

    boolean isWriteSuccess();

    void setWriteResult(boolean result);

    String requestId();

    T response();

    void setResponse(TransactionMapData response);

    boolean isTimeout();


}
