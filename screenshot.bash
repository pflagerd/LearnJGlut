#!/bin/bash -x
xwd -id $(xwininfo -root -children -tree | grep -w $1 | tail -1 | awk '{ print $1; }') -silent | xwdtopnm | pnmtopng > artifacts/$2