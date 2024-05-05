//package s1riys.lab7.server.network;
//
//import com.google.common.primitives.Bytes;
//import org.apache.commons.lang3.ArrayUtils;
//import org.apache.commons.lang3.SerializationException;
//import org.apache.commons.lang3.SerializationUtils;
//import org.apache.commons.lang3.tuple.ImmutablePair;
//import org.apache.commons.lang3.tuple.Pair;
//import org.apache.logging.log4j.Logger;
//import s1riys.lab7.common.network.UDPShared;
//import s1riys.lab7.common.network.requests.Request;
//import s1riys.lab7.common.network.responses.NoSuchCommandResponse;
//import s1riys.lab7.common.network.responses.Response;
//import s1riys.lab7.server.Main;
//import s1riys.lab7.server.handlers.CommandHandler;
//
//import java.io.IOException;
//import java.net.*;
//import java.nio.ByteBuffer;
//import java.nio.channels.DatagramChannel;
//import java.nio.channels.SelectionKey;
//import java.util.*;
//
//import static s1riys.lab7.common.constants.Network.PACKET_SIZE;
//import static s1riys.lab7.common.constants.Network.STOP_BYTE;
//
//public class UDPServerChannels extends UDPShared {
//    private static final Map<SelectionKey, byte[]> buffers = new HashMap<>();
//    private final CommandHandler commandHandler;
//    private boolean running = true;
//    private final Logger logger = Main.logger;
//
//    public UDPServerChannels(InetAddress address, int port, CommandHandler commandHandler) throws IOException {
//        super(address, port);
//        DatagramChannel channel = DatagramChannel.open();
//        channel.configureBlocking(false);
//        channel.bind(getAddr());
//
//        this.commandHandler = commandHandler;
//    }
//
//    private void processSelectedKeys(Set<SelectionKey> selectedKeys) throws IOException {
//        Iterator<SelectionKey> iterator = selectedKeys.iterator();
//
//        while (iterator.hasNext()) {
//            SelectionKey key = iterator.next();
//            iterator.remove();
//
//            if (key.isReadable()) {
//                handleRead(key);
//            }
//        }
//    }
//
//    private void handleRead(SelectionKey key) throws IOException {
//        DatagramChannel selectedChannel = (DatagramChannel) key.channel();
//
//        if (!buffers.containsKey(key)) {
//            buffers.put(key, new byte[0]);
//        }
//
//        handleDataRead(key, selectedChannel);
//    }
//
//    private void handleDataRead(SelectionKey key, DatagramChannel selectedChannel) throws IOException {
//        ByteBuffer buffer = ByteBuffer.allocate(PACKET_SIZE);
//        InetSocketAddress clientAddress = (InetSocketAddress) selectedChannel.receive(buffer);
//        buffer.flip();
//
//
//
//    }
//
////    public Pair<Byte[], SocketAddress> receiveData() throws IOException {
////        var received = false;
////        var result = new byte[0];
////        SocketAddress addr = null;
////
////        while (!received) {
////            var data = new byte[PACKET_SIZE];
////
////            var dp = new DatagramPacket(data, PACKET_SIZE);
////            datagramSocket.receive(dp);
////
////            addr = dp.getSocketAddress();
////            logger.info("Получено \"{}\" от {}", new String(data), dp.getAddress());
////
////            if (data[data.length - 1] == STOP_BYTE) {
////                received = true;
////                logger.info("Получение данных от {} завершено", dp.getAddress());
////            }
////            result = Bytes.concat(result, Arrays.copyOf(data, data.length - 1));
////        }
////        return new ImmutablePair<>(ArrayUtils.toObject(result), addr);
////    }
////
////    public void sendData(byte[] data, SocketAddress addr) throws IOException {
////        byte[][] dataChuncks = this.generateDataChuncks(data);
////
////        logger.info("Отправление чанков... ({} шт.)", dataChuncks.length);
////        for (int i = 0; i < dataChuncks.length; i++) {
////            var chunk = dataChuncks[i];
////            if (i == dataChuncks.length - 1) {
////                var lastChunk = Bytes.concat(chunk, new byte[]{STOP_BYTE});
////                var dp = new DatagramPacket(lastChunk, PACKET_SIZE, addr);
////                datagramSocket.send(dp);
////                logger.info("Последний чанк размером " + lastChunk.length + " отправлен на сервер.");
////            } else {
////                var dp = new DatagramPacket(ByteBuffer.allocate(PACKET_SIZE).put(chunk).array(), PACKET_SIZE, addr);
////                datagramSocket.send(dp);
////                logger.info("Чанк размером " + chunk.length + " отправлен на сервер.");
////            }
////        }
////
////        logger.info("Отправка данных завершена");
////    }
////
////    public void connectToClient(SocketAddress addr) throws SocketException {
////        datagramSocket.connect(addr);
////    }
////
////    public void disconnectFromClient() {
////        datagramSocket.disconnect();
////    }
////
////    public void close() {
////        datagramSocket.close();
////    }
////
////    public void run() {
////        logger.info("Сервер запущен по адресу {}", getAddr());
////
////        while (running) {
////            Pair<Byte[], SocketAddress> dataPair;
////            try {
////                dataPair = receiveData();
////            } catch (Exception e) {
////                logger.error("Ошибка получения данных : {}", e.toString(), e);
////                disconnectFromClient();
////                continue;
////            }
////
////            var dataFromClient = dataPair.getKey();
////            var clientAddr = dataPair.getValue();
////
////            try {
////                connectToClient(clientAddr);
////                logger.info("Соединено с " + clientAddr);
////            } catch (Exception e) {
////                logger.error("Ошибка соединения с клиентом: {}", e.toString(), e);
////            }
////
////            Request request;
////            try {
////                request = SerializationUtils.deserialize(ArrayUtils.toPrimitive(dataFromClient));
////                logger.info("Обработка {} от {}", request, clientAddr);
////            } catch (SerializationException e) {
////                logger.error("Невозможно десериализовать объект запроса", e);
////                disconnectFromClient();
////                continue;
////            }
////
////            Response response = null;
////            try {
////                response = commandHandler.handle(request);
////            } catch (Exception e) {
////                logger.error("Ошибка выполнения команды: {}", e.toString(), e);
////            }
////            if (response == null) response = new NoSuchCommandResponse(request.getName());
////
////            var data = SerializationUtils.serialize(response);
////            logger.info("Ответ: {}", response);
////
////            try {
////                sendData(data, clientAddr);
////                logger.info("Отправлен ответ клиенту по адресу {}", clientAddr);
////            } catch (Exception e) {
////                logger.error("Ошибка ввода-вывода: {}", e.toString(), e);
////            }
////
////            disconnectFromClient();
////            logger.info("Отключение от клиента {}", clientAddr);
////        }
////
////        close();
////    }
//}