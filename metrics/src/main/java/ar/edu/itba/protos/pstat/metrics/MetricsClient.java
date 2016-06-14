package ar.edu.itba.protos.pstat.metrics;

import ar.edu.itba.protos.pstat.interfaces.Protocol;
import ar.edu.itba.protos.pstat.models.PStatParser;
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

    private static final String METRICS_REQUEST = "getmetrics\r\n";
    private static final Integer RESPONSE_BFF_SIZE = 1024;

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

        // Initiate connection to server and repeatedly poll until complete
        if (!channel.connect(new InetSocketAddress(ip, port))) {
            while (!channel.finishConnect()) {
                System.out.print("."); // Do something else
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                }
            }
        }

        LOG.info("Metrics client Task");
        LOG.info("Metrics: Ip => {}, Port => {}", ip, port);

        final ByteBuffer readBuf = ByteBuffer.allocate(RESPONSE_BFF_SIZE);
        int bytesRcvd;
        if ((bytesRcvd = channel.read(readBuf)) == -1) {
            throw new SocketException("Connection closed prematurely");
        }

        LOG.info("Received: {}", new String(readBuf.array(), 0, bytesRcvd));

    }

    public void run(){
        LOG.info(".");
        final ByteBuffer readBuf = ByteBuffer.allocate(RESPONSE_BFF_SIZE);
        boolean gotResponse = false;
        //____________________________________________________
        try {

            ByteBuffer writeBuf = ByteBuffer.wrap(request);

            int totalBytesRcvd = 0;
            int bytesRcvd;

            while (!gotResponse){
                if (writeBuf.hasRemaining()) {
                    channel.write(writeBuf);
                }
                if ((bytesRcvd = channel.read(readBuf)) == -1) {
                    throw new SocketException("Connection closed prematurely");
                }

                gotResponse = PStatParser.gotResponse(new String(readBuf.array(), 0, totalBytesRcvd));

                totalBytesRcvd += bytesRcvd;
            }

            //System.out.println("Received: " + new String(readBuf.array(), 0, totalBytesRcvd));

            LOG.info("Response received from server, proceed to call handler");
            final String response = new String(readBuf.array(), 0, totalBytesRcvd);

            protocol.handle(response);

            //Metric metric = parser.parse(new String(readBuf.array(), 0, totalBytesRcvd));
            //channel.close();

        } catch (IOException e){
            LOG.error("Unable to start metrics client");
            System.exit(1);
        }
        //____________________________________________________
    }
}
