FROM postgres:latest
ENV POSTGRES_DB ghost-net
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD admin

COPY init.sql /docker-entrypoint-initdb.d/


