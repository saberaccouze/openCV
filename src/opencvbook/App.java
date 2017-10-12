/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencvbook;

/**
 *
 * @author saber
 */
import  main.java.org.javaopencvbook.util.ImageViewer;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
public class App 
{    
    public static byte[] loc(int x,int y,int n,int cols,int rows,byte[] buffer)
        {   
            for ( int i = x-n; i <= x+n; i++) {
                            for (int j = y-n; j <= y+n; j++) {
                                buffer[(j*cols+i)*3]=0;
                                buffer[(j*cols+i)*3+1]=0;   
                                buffer[(j*cols+i)*3+2]=0; 
                            }
                        }
            return buffer;
        }

	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

	public static void main(String[] args) throws Exception {
		String filePath = "src/main/resources/images/tetouan.jpg";
		Mat Image = Imgcodecs.imread(filePath);
		
		if(Image.dataAddr()==0){
			System.out.println("Erreur d'ouverture de fichier " + filePath);
		}else{
			ImageViewer imageViewer = new ImageViewer();
			imageViewer.show(Image, "Image Chargée");
			int totalBytes = (int)(Image.total()*Image.elemSize());
			byte buffer[] = new byte[totalBytes];
			Image.get(0, 0, buffer);
                        int x=894,y=6;
			
                               /* buffer[(y*Image.cols()+x)*3]=0;
                                buffer[(y*Image.cols()+x)*3+1]=0;
                                buffer[(y*Image.cols()+x)*3+2]=0;
                            */
                       
                        System.out.println(Image.cols()+"x"+Image.rows());
				/*if(i%3==1)
					buffer[i]=0;*/
                        buffer=loc(x,y,5,Image.cols(),Image.rows(),buffer);
			Image.put(0, 0, buffer);
			ImageViewer imageViewer1 = new ImageViewer();
			imageViewer1.show(Image, "Image Changer");
		}

	}
}
