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
cp extrabiomes/*.txt reobf/minecraft/
cp extrabiomes/mcmod.info reobf/minecraft/
cp extrabiomes/src/extrabiomes/*.png reobf/minecraft/extrabiomes/
mkdir -p reobf/minecraft/extrabiomes/lang/
cp extrabiomes/src/extrabiomes/lang/*.xml reobf/minecraft/extrabiomes/lang/
echo
echo Restoring src-bak
rm -rf src
mv src-bak src
