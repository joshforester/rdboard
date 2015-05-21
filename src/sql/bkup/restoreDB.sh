#!/bin/bash
#
# Josh Forester
# 2009/09/09
#
# This script reinitializes the development database.
#

DATASET_PATH=`pwd`
DEFAULT_DATASET_VERSION=20091106

source ../leaderboardAdmCreds.profile
DATASET_VERSION=$DEFAULT_DATASET_VERSION

printUsage() {
        echo "Usage: $0"
        echo " --version <dataset version number>"
        return
}

# Vet arguments
if [ $# -ne 2 ] ; then
        if [ $# -ne 0 ]; then
                printUsage
                exit 1
        fi
fi

case $1 in
--version) DATASET_VERSION=$2;;
--help) printUsage; exit 1;;
"") ;;
*) printUsage; exit 1;;
esac


mysql -u $MYSQL_USER -p -h $MYSQL_HOST $MYSQL_DB < ${DATASET_PATH}/devSet.${DATASET_VERSION}.sql

exit 0

