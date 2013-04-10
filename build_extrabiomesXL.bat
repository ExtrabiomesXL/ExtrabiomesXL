@echo off
echo -------------------------------- Building ExtrabiomesXL -----------------------------------
echo Backing up src
XCOPY src src-bak /E /I /Q
echo.
echo Copying source 
XCOPY common src\minecraft /E /Q
echo.
call recompile.bat
call reobfuscate.bat
echo.
echo Adding release assets
XCOPY *.txt reobf\minecraft /Q
XCOPY resources\mods reobf\minecraft /Q
XCOPY common\extrabiomes\lang\*.xml reobf\minecraft\extrabiomes\lang /E /Q
echo.
echo Restoring src-bak
RMDIR /S /Q src
PAUSE
REN src-bak src
PAUSE
