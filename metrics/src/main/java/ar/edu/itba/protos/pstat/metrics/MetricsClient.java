package ar.edu.itba.protos.pstat.metrics;

import ar.edu.itba.protos.pstat.interfaces.Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class MetricsClient implements Runnable {

    //Main Logger info
    private static final Logger LOG = LoggerFactory.getLogger(MetricsClient.class.getSimpleName());

    private static final String METRICS_REQUEST = "getmetrics";

    private final String ip;
    private final Integer port;
    private final Protocol protocol;

    private final SocketChannel channel;

    private final byte[] request = METRICS_REQUEST.getBytes();

    public MetricsClient(final String metricsIp, final Integer metricsPort, Protocol protocol) throws IOException{
        this.ip = metricsIp;
        this.port = metricsPort;
        this.protocol = protocol;

        channel = SocketChannel.open();
        channel.configureBlocking(false);
    }




    public void run() {
        LOG.info("Running Metrics client Task");
        LOG.info("Metrics: Ip => {}, Port => {}", ip, port);

        //____________________________________________________
        // Initiate connection to server and repeatedly poll until complete
        if (!channel.connect(new InetSocketAddress(ip, port))) {
            while (!clntChan.finishConnect()) {
                System.out.print("."); // Do something else
            }
        }

        final ByteBuffer writeBuffer = ByteBuffer.wrap(METRICS_REQUEST);

        ByteBuffer writeBuf = ByteBuffer.wrap(request);

        int totalBytesRcvd = 0;
        int bytesRcvd;

        while (totalBytesRcvd < argument.length) {
            if (writeBuf.hasRemaining()) {
                clntChan.write(writeBuf);
            }
            if ((bytesRcvd = clntChan.read(readBuf)) == -1) {
                throw new SocketException("Connection closed prematurely");
            }
            totalBytesRcvd += bytesRcvd;
            System.out.print("."); // Do something else
        }
        System.out.println("Received: " + // convert to String per default
                // charset
                new String(readBuf.array(), 0, totalBytesRcvd));
        clntChan.close();
        //____________________________________________________
    }
}
