package budgetaryinfoXXYN;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.EventQueue;
import java.awt.Graphics;
public class RunnableOne {

		public static void main(String[] args) throws Exception {

		EventQueue.invokeLater(() ->
		{

			FormFrame formf = new FormFrame();
			
		     SQLConnect sqlConnect = new SQLConnect();


	});

	}

	}
