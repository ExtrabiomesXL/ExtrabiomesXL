#!/bin/bash
# Author: FnordMan
echo -------------------------------- Building ExtrabiomesXL -----------------------------------
echo Backing up src
cp -R src src-bak 
echo
echo Copying source
cp -R common src/minecraft
echo
./recompile.sh
./reobfuscate.sh
echo
echo Adding release assets
cp *.txt reobf/minecraft/
cp resources/mods reobf/minecraft/
mkdir -p reobf/minecraft/extrabiomes/lang/
cp common/extrabiomes/lang/*.xml reobf/minecraft/extrabiomes/lang/
echo
echo Restoring src-bak
rm -rf src
mv src-bak src
