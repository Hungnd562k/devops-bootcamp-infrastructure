FROM jenkins/ssh-agent:jdk21

USER root

RUN apt-get update && apt-get install -y docker.io

ARG DOCKER_GID=986
RUN groupmod -g ${DOCKER_GID} docker || groupadd -g ${DOCKER_GID} docker_host
RUN usermod -aG docker,docker_host jenkins