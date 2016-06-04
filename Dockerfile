FROM opensuse:42.1
MAINTAINER Bob W. Hogg <rwhogg@linux.com>
USER root
RUN zypper --non-interactive install java-devel wget tar
WORKDIR /opt
RUN wget https://dl.google.com/android/android-sdk_r24.4.1-linux.tgz
RUN tar xvf android-sdk_r24.4.1-linux.tgz
RUN printf "y\n" | android-sdk-linux/tools/android update sdk --no-ui --all --filter 7,33,108,139
ENV ANDROID_HOME=/opt/android-sdk-linux

COPY . /app
WORKDIR /app
RUN useradd droidbuilder
RUN chown -R droidbuilder /app
USER droidbuilder
