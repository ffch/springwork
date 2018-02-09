package com.cff.springwork.network.tcp.future;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.cff.springwork.network.tcp.data.TransactionMapData;

/**
 * Created by fuzhengwei1 on 2016/10/20.
 */
public class SyncWrite {

    public TransactionMapData writeAndSync(final Channel channel, final TransactionMapData request, final long timeout) throws Exception {

        if (channel == null) {
            throw new NullPointerException("channel");
        }
        if (request == null) {
            throw new NullPointerException("request");
        }
        if (timeout <= 0) {
            throw new IllegalArgumentException("timeout <= 0");
        }

        String requestId = UUID.randomUUID().toString();
        request.put("reqTransFlow", requestId);

        WriteFuture<TransactionMapData> future = new SyncWriteFuture(request.get("reqTransFlow").toString());
        SyncWriteMap.syncKey.put(request.get("reqTransFlow").toString(), future);

        TransactionMapData response = doWriteAndSync(channel, request, timeout, future);

        SyncWriteMap.syncKey.remove(request.get("reqTransFlow").toString());
        return response;
    }

    private TransactionMapData doWriteAndSync(final Channel channel, final TransactionMapData request, final long timeout, final WriteFuture<TransactionMapData> writeFuture) throws Exception {

        channel.writeAndFlush(request).addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture future) throws Exception {
                writeFuture.setWriteResult(future.isSuccess());
                writeFuture.setCause(future.cause());
                //失败移除
                if (!writeFuture.isWriteSuccess()) {
                    SyncWriteMap.syncKey.remove(writeFuture.requestId());
                }
            }
        });

        TransactionMapData response = writeFuture.get(timeout, TimeUnit.MILLISECONDS);
        if (response == null) {
            if (writeFuture.isTimeout()) {
                throw new TimeoutException();
            } else {
                // write exception
                throw new Exception(writeFuture.cause());
            }
        }
        return response;
    }

}
