#!/bin/bash
# Author: FnordMan
echo -------------------------------- Building ExtrabiomesXL -----------------------------------
echo Backing up src
cp -R src src-bak 
echo
echo Copying source
cp -R extrabiomes/src src/common
echo
./recompile.sh
./reobfuscate.sh
echo
echo Adding release assets
cp MMPL-1.0.txt reobf/minecraft 
cp extrabiomes/*.txt reobf/minecraft
cp extrabiomes/mcmod.info reobf/minecraft 
cp extrabiomes/src/extrabiomes/*.png reobf/minecraft/extrabiomes
echo
echo Restoring src-bak
rm -rf src
mv src-bak src
