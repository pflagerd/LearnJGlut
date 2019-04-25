 package com.pflager.redbook;

import java.io.*;

public class ExecuteCProgram {
	private String CFileName;
	private static String AppWindowName ;

	public ExecuteCProgram(String cFileName) {
		super();
		CFileName = cFileName;
	}

	public void CompilingCFile() {
		//ProcessBuilder Compiler = new ProcessBuilder("g++", "/redbook-1.1-src/src/" +  CFileName + ".c", "-o", CFileName+".exe");
		ProcessBuilder Compiler = new ProcessBuilder("g++", "C:\\Users\\Administrator\\Desktop\\TmpGlut\\Temp.c", "-o", CFileName+".exe");
		try {
			Compiler.start();
//			try {
//				Compiler.wait();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Compiler.wait(2000);
		
	}

	public void ExecuteEXE() {
		AppWindowName = "c:\\Users\\Administrator\\Desktop\\New folder\\jglut-code\\redbook-1.1-x86_64-w64-mingw32\\src\\aargb.exe";
		Runtime run  = Runtime.getRuntime(); 
        try {
			Process proc = run.exec(AppWindowName);
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
//		Process PS = null;
//		try {
//			PS = new ProcessBuilder( "c:\\Program Files\\TortoiseGit\\bin\\notepad2.exe").start();
//			//PS = new ProcessBuilder( System.getProperty("user.dir") + "\\" + CFileName +  ".exe").start();
//		
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		InputStream is = PS.getInputStream();
//		InputStreamReader isr = new InputStreamReader(is);
//		BufferedReader br = new BufferedReader(isr);
//		String line;
        
	}
	public static void main(String[] args) {
		ExecuteCProgram ExecuteCProgramObj = new ExecuteCProgram("Temp");
		ExecuteCProgramObj.CompilingCFile();
		ExecuteCProgramObj.ExecuteEXE();
		ImageCompareJNA ImageCompareJNAObj = new ImageCompareJNA(AppWindowName);
		if (ImageCompareJNAObj.capture()) {
			File	FirstFile = new File("C:\\Users\\sa463e\\Desktop\\Geoduck2\\Tst\\A.png");
			File	 SecondFile = new File("C:\\Users\\sa463e\\Desktop\\Geoduck2\\Tst\\ACopy.png");
			if (ImageCompareJNAObj.IdenticalImage(FirstFile, SecondFile)) {
				System.out.println("Identical Image");

			} else {

				System.out.println("Different Image");
			}
		}

		
	}
}
