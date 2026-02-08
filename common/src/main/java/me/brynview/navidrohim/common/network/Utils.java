package me.brynview.navidrohim.common.network;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import me.brynview.navidrohim.common.Constants;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Utils {
    public static void writeVarInt(int value, DataOutputStream out) throws IOException {
        while ((value & 0xFFFFFF80) != 0L) {
            out.writeByte((value & 0x7F) | 0x80);
            value >>>= 7;
        }
        out.writeByte(value & 0x7F);
    }

    public static void writeFriendlyByteBufFormatUTF(String data, DataOutputStream out)
    {
        try {
            byte[] src = data.getBytes(StandardCharsets.UTF_8);

            if (src.length > Constants.PACKET_SIZE) {
                throw new RuntimeException("Packet size exceeded. More than %s".formatted(Constants.PACKET_SIZE));
            } else {
                writeVarInt(src.length, out);
                out.write(src);
            }
        } catch (IOException e) {
            Constants.getLogger().info(e.getMessage());
        }
    }

    public static int readVarInt(ByteArrayDataInput in) {
        int i = 0;
        int j = 0;

        byte b0;

        do {
            b0 = in.readByte();
            i |= (b0 & 127) << j++ * 7;
            if (j > Constants.PACKET_SIZE) {
                throw new RuntimeException("VarInt too big");
            }
        } while ((b0 & 128) == 128);

        return i;
    }

    public static String readUtf(ByteArrayDataInput in) {
        int j = readVarInt(in);

        if (j > Constants.PACKET_SIZE * 4) {
            throw new RuntimeException("The received encoded string buffer length is longer than maximum allowed (" + j + " > " + Constants.PACKET_SIZE * 4 + ")");
        } else if (j < 0) {
            throw new RuntimeException("The received encoded string buffer length is less than zero! Weird string!");
        } else {
            byte[] out = new byte[j];
            in.readFully(out, 0, j);

            String s = new String(out);

            if (s.length() > Constants.PACKET_SIZE) {
                throw new RuntimeException("The received string length is longer than maximum allowed (" + j + " > " + Constants.PACKET_SIZE + ")");
            } else {
                return s;
            }
        }
    }

    public static byte[] getByteArrayOutputStreamWithEncodedString(String string)
    {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteStream);

        Utils.writeFriendlyByteBufFormatUTF(string, dataOutputStream);
        return byteStream.toByteArray();
    }
}
