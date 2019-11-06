#!/bin/bash
xwd -id $(wmctrl -l | grep $1 | tail -1 | cut -d' ' -f 1) -silent | xwdtopnm | pnmtopng > artifacts/$2.reference.png
