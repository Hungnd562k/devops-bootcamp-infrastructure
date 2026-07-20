FROM jenkins/ssh-agent:jdk21

USER root

RUN apt-get update && apt-get install -y docker.io

ARG DOCKER_GID=986
# Đổi GID của group docker sẵn có sang 986
RUN groupmod -g ${DOCKER_GID} docker
# Thêm user jenkins vào group docker
RUN usermod -aG docker jenkins