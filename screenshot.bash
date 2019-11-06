#!/bin/bash
xwd -id $(wmctrl -l | grep $1 | cut -d' ' -f 1) -silent | xwdtopnm | pnmtopng > artifacts/Test_com_pflager_redbook_$1.reference.png
