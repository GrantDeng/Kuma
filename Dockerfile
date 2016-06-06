FROM opensuse:42.1
MAINTAINER Bob W. Hogg <rwhogg@linux.com>
RUN zypper --non-interactive install java-devel wget tar libstdc++6-32bit zlib-devel-32bit libz1-32bit
WORKDIR /opt
RUN wget https://dl.google.com/android/android-sdk_r24.4.1-linux.tgz
RUN tar xvf android-sdk_r24.4.1-linux.tgz
RUN printf "y\n" | android-sdk-linux/tools/android update sdk --no-ui --all --filter 7,33,108,139
ENV ANDROID_HOME=/opt/android-sdk-linux
ENV JAVA_HOME=/usr/lib64/jvm/jre-1.8.0-openjdk

COPY . /app
WORKDIR /app
RUN ./gradlew --info
RUN ./gradlew --info assembleDebug
