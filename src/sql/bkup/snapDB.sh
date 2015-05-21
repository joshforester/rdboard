#!/bin/bash
#
# Josh Forester
# 2009/09/09
#
# This script performs a snapshot of the current dev database
# for use by future calls to initializeDatabase.sh
#

DATASET_PATH=`pwd`
DEFAULT_DATASET_VERSION=`date +%Y%m%d`
DEFAULT_ENVIRON=dev

source ../leaderboardAdmCreds.profile
DATASET_VERSION=$DEFAULT_DATASET_VERSION
ENVIRON=$DEFAULT_ENVIRON

printUsage() {
        echo "Usage: $0"
        echo " --version <dataset version number>"
	echo " [--prd|--dev]"
        return
}

# Vet arguments
if [ $# -ne 3 ] ; then
	if [ $# -ne 2 ]; then
		if [ $# -ne 1 ]; then
        		if [ $# -ne 0 ]; then
                		printUsage
                		exit 1
        		fi
		fi
	fi
fi

case $1 in
--version) DATASET_VERSION=$2;;
--help) printUsage; exit 1;;
--prd) ENVIRON=prd;;
--dev) ENVIRON=dev;;
"") ;;
*) printUsage; exit 1;;
esac

case $3 in
--prd) ENVIRON=prd;;
--dev) ENVIRON=dev;;
"") ;;
*) printUsage; exit 1;;
esac

mysqldump --add-drop-table --host=$MYSQL_HOST --user $MYSQL_USER --password $MYSQL_DB > ${DATASET_PATH}/${ENVIRON}Set.${DATASET_VERSION}.sql

exit 0

