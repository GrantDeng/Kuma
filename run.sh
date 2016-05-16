#!/bin/bash
android avd;
./gradlew installDebug;
adb install -r app/build/outputs/apk/app-debug.apk;
