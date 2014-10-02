package com.kevinychen.chess.test.util;

import com.kevinychen.chess.main.pieces.Piece;
import com.kevinychen.chess.main.util.NetworkMessage;
import com.kevinychen.chess.main.util.Preferences;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class NetworkMessageTest {

    @Test
    public void testSerializable() {
        NetworkMessage networkMessage = new NetworkMessage(NetworkMessage.Type.HANDSHAKE);
        networkMessage.setPlayerName("Player");
        networkMessage.setColor(Piece.Color.WHITE);
        networkMessage.setNetworkMode(Preferences.NetworkMode.HOST);
        networkMessage.setTimerMode(Preferences.TimerMode.COUNTDOWN);
        networkMessage.setTimeLimit(30);
        networkMessage.setUsingCustomPieces(false);
        networkMessage.setBoardReversed(true);
        networkMessage.setMove('a', 1, 'b', 2);
        networkMessage.setUndoAccepted(false);

        // Serializing
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("/tmp/networkmessage.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(networkMessage);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            assertTrue(false);
        }

        // Deserializing
        NetworkMessage deserializedNetworkMessage = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("/tmp/networkmessage.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            deserializedNetworkMessage = (NetworkMessage) objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNotNull(deserializedNetworkMessage);
        assertEquals("Player", deserializedNetworkMessage.getPlayerName());
        assertEquals(Piece.Color.WHITE, deserializedNetworkMessage.getColor());
        assertEquals(Preferences.NetworkMode.HOST, deserializedNetworkMessage.getNetworkMode());
        assertEquals(Preferences.TimerMode.COUNTDOWN, deserializedNetworkMessage.getTimerMode());
        assertEquals(30, deserializedNetworkMessage.getTimeLimit());
        assertEquals(false, deserializedNetworkMessage.isUsingCustomPieces());
        assertEquals(true, deserializedNetworkMessage.isBoardReversed());
        assertEquals('a', deserializedNetworkMessage.getOriginFile());
        assertEquals(false, deserializedNetworkMessage.isUndoAccepted());
    }
}
