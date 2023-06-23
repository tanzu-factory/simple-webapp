#!/bin/bash

export $(cat $HOME/.env | xargs)


if [[ ! -f $HOME/binaries/scripts/download-common-scripts.sh ]]
then
    if [[ ! -d  "$HOME/binaries/scripts" ]]
    then
        mkdir -p $HOME/binaries/scripts
    fi
    printf "\n\n************Downloading Merlin file Getter**************\n\n"
    curl -L https://raw.githubusercontent.com/alinahid477/common-merlin-scripts/main/scripts/download-common-scripts.sh -o $HOME/binaries/scripts/download-common-scripts.sh
    sleep 1
    chmod +x $HOME/binaries/scripts/download-common-scripts.sh
    sleep 1
    printf "\n\n\n///////////// COMPLETED //////////////////\n\n\n"
    printf "\n\n"
fi

if [[ ! -f $HOME/binaries/scripts/returnOrexit.sh ]]
then
    if [[ ! -d  "$HOME/binaries/scripts" ]]
    then
        mkdir -p $HOME/binaries/scripts
    fi
    printf "\n\n************Downloading Tanzu Scripts**************\n\n"
    $HOME/binaries/scripts/download-common-scripts.sh tanzucli scripts
    sleep 1
    if [[ -n $BASTION_HOST ]]
    then
        $HOME/binaries/scripts/download-common-scripts.sh bastion scripts/bastion
        sleep 1
    fi
    printf "\n\n\n///////////// COMPLETED //////////////////\n\n\n"
    printf "\n\n"
fi


printf "\n\nsetting executable permssion to all binaries sh\n\n"
ls -l $HOME/binaries/scripts/*.sh | awk '{print $9}' | xargs chmod +x
sleep 1
## housekeeping
rm /tmp/checkedConnectedK8s > /dev/null 2>&1
rm $HOME/configs/output > /dev/null 2>&1

source $HOME/binaries/scripts/returnOrexit.sh
source $HOME/binaries/scripts/color-file.sh
source $HOME/binaries/scripts/init-prechecks.sh


## Below code BINDs the CLI tools.
## Then provide bash / shell access where user executed merlin or tanzu command to perform necessary actions.

## 17/04/2023 --- Below is commented out as I am not creating k8s cluster as part of this any more. 
# printf "\n\n************Checking cloud CLI if needed**************\n\n"
# source $HOME/binaries/scripts/install-cloud-cli.sh
# if [[ -n $AWS_ACCESS_KEY_ID ]]
# then
#     # aks cluster does NOT require az cli to be present
#     # BUT eks cluster does. Hence installing aws cli
#     installAWSCLI
# fi

printf "\n\n************Checking Tanzu CLI binaries**************\n\n"
source $HOME/binaries/scripts/install-tanzu-cli.sh
installTanzuCLI
ret=$?
if [[ $ret == 1 ]]
then
    printf "\nERROR: Tanzu CLI was not successfully installed. Please check if you have place right tar file in the binaries directory.\n"
    exit 1
fi
printf "DONE\n\n\n"


# UPDATE: 18/11/2022: Commenting the the beloe if block
# -----------------------
# No need to do the below (just to link the kapp binary). 
# Because I have added carvel tooling installation in the docker file the kapp binary should already be available.

# if [[ $INSTALL_TANZU_CLUSTER_ESSENTIAL == 'COMPLETED' ]]
# then
#     # The purpose for calling installClusterEssential here again 
#     # is so that the script can perform cp $HOME/tanzu-cluster-essentials/kapp /usr/local/bin/kapp 

#     # The purpose for this code block here is NOT to trigger re-deploy of cluster-essential in k8s cluster
#     # BUT to only add kapp in /usr/local/bin/kapp
    
#     printf "\n\n************Checking Cluster Essential Kapp binaries**************\n\n"
#     source $HOME/binaries/scripts/install-cluster-essential.sh
#     installClusterEssential
#     printf "DONE\n\n\n"
# fi



if [[ -z $SILENTMODE || $SILENTMODE != 'YES' ]]
then
    printf "\n\n\n"

    printf "\nUsage:\n"
    printf "\ttanzu --help\n"

    printf "\n\n\n"

    cd ~
    /bin/bash
fi