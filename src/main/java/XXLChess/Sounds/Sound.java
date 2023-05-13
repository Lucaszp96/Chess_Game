package XXLChess.Sounds;

import javax.sound.sampled.*;
import java.io.File;

public class Sound {
    private AudioInputStream audioStream;
    private AudioFormat audioFormat;
    private SourceDataLine sourceDataLine;

    public Sound(String path) {
        playMusic(path);
    }

    public static void playMusic(String path){
        try {
//            File musicPath = new File(path);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File(path));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
//        try{
//            int count;
//            byte buf[] = new byte[2048];
//            //获取音频输入流
//            audioStream = AudioSystem.getAudioInputStream(new File(path));
//            //获取音频格式
//            audioFormat = audioStream.getFormat();
//
//            //封装音频信息
//            DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class,
//                    audioFormat,AudioSystem.NOT_SPECIFIED);
//            //获取虚拟扬声器（SourceDataLine）实例
//            sourceDataLine = (SourceDataLine)AudioSystem.getLine(dataLineInfo);
//
//            sourceDataLine.open(audioFormat);
//            sourceDataLine.start();
//            //播放音频
//            while((count = audioStream.read(buf,0,buf.length)) != -1){
//                sourceDataLine.write(buf,0,count);
//            }
//            //播放结束，释放资源
//            sourceDataLine.drain();
//            sourceDataLine.close();
//            audioStream.close();
//
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
    }
}
