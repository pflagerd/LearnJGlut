xwd -id $(wmctrl -l | grep colormat | cut -d' ' -f 1) -silent | xwdtopnm | pnmtopng > colormat.png
