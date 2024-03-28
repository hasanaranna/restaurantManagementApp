package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Threads.readThread;
import Threads.updateFileThread;
import util.NetworkUtil;
import util.Order;
import util.clientToServerIntroduction;
import util.savedOrder;

public class serverMain {
    public static void main(String[] args) {
        Manager manager = new Manager();
        manager.setFoodFileName("menu.txt");
        manager.setRestaurantFileName("restaurant.txt");
        manager.setOrderFileName("order.txt");
        HashMap<String, NetworkUtil> clientMap;
        List<String> customerNames = new ArrayList<>();
        List<savedOrder> savedOrderList = new ArrayList<>();
        try {
            clientMap = new HashMap<>();
            ServerSocket serverSocket = new ServerSocket(35000);

            System.out.println("Server started");


            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    manager.readFiles();
                    System.out.println("Client connected");
                    NetworkUtil networkUtil = new NetworkUtil(socket);
                    networkUtil.write(manager);
                    Object object = networkUtil.read();
                    if(object instanceof clientToServerIntroduction)
                    {
                        clientToServerIntroduction csi = (clientToServerIntroduction) object;
                        if(csi.getWhat().equals("client")){
                            String clientName = csi.getUserName();
                            System.out.println(clientName+" has communicated");
                            clientMap.put(clientName, networkUtil);
                            customerNames.add(clientName);
                            new readThread(clientMap, networkUtil, savedOrderList, customerNames);
                        }
                        else if(csi.getWhat().equals("restaurant")){
                            String clientName = csi.getUserName();
                            System.out.println(clientName+" has communicated");
                            clientMap.put(clientName, networkUtil);
                            customerNames.add(clientName);
                            new readThread(clientMap, networkUtil, savedOrderList, customerNames);
                        }
                    }

                } catch (Exception e) {
                    System.out.println(e);
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}