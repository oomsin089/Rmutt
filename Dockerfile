FROM ubuntu:latest
LABEL authors="natta"

ENTRYPOINT ["top", "-b"]

EXPOSE 8080
