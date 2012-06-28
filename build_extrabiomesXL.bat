@echo off
echo -------------------------------- Building ExtrabiomesXL -----------------------------------
echo Backing up src
XCOPY src src-bak /E /I /Q
echo.
echo Copying client source 
XCOPY extrabiomes\src\minecraft src\minecraft /E /Q
XCOPY extrabiomes\src\common src\minecraft /E /Q
echo.
echo Copying server sources
XCOPY extrabiomes\src\minecraft_server src\minecraft_server /E /Q
XCOPY extrabiomes\src\common src\minecraft_server /E /Q
echo.
call recompile.bat
call reobfuscate.bat
echo.
echo Adding release assets
XCOPY extrabiomes\*.txt reobf\minecraft /E /Q
XCOPY extrabiomes\*.txt reobf\minecraft_server /E /Q
XCOPY extrabiomes\src\minecraft\extrabiomes\*.png reobf\minecraft\extrabiomes /E /Q
XCOPY extrabiomes\artwork\eb-logo.png reobf\minecraft\extrabiomes /E /Q
XCOPY extrabiomes\artwork\eb-logo.png reobf\minecraft_server\extrabiomes /E /Q
echo.
echo Restoring src-bak
RMDIR /S /Q src
REN src-bak src
PAUSE
