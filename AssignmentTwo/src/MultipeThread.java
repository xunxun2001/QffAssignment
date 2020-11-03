import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class MultipeThread {
    public static void main(String[] args)  {
        String srcpath = "C:\\Users\\fhrlk\\Desktop\\QFF\\a.txt";
        String destpath = "C:\\Users\\fhrlk\\Desktop\\QFF\\b.txt";

        MutilThreadCopyFile(srcpath,destpath,14);

    }


    public static void MutilThreadCopyFile(String srcPath,String destPath,Integer ThreadNum){
        if(ThreadNum < 1){
            return;
        }

        File file = new File(srcPath);
        long length = file.length();
        long len = length/ThreadNum;
        // System.out.println(len);
        int sum = 0;
        for (int i = 0; i < ThreadNum-1; i++) {
            SubThread subThread = new SubThread(srcPath, destPath, i * len + sum, (i + 1) * len + sum, i, len);
            //System.out.println("第"+i+"个子线程启动");
            subThread.start();
            String add = "--" + i + ". Thread read:";
            sum += add.getBytes().length;
        }
        if (ThreadNum > 1) {
            SubThread subThread = new SubThread(srcPath, destPath, (ThreadNum-1) * len + sum, file.length() + sum,ThreadNum-1, len);
            //System.out.println("lastThread start...");
            subThread.start();
        }
    }
}
class SubThread extends Thread{
    private String srcPath;
    private String destPath;
    private long startIndex;
    private long endIndex;
    private int id;
    private int len;
    public SubThread(String srcPath,String destPath,long startIndex,long endIndex, int id, long len){
        this.srcPath = srcPath;
        this.destPath = destPath;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.id = id;
        this.len = (int)len;
    }


    @Override
    public void run() {
        try {
            RandomAccessFile srcFile = new RandomAccessFile(srcPath,"r");
            RandomAccessFile destFile = new RandomAccessFile(destPath, "rw");
            //System.out.println(startIndex);
            srcFile.seek(id * len);
            destFile.seek(startIndex);

            long index = startIndex;

            byte[] bytes = new byte[1000];

            int  n;
            String add = "--" + id + ". Thread read:";
            int length = add.getBytes().length;
            destFile.write(add.getBytes(),0, length);
            index += length;
            while ((n = srcFile.read(bytes)) != -1){
                index+=len;

                destFile.write(bytes,0,len);

                if(index >= endIndex+length){
                    break;
                }
            }
            srcFile.close();
            destFile.close();
            //System.out.println("第"+id+"个子线程结束");
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
}
