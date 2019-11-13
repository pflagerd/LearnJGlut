#!/bin/bash
xwd -id $(xwininfo -root -children -tree | grep $1 | tail -1 | cut -d ' ' -f 9) -silent | xwdtopnm | pnmtopng > artifacts/$2