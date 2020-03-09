#!/bin/bash
cp $(ls -1 2*.md | sort -r | head -1) $(date -u +%y%m%d%H%M%SZ).md
