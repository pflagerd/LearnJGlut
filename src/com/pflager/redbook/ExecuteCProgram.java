package ImageCompare;

import java.io.IOException;

public class ExecuteCProgram {
	private String CFileName;

	public ExecuteCProgram(String cFileName) {
		super();
		CFileName = cFileName;
	}

	public void CompilingCFile() {
		ProcessBuilder Compiler = new ProcessBuilder("notepad.exe", "/redbook-1.1-src/src/" +  CFileName + ".c", "-o", "CFileName");
		try {
			Compiler.start();
			try {
				Compiler.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		ExecuteCProgram ExecuteCProgramObj = new ExecuteCProgram("cube");
		ExecuteCProgramObj.CompilingCFile();
	}
}
