package org.pflager.redbook.dpp;

import com.pflager.glut;

public class template extends glut {

	public int main(int argc, String[] argv)
	{ return 0; }

	public static void main(String[] args) {
		System.exit(new template().main(args.length, args));
	}

}
