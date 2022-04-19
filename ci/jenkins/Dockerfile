FROM jenkins/jenkins:lts-jdk11

USER root
RUN apt-get update
RUN apt-get install -y gnupg locales fonts-noto wget curl vim fluxbox x11vnc xvfb \
  && wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | apt-key add - \
  && wget -q https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb \
  && apt install -y ./google-chrome-stable_current_amd64.deb \
  && apt clean \
  && rm -rf /var/lib/apt/lists/ \
  && rm google-chrome-stable_current_amd64.deb \
  && curl -sSL https://get.docker.com/ | sh

RUN ln -sf  /usr/share/zoneinfo/Asia/Tokyo /etc/localtime
RUN locale-gen ja_JP.UTF-8

ENV LANG ja_JP.UTF-8

RUN groupadd docker \
  && usermod -aG docker jenkins

COPY --chown=jenkins:jenkins plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN jenkins-plugin-cli -f /usr/share/jenkins/ref/plugins.txt