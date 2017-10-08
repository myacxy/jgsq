package net.myacxy.jgsq.protocols;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioDatagramConnector;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class MinaTest {

    @Test
    public void minaTest() throws IOException, InterruptedException {
        byte oob = (byte) 0xff;
        byte[] getStatus = "getstatus".getBytes();
        final byte[] request = new byte[4 + getStatus.length];
        for (int i = 0; i < request.length; i++) {
            if (i < 4) request[i] = oob;
            else request[i] = getStatus[i - 4];
        }

        final CountDownLatch latch = new CountDownLatch(1);

        final NioDatagramConnector client = new NioDatagramConnector();
        client.setHandler(new IoHandlerAdapter() {
            @Override
            public void sessionOpened(IoSession session) throws Exception {
                System.out.println("sessionOpened");
                System.out.println(new String(request));
                session.write(IoBuffer.wrap(request));
            }

            @Override
            public void messageSent(IoSession session, Object message) throws Exception {
                System.out.println("messageSent");
                System.out.println(message);
            }

            @Override
            public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
                System.out.println("exceptionCaught");
                System.out.println(cause.toString());
            }

            @Override
            public void messageReceived(IoSession session, Object message) throws Exception {
                System.out.println("messageReceived");
                if (message instanceof IoBuffer) {
                    System.out.println(new String(((IoBuffer) message).array()));
                }
                session.closeNow();
                latch.countDown();
            }
        });
        IoFuture connect = client.connect(new InetSocketAddress("85.25.149.26", 28070));
        latch.await(10, TimeUnit.SECONDS);
    }
}
