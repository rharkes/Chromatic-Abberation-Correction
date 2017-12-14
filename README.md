# Chromatic-Abberation-Correction
ImageJ plugin to do chromatic abberation correction on csv data obtained in ThunderSTORM. (https://github.com/zitmen/thunderstorm)
This plugin uses javaFX.
http://www.oracle.com/technetwork/java/javase/overview/javafx-overview-2158620.html
If the GUI looks strange, please upgrade your javaFX version.

Features:
* Can fit the affine transform to two equally sized sets of point data. For example when measuring multi colored beads.
* Can save affine transform as aft-file
* Can correct a .csv file using aft-files
* Opens the most recently used aft-file at startup
