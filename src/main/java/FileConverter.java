import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;

/**
 * Created by Shoma on 18.07.2017.
 */
public class FileConverter {
    public byte[] loadFileAsByteArray(String fileName) throws IOException {
        File file = new File(fileName);
        int fileLength = (int) file.length();
        byte[] byteArray = new byte[fileLength];

        BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file));
        reader.read(byteArray, 0, fileLength);
        reader.close();
        return byteArray;
    }

    public void writeByteArrayToFile(String fileName, byte[] bytes) throws IOException {
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(fileName));
        outputStream.write(bytes, 0, bytes.length);
        outputStream.flush();
        outputStream.close();
    }

    public String encode (String fileName) throws IOException {
      return Base64.encodeBase64String(loadFileAsByteArray(fileName));
    }

    public void decode (String source, String fileName) throws IOException {

        byte[] bytes = Base64.decodeBase64(source);
        writeByteArrayToFile(fileName,bytes);
    }
}
