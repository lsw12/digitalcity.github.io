import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class CreateQrcode {

public static void main(String args[]) throws IOException{
int version=6;
int imageSize=127;

Qrcode s=new Qrcode();
s.setQrcodeVersion(version);
String content="learn.unipus.cn";
byte[] data=content.getBytes("utf-8");
boolean[][] x=s.calQrcode(data);

//设置图片缓冲
BufferedImage bufferedImage=new BufferedImage(imageSize,imageSize,BufferedImage.TYPE_INT_RGB);
//图片绘画
Graphics2D gs=bufferedImage.createGraphics();

//设置背景色
gs.setBackground(Color.white);

//清除画布
gs.clearRect(0, 0, imageSize,imageSize);
//偏移量

int pixoff =2;
//二维码绘画
int startR=225,startG=0,startB=225;
int endR=0,endB=0,endG=255;
for(int i=0;i<x.length;i++){
for(int j=0;j<x.length;j++){
if(x[i][j]){
Random rand=new Random();

int num1=startR+(endR-startR)/x.length*((i+j)/2);
int num2=startG+(endG-startG)/x.length*((i+j)/2);
int num3=startR+(endB-startB)/x.length*((i+j)/2);

Color color=new Color(num1, num2, num3);
gs.setColor(color);
gs.fillRect(i*3+pixoff,j*3+pixoff,3,3);//ij互换可以竖向径向渐变；i+j/2对角线渐变
}
}
}
BufferedImage logo=ImageIO.read(new File("D:/logo.jpg"));
int logoSize=imageSize/4;
//设置图片在二维码中间
int o=(imageSize-logoSize)/2;
gs.drawImage(logo, o, o, logoSize, logoSize, null);
gs.dispose();
bufferedImage.flush();
try {
ImageIO.write(bufferedImage, "png", new File("D:/logo.jpg"));	
}catch(IOException e){
e.printStackTrace();
System.out.println("产生问题");
}
System.out.println("生成二维码");

}

}
