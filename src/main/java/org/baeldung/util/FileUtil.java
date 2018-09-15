package org.baeldung.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class FileUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    private static final int BUFFER_SIZE = 1024;

    private FileUtil() {
    }

    public static byte[] gzipCompress(byte[] uncompressedData) {
        byte[] result = new byte[] {};
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream(uncompressedData.length);
            GZIPOutputStream gzipOS = new GZIPOutputStream(bos);
            gzipOS.write(uncompressedData);
            gzipOS.close();
            result = bos.toByteArray();
        } catch (IOException e) {
            LOGGER.error("Unexpect error occur during compression", e);
        }
        return result;
    }

    public static byte[] gzipUncompress(byte[] compressedData) throws IOException {
        byte[] result = new byte[] {};
        try (ByteArrayInputStream bis = new ByteArrayInputStream(compressedData);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                GZIPInputStream gzipIS = new GZIPInputStream(bis)) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int len;
            while ((len = gzipIS.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            result = bos.toByteArray();
        }
        return result;
    }

}
