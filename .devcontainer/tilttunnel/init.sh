#!/bin/bash

if [[ -f $HOME/.env ]]
then
    export $(cat $HOME/.env | xargs)
fi

if [[ -n $DOCKER_LOGIN_SERVER && -n $DOCKER_LOGIN_USERNAME && -n $DOCKER_LOGIN_PASSWORD ]]
then
    docker login $DOCKER_LOGIN_SERVER -u $DOCKER_LOGIN_USERNAME -p $DOCKER_LOGIN_PASSWORD
fi

if [[ -n $TUNNEL_LOCAL_PORT && -n $TUNNEL_TARGET_PORT && -n $TUNNEL_TARGET_IP && -n $TUNNEL_JUMPHOST_USER && -n $TUNNEL_JUMPHOST_IP ]]
then
    ssh -i /workspace/.devcontainer/tilttunnel/.ssh/id_rsa -4 -fNT -L $TUNNEL_LOCAL_PORT:$TUNNEL_TARGET_IP:$TUNNEL_TARGET_PORT $TUNNEL_JUMPHOST_USER@$TUNNEL_JUMPHOST_IP
fi
