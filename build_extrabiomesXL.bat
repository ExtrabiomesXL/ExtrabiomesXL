@echo off
echo -------------------------------- Building ExtrabiomesXL -----------------------------------
set olddir=%cd%
cd ..\forge\mcp
echo Backing up src
XCOPY src src-bak /E /I /Q
echo.
echo Copying source 
XCOPY ..\..\ExtrabiomesXL\common src\minecraft /E /Q
echo.
call recompile.bat
call reobfuscate_srg.bat
echo.
echo Adding release assets
XCOPY ..\..\ExtrabiomesXL\resources\* reobf\minecraft /s /q
XCOPY ..\..\ExtrabiomesXL\common\extrabiomes\lang\*.xml reobf\minecraft\extrabiomes\lang /e /s
echo.
echo Restoring src-bak
RMDIR /S /Q src
REN src-bak src
cd %olddir%
echo copying output to jar
CScript zip.vbs %cd%\..\forge\mcp\reobf\minecraft %cd%\extrabiomes.zip
REN extrabiomes.zip extrabiomes.jar
PAUSE
