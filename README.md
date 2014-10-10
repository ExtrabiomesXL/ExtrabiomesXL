### Links of Interest
 - [Official ExtrabiomesXL Wiki](https://github.com/ExtrabiomesXL/ExtrabiomesXL/wiki)
 - [Downloads](http://goo.gl/gxlmm)
 - [Issue Tracking System](https://github.com/ExtrabiomesXL/ExtrabiomesXL/issues)
 
* * *

### How to install and use the source code ####

[Set Up Java](#set-up-java)

[Set Up Git](#set-up-git)

[Set Up This Project](#set-up-this-project)

[Compile This Project](#compile-this-project)

[Update Your Repository](#update-your-repository)

####Set Up Java
The Java JDK is used to compile this project.

1. Download and install the Java JDK.
	* [Windows/Mac download link](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html).  Scroll down, accept the `Oracle Binary Code License Agreement for Java SE`, and download it (if you have a 64-bit OS, please download the 64-bit version).
	* Linux: Installation methods for certain popular flavors of Linux are listed below.  If your distribution is not listed, follow the instructions specific to your package manager or install it manually [here](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html).
		* Gentoo: `emerge dev-java/oracle-jdk-bin`
		* Archlinux: `pacman -S jdk7-openjdk`
		* Ubuntu/Debian: `apt-get install openjdk-7-jdk`
		* Fedora: `yum install java-1.7.0-openjdk`
2. Windows: Set environment variables for the JDK.
    * Go to `Control Panel\System and Security\System`, and click on `Advanced System Settings` on the left-hand side.
    * Click on `Environment Variables`.
    * Under `System Variables`, click `New`.
    * For `Variable Name`, input `JAVA_HOME`.
    * For `Variable Value`, input something similar to `C:\Program Files\Java\jdk1.7.0_51` EXACTLY AS SHOWN (*or wherever your Java JDK installation is*), and click `Ok`.
    * Scroll down to a variable named `Path`, and double-click on it.
    * Append `;%JAVA_HOME%\bin` EXACTLY AS SHOWN and click `Ok`.  Make sure the location is correct; double-check just to make sure.
3. Open up your command line and run `javac`.  If it spews out a bunch of possible options and the usage, then you're good to go.

####Set Up Git
Git is used to clone this repository and update your local copy.

1. Download and install Git [here](http://git-scm.com/download/).
	* *Optional*: Download and install a Git GUI client, such as SourceTree, Github for Windows/Mac, TortoiseGit, etc.  A nice list is available [here](http://git-scm.com/downloads/guis).

####Set Up This Project
This section assumes that you're using the command-line version of Git.

1. Open up your command line.
2. Set up ```ossrhUsername``` and ```ossrhPassword``` per [this](http://central.sonatype.org/pages/gradle.html#credentials). If you to not have a sonatype.org account, just set these to anything.
2. Navigate to a place where you want to download this project (eg `C:\Github\Development\`) by executing `cd [folder location]`.  This location is known as `mcdev` from now on.
3. Execute `git clone git@github.com:ExtrabiomesXL/ExtrabiomesXL.git`.  This will download this project into a folder in `mcdev`.
4. Right now, you should have a directory that looks something like:

***
	mcdev
	\-ExtrabiomesXL
		\-This project's files (should have `build.gradle`, etc.)
***

####Compile This Project
1. Execute `gradlew build`. If you did everything right, `BUILD SUCCESSFUL` will be displayed after it finishes.
    * If you see `BUILD FAILED`, check the error output (it should be right around `BUILD FAILED`), fix everything (if possible), and try again.
3. Navigate to `mcdev\ExtrabiomesXL\build\libs`.
    *  You should see a `.jar` file named `[A.A.AA]ExtrabiomesXL-B.B.jar`, where A.A.AA is the `minecraft_version` value in `build.properties` and B.B is the `mod_version` value in `build.properties`. This is the mod file to be used with Minecraft.
    *  Additionally, you should see two more `.jar` files named `[A.A.AA]ExtrabiomesXL-deobf-B.B.jar` and `[A.A.AA]ExtrabiomesXL-deobf-B.B-src.jar`. These are, respectively, the development modfile and the source code. The development mod file can be used when testing mods that are under development.
4. Copy the first jar into your Minecraft mods folder, and you are done!

####Update Your Repository
In order to get the most up-to-date builds, you'll have to periodically update your local repository and recompile this project.

1. Open up your command line.
2. Navigate to `mcdev/ExtrabiomesXL` in the console.
3. Make sure you have not made any changes to the local repository, or else there might be issues with Git.
	* If you have, try reverting them to the status that they were when you last updated your repository.
4. Execute `git pull master`.  This pulls all commits from the official repository that do not yet exist on your local repository and updates it.


* * *

ExtrabiomesXL
=============
The ExtrabiomesXL Mod for Minecraft

![Extrabiomes XL](https://raw.github.com/ExtrabiomesXL/extrabiomes-artwork/master/code%20repository/logo.png)

<a rel="license" href="http://creativecommons.org/licenses/by-sa/3.0/deed.en_US"><img alt="Creative Commons License" style="border-width:0" src="http://i.creativecommons.org/l/by-sa/3.0/80x15.png" /></a><br /><span xmlns:dct="http://purl.org/dc/terms/" property="dct:title">ExtrabiomesXL</span> by <a xmlns:cc="http://creativecommons.org/ns#" href="https://github.com/ExtrabiomesXL?tab=members" property="cc:attributionName" rel="cc:attributionURL">The ExtrabiomesXL Team</a> is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-sa/3.0/deed.en_US">Creative Commons Attribution-ShareAlike 3.0 Unported License</a>.
