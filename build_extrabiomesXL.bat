@echo off
echo -------------------------------- Building ExtrabiomesXL -----------------------------------
echo Backing up src
XCOPY src src-bak /E /I /Q
echo.
echo Copying source 
XCOPY extrabiomes\src src\common /E /Q
echo.
call recompile.bat
call reobfuscate.bat
echo.
echo Adding release assets
REM XCOPY MMPL-1.0.txt reobf\minecraft /Q
REM XCOPY MMPL-1.0.txt reobf\minecraft_server /Q
REM XCOPY extrabiomes\*.txt reobf\minecraft /Q
REM XCOPY extrabiomes\*.txt reobf\minecraft_server /Q
REM XCOPY extrabiomes\mcmod.info reobf\minecraft /Q
REM XCOPY extrabiomes\mcmod.info reobf\minecraft_server /Q
REM XCOPY extrabiomes\src\minecraft\extrabiomes\*.png reobf\minecraft\extrabiomes /E /Q
REM XCOPY extrabiomes\artwork\eb-logo.png reobf\minecraft\extrabiomes /Q
REM XCOPY extrabiomes\artwork\eb-logo.png reobf\minecraft_server\extrabiomes /Q
echo.
echo Restoring src-bak
RMDIR /S /Q src
REN src-bak src
PAUSE
