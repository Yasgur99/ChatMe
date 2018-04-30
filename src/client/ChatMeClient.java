package chatme;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author michaelmaitland
 */
public class ChatMeClient {

    private int port;
    private String host;
    private Socket connection;
    public OutputStream output;
    private InputStream input;
    private ExecutorService executor;

    public ChatMeClient(int port, String host) {
        this.port = port;
        this.host = host;
        this.executor = Executors.newFixedThreadPool(2);
    }

    public boolean connect() {
        try {
            this.connection = new Socket(host, port);
            this.input = this.connection.getInputStream();
            this.output = this.connection.getOutputStream();
            return true;
        } catch (UnknownHostException ex) {
        } catch (IOException ex) {
        }
        return false;
    }

    public String readMessage() {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() {
                StringBuilder sb = new StringBuilder();
                try {
                    int c;
                    while ((c = input.read()) != -1) {
                        sb.append((char) c);
                    }
                    return sb.toString();
                } catch (IOException ex) {
                    return null;
                }
            }
        };
        Future<String> future = executor.submit(callable);
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException ex) {
        }
        return null;
    }

    public boolean writeMessage(String message) {
        Callable<Boolean> callable = new Callable<Boolean>() {
            @Override
            public Boolean call() {
                try {
                    Writer out = new OutputStreamWriter(output);
                    out.write(message);
                    out.flush();
                    return true;
                } catch (IOException ex) {
                }
                return false;
            };
        };
        Future<Boolean> future = executor.submit(callable);

        try {
            return future.get();
        } catch (InterruptedException | ExecutionException ex) {
        }
        return false;
    }

    public boolean closeConnection() {
        try {
            this.output.flush();
            this.output.close();
            this.input.close();
            return true;
        } catch (IOException ex) {
        }
        return false;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Socket getConnection() {
        return connection;
    }

    public OutputStream getOutput() {
        return output;
    }

    public InputStream getInput() {
        return input;
    }

}
