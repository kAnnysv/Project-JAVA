package ex03;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        List<String> urlList = new ArrayList<>();
        urlList = Files.readAllLines(Paths.get("C:\\Users\\valss\\Desktop\\java\\DAY03\\src\\ex03\\file_urls.txt"));


        ExecutorService executorService = Executors.newFixedThreadPool(3);



        for(int i = 0; i < urlList.size(); i++){
            String str = (urlList.get(i).split(" "))[1];
            executorService.submit(new Downloader(str, str, i));

        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

class Downloader implements Runnable{
    private URL url;
    private int id;
    private String fileName;
    private int i;
    private static Path path = Paths.get("C:\\Users\\valss\\Desktop\\java\\DAY03\\src\\ex03\\download");

    public Downloader(String s, String fileName, int i) throws MalformedURLException {

        this.url = new URL(s);
        this.id = Count.getCount().IdGenerator();
        this.fileName = fileName;
        this.i = i;


    }
    public void fileDownload() throws IOException {
        System.out.println("Thread - " + id + " start download " + " file number " + i );
        ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
        FileOutputStream outputStream = new FileOutputStream(String.valueOf(path.resolve(Paths.get(fileName.substring(fileName.length() - 10)))));
        outputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        System.out.println("Thread - " + id + " finish download " + " file number " + i );

        readableByteChannel.close();
        outputStream.close();



    }


    @Override
    public void run() {
        try {
            fileDownload();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
class Count{
    private static Integer id = 1;
    private static Count count;
    private Count(){};

    public static Count getCount(){
        if(count == null){
            count = new Count();
        }
        return count;
    }
    public int IdGenerator(){
        return id++;
    }


}



