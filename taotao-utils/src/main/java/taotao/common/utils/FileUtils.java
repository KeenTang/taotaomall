package taotao.common.utils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: keen
 * Date: 2018-10-06
 * Time: 17:36
 */
public class FileUtils {
    //SEPARATOR
    private static final char UNIX_SEPARATOR = '/';
    private static final char WINDOWS_SEPARATOR = '\\';

    /*
    public static void copyTo(File file, String to, boolean isCover) throws IOException {
        if (file == null) {
            throw new NullPointerException();
        }
        if (to == null || to.trim().length() == 0) {
            return;
        }
        File copyFile = new File(to);
        if (copyFile.exists()) {
            if (isCover) {
                ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
                try (FileInputStream fis = new FileInputStream(file);
                     FileChannel fisChannel = fis.getChannel();
                     FileOutputStream fos = new FileOutputStream(copyFile);
                     FileChannel fosChannel = fos.getChannel()) {
                    int len;
                    while ((len = fisChannel.read(byteBuffer)) > 0) {
                        fosChannel.write(byteBuffer,);
                    }
                } finally {

                }

            }
        }
    }*/


    public static File[] local(File dir, final String regx) {
        return dir.listFiles(new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regx);

            @Override
            public boolean accept(File file, String name) {
                return pattern.matcher(new File(name).getName()).matches();
            }
        });
    }

    public static File[] local(String path, String regx) {
        return local(new File(path), regx);
    }

    public static boolean moveTo(File file, String destination, boolean isCover) {
        if (file == null) {
            throw new NullPointerException();
        }
        if (destination == null || destination.length() == 0) {
            return false;
        }
        //d:\\a---e:\\a
        //d:\\a.txt---e:\\a.txt
        File tempFile = new File(destination, file.getName());
        String path = file.getName();
        if (tempFile.exists()) {
            if (!isCover) {
                if (tempFile.isDirectory()) {
                    path = tempFile.getName() + "_副本";
                } else {
                    path = getFullFilePath(file) + getFileNameWithoutExtension(file) + "_副本" + getFileExtension(file);
                }
            }
        }
        return file.renameTo(new File(path));
    }

    public static String getFileNameWithoutExtension(File file) {
        if (file == null || file.isDirectory()) {
            return "";
        }
        String fileName = file.getName();
        int length = fileName.lastIndexOf(".");
        return fileName.substring(0, length);
    }

    public static String getFullFilePath(File file) {
        if (file == null) {
            return "";
        }
        String filePath = file.getAbsolutePath();
        if (file.isDirectory()) {
            return filePath;
        }
        return filePath.substring(0, filePath.lastIndexOf(File.separator) + 1);
    }

    public static String getFileExtension(File file) {
        if (file == null) {
            throw new NullPointerException();
        }
        if (file.isDirectory()) {
            throw new IllegalArgumentException();
        }
        String fileName = file.getName();
        return fileName.substring(fileName.lastIndexOf("."), fileName.length());
    }
}
