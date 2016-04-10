/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.io.notifications;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;

import java.util.Collection;

public class NotifierService {

    private SocketIOServer server;

    // https://github.com/mrniko/netty-socketio/wiki/Configuration-details
    public NotifierService() {

        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(82);

        server = new SocketIOServer(config);

        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient client) {
                System.out.println("Client connected on socket.io !");
            }
        });

        server.start();
        System.out.println("NotifierService is started !");
    }

    public void notifyClients(Event event, Object message) {

        Collection<SocketIOClient> clients = this.server.getAllClients();

        for (SocketIOClient c : clients) {
            System.out.println("Notify client of : " + event.name());
            c.sendEvent(event.name(), message);

        }

    }
}
