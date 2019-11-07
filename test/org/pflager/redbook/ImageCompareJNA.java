package org.pflager.redbook;

import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.sun.jna.Memory;
import com.sun.jna.platform.win32.GDI32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HBITMAP;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.RECT;
import com.sun.jna.platform.win32.WinGDI;
import com.sun.jna.platform.win32.WinGDI.BITMAPINFO;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.platform.win32.WinUser;

import jna.extra.GDI32Extra;
import jna.extra.User32Extra;
import jna.extra.WinGDIExtra;

public class ImageCompareJNA extends JFrame {
	private static String AppWindowName;
	private static final long serialVersionUID = 1L;

	public static boolean compareImages(File FirstFile, File SecondFile) {
		try {
			BufferedImage biA = ImageIO.read(FirstFile);
			DataBuffer dbA = biA.getData().getDataBuffer();
			int sizeA = dbA.getSize();
			BufferedImage biB = ImageIO.read(SecondFile);
			DataBuffer dbB = biB.getData().getDataBuffer();
			int sizeB = dbB.getSize();
			if (sizeA == sizeB) {
				for (int i = 0; i < sizeA; i++) {
					if (dbA.getElem(i) != dbB.getElem(i)) {
						return false;
					}
				}
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("Failed to compare image files.");
			return false;
		}
	}

	public String classpath;
	private String fileformat = "jpg";
	private File FirstFile;
	private HWND hWnd;
	BufferedImage image;
	private String ImageName;
	public String javaBin;
	public String javaHome;
	private double MaxWaitTime = 10000; // Milliseconds

	private String osName;
	private Process process;
	Process PS = null;

	private File SecondFile;

	public ImageCompareJNA() throws HeadlessException {
		osName = System.getProperty("os.name");
		javaHome = System.getProperty("java.home");
		javaBin = javaHome + File.separator + "bin" + File.separator + "java";
		classpath = System.getProperty("java.class.path");
		FirstFile = null;
		SecondFile = null;
	}

	public boolean capture(String ExportPath) {
		if (hWnd == null) {
			System.out.print("Window not found!");
			return false;
		}

		HDC hdcWindow = User32.INSTANCE.GetDC(hWnd);
		HDC hdcMemDC = GDI32.INSTANCE.CreateCompatibleDC(hdcWindow);

		RECT bounds = new RECT();
		User32Extra.INSTANCE.GetClientRect(hWnd, bounds);

		int width = bounds.right - bounds.left;
		int height = bounds.bottom - bounds.top;

		HBITMAP hBitmap = GDI32.INSTANCE.CreateCompatibleBitmap(hdcWindow, width, height);

		HANDLE hOld = GDI32.INSTANCE.SelectObject(hdcMemDC, hBitmap);
		GDI32Extra.INSTANCE.BitBlt(hdcMemDC, 0, 0, width, height, hdcWindow, 0, 0, WinGDIExtra.SRCCOPY);

		GDI32.INSTANCE.SelectObject(hdcMemDC, hOld);
		GDI32.INSTANCE.DeleteDC(hdcMemDC);

		BITMAPINFO bmi = new BITMAPINFO();
		bmi.bmiHeader.biWidth = width;
		bmi.bmiHeader.biHeight = -height;
		bmi.bmiHeader.biPlanes = 1;
		bmi.bmiHeader.biBitCount = 32;
		bmi.bmiHeader.biCompression = WinGDI.BI_RGB;

		Memory buffer = new Memory(width * height * 4);
		GDI32.INSTANCE.GetDIBits(hdcWindow, hBitmap, 0, height, buffer, bmi, WinGDI.DIB_RGB_COLORS);

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		image.setRGB(0, 0, width, height, buffer.getIntArray(0, width * height), 0, width);

		File outputfile = new File(ExportPath + ImageName);

		try {
			ImageIO.write(image, fileformat, outputfile);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		GDI32.INSTANCE.DeleteObject(hBitmap);
		User32.INSTANCE.ReleaseDC(hWnd, hdcWindow);
		return true;
	}

	public boolean /* succeeded */ captureCRedbookReferencePng(String windowName /* aka Window Title */) throws InterruptedException, IOException {
		if (osName.contentEquals("Linux")) {
			process = Runtime.getRuntime().exec("redbook-1.1-src/src/" + windowName);
			Thread.sleep(1000);

			try {
				String testClassName = getClass().getName();
				testClassName = testClassName.substring(testClassName.lastIndexOf('.') + 1);

				Process screenShotProcess = Runtime.getRuntime().exec("./screenshot.bash " + windowName + " " + testClassName + ".reference.png");

				StringBuilder stdoutString = new StringBuilder();
				StringBuilder stderrString = new StringBuilder();

				BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(screenShotProcess.getInputStream()));
				BufferedReader stderrReader = new BufferedReader(new InputStreamReader(screenShotProcess.getErrorStream()));

				String line;
				while ((line = stdoutReader.readLine()) != null) {
					stdoutString.append(line + "\n");
				}
				while ((line = stderrReader.readLine()) != null) {
					stderrString.append(line + "\n");
				}

				int exitVal = screenShotProcess.waitFor();
				if (exitVal != 0) {
					// abnormal...
					System.err.println("Problems!");
					System.out.println(stdoutString);
					System.err.println(stderrString);
					process.destroyForcibly();
					return false;
				}
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
				process.destroyForcibly();
				return false;
			}
			process.destroyForcibly();
			return true;
		} else {
			File Tempfile = new File("redbook-1.1-src/src/" + windowName + "CImage");
			if (Tempfile.exists() == false) {
				if (ExecuteEXE(windowName)) {// Running c application with window name
					Thread.sleep(8000);
					hWnd = User32.INSTANCE.FindWindow(null, windowName);
					if (hWnd == null) {
						hWnd = User32.INSTANCE.FindWindow(null, AppWindowName);
						if (hWnd == null) {
							hWnd = User32.INSTANCE.FindWindow(null, windowName + ".c");
						}
					}
					if (hWnd != null) {
						ImageName = windowName + "CImage";
						if (capture("redbook-1.1-src/src/")) { // Capturing c application window
							PS.destroy(); // Ending C executable process
							return true;
						}
					} else {
						PS.destroy(); // Ending C executable process
						return false;
					}
				}
				return false;
			} else {
				return true;
			}
		}
	}

	/**
	 *
	 * @param WindowName Name Name of the active Jglut window, this also used for running the C program
	 * @return Returns true if the image are identical
	 */
	public boolean CompareImage(String WindowName) {
		ImageName = WindowName;
		hWnd = User32.INSTANCE.FindWindow(null, WindowName);// finding jglut window

		User32.INSTANCE.PostMessage(hWnd, WinUser.WM_CLOSE, null, null);
		if (capture("redbook-1.1-src/src/")) { // Capturing jglut window
			if (ExecuteEXE(WindowName)) {// Running c application with window name
				hWnd = User32.INSTANCE.FindWindow(null, AppWindowName);
				ImageName = WindowName + "CImage";
				if (capture("redbook-1.1-src/src/")) { // Capturing c application window
					PS.destroy(); // Ending C executable process
					SecondFile = new File("redbook-1.1-src/src/" + WindowName + "CImage");
					if (compareImages(FirstFile, SecondFile)) {
						FirstFile.delete();
						// SecondFile.delete();
						System.out.println("Identical Image");
						return true;
					} else {
						FirstFile.delete();
						// SecondFile.delete();
						System.out.println("Different Image");
						return false;
					}
				}
			}
		}
		return false;
	}

	public boolean /* matches */ captureAndCompareJGlutRedbookWithCRedbook(String windowName /* aka Window Title */) throws InterruptedException, IOException {
		if (osName.contentEquals("Linux")) {
			Thread.sleep(1000);

			try {
				Process screenShotProcess = Runtime.getRuntime().exec("./screenshot.bash " + windowName + " " + windowName + ".png");

				StringBuilder stdoutString = new StringBuilder();
				StringBuilder stderrString = new StringBuilder();

				BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(screenShotProcess.getInputStream()));
				BufferedReader stderrReader = new BufferedReader(new InputStreamReader(screenShotProcess.getErrorStream()));

				String line;
				while ((line = stdoutReader.readLine()) != null) {
					stdoutString.append(line + "\n");
				}
				while ((line = stderrReader.readLine()) != null) {
					stderrString.append(line + "\n");
				}

				int exitVal = screenShotProcess.waitFor();
				if (exitVal != 0) {
					// abnormal...
					System.err.println("Problems!");
					System.out.println(stdoutString);
					System.err.println(stderrString);
					process.destroyForcibly();
					return false;
				}
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
				process.destroyForcibly();
				return false;
			}
			process.destroyForcibly();

			String testClassName = getClass().getName();
			testClassName = testClassName.substring(testClassName.lastIndexOf('.') + 1);

			boolean success = compareImages(new File("artifacts/" + windowName + ".png"), new File("artifacts/" + testClassName + ".reference.png"));

			Files.delete(Paths.get("artifacts/" + windowName + ".png"));

			return success;
		} else {
			ImageName = windowName;
			hWnd = User32.INSTANCE.FindWindow(null, windowName);// finding jglut window
			if (hWnd == null) {
				hWnd = User32.INSTANCE.FindWindow(null, windowName + ".java");
			}
			double waitTime = 0;
			while ((hWnd == null) && (waitTime < MaxWaitTime)) {
				waitTime = waitTime + 500;
				try {
					Thread.sleep(500);
					hWnd = User32.INSTANCE.FindWindow(null, windowName);// finding jglut window
					if (hWnd == null) {
						hWnd = User32.INSTANCE.FindWindow(null, windowName + ".java");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Thread.sleep(3500);
			// User32.INSTANCE.PostMessage(hWnd, WinU'ser.WM_CLOSE, null, null);
			if (capture("redbook-1.1-src/src/")) { // Capturing jglut window
				File FirstFile = new File("redbook-1.1-src/src/" + windowName);
				File SecondFile = new File("redbook-1.1-src/src/" + windowName + "CImage");
				if (compareImages(FirstFile, SecondFile)) {
					FirstFile.delete();
					// SecondFile.delete();
					System.out.println("Identical Image");
					return true;
				} else {
					FirstFile.delete();
					// SecondFile.delete();
					System.out.println("Different Image");
					return false;
				}
			}
		}
		return false;
	}

	private boolean ExecuteEXE(String CFileName) {
		try {
			PS = new ProcessBuilder("redbook-1.1-src/src/" + CFileName + ".exe").start();
			AppWindowName = "redbook-1.1-src\\src\\" + CFileName + ".exe";
			hWnd = null;
			hWnd = User32.INSTANCE.FindWindow(null, CFileName);
			if (hWnd == null) {
				hWnd = User32.INSTANCE.FindWindow(null, AppWindowName);
				if (hWnd == null) {
					hWnd = User32.INSTANCE.FindWindow(null, CFileName + ".c");
					System.out.println("AppWindowName" + AppWindowName);
				}
			}
			double waitTime = 0;
			while ((hWnd == null) && (waitTime < MaxWaitTime)) {
				waitTime = waitTime + 100;
				try {
					Thread.sleep(100);
					// System.out.println("Wait time" + waitTime);
					hWnd = User32.INSTANCE.FindWindow(null, CFileName);
					if (hWnd == null) {
						hWnd = User32.INSTANCE.FindWindow(null, AppWindowName);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	protected void finalize() throws Throwable {
		process.destroy();
		if (FirstFile != null)
			FirstFile.delete();
		if (SecondFile != null)
			FirstFile.delete();
	}

	public void RunNewProcess(String classname) throws IOException {
		process = new ProcessBuilder(javaBin, "-cp", classpath, classname).inheritIO().start();
	}

}