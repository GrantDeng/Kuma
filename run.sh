#!/bin/bash
# This is for Bob's benefit. Don't use it if you don't want to.
android avd;
./gradlew installDebug;
adb install -r app/build/outputs/apk/app-debug.apk;
