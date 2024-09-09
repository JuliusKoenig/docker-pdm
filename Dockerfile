FROM ubuntu:24.04
LABEL authors="Julius Koenig"
LABEL description="A Docker image for CIs using pdm."
LABEL version="0.1.0"

# update apt
RUN apt-get update

# install deadsnakes ppa
RUN apt-get install software-properties-common -y
RUN add-apt-repository ppa:deadsnakes/ppa -y
RUN apt-get update

# install python
RUN apt-get install python3.8-full -y
RUN apt-get install python3.9-full -y
RUN apt-get install python3.10-full -y
RUN apt-get install python3.11-full -y
RUN apt-get install python3.12-full -y

# install pip
RUN apt-get install python3-pip -y

# install pdm
RUN pip install pdm --break-system-packages
