#!/bin/sh

set -e

echo "Injecting MCPForge in to workspace..."
time cp -fr $JENKINS_HOME/MCPForgeSetup/workspace/* $WORKSPACE/

cd extrabiomes
VERSION="`cat mcmod.info | sed -n 's/[^"]*"version"[^"]*"\([0123456789\.]*.*\)".*/\1/p' | awk '{print $1}' | head -n 1`.$BUILD_NUMBER"
cd ..
echo "Generated version of ${JOB_NAME} is:  ${VERSION}"

echo "Copying ${JOB_NAME} into MCP..."
cd src/common
cp -rf ../../extrabiomes/src/* .
cd ../..

echo "Recompiling with ${JOB_NAME}..."
time bash ./recompile.sh

echo "Reobfuscating ${JOB_NAME}..."
time bash ./reobfuscate.sh

echo "Creating ${JOB_NAME} distribution package..."
cd reobf
cd minecraft
cp -rf ../../extrabiomes/mcmod.info .
cp -rf ../../extrabiomes/*.txt .
cp -rf ../../extrabiomes/src/extrabiomes/*.png extrabiomes/
zip -r9 "../${JOB_NAME}-universal-${VERSION}.jar" *
cd ..

echo "Uploading universal package..."
sh $JENKINS_HOME/Scripts/jenkins-upload.sh
cd ..