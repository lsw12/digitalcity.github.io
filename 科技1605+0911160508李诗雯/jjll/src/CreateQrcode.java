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

//����ͼƬ����
BufferedImage bufferedImage=new BufferedImage(imageSize,imageSize,BufferedImage.TYPE_INT_RGB);
//ͼƬ�滭
Graphics2D gs=bufferedImage.createGraphics();

//���ñ���ɫ
gs.setBackground(Color.white);

//�������
gs.clearRect(0, 0, imageSize,imageSize);
//ƫ����

int pixoff =2;
//��ά��滭
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
gs.fillRect(i*3+pixoff,j*3+pixoff,3,3);//ij�������������򽥱䣻i+j/2�Խ��߽���
}
}
}
BufferedImage logo=ImageIO.read(new File("D:/logo.jpg"));
int logoSize=imageSize/4;
//����ͼƬ�ڶ�ά���м�
int o=(imageSize-logoSize)/2;
gs.drawImage(logo, o, o, logoSize, logoSize, null);
gs.dispose();
bufferedImage.flush();
try {
ImageIO.write(bufferedImage, "png", new File("D:/logo.jpg"));	
}catch(IOException e){
e.printStackTrace();
System.out.println("��������");
}
System.out.println("���ɶ�ά��");

}

}
